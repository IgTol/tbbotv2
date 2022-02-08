package by.itoverone.tbbotv2.botData;

import by.itoverone.tbbotv2.dao.BotStatusService;

import by.itoverone.tbbotv2.dao.DBService;
import by.itoverone.tbbotv2.entity.BotStatus;
import by.itoverone.tbbotv2.entity.Dbup;
import by.itoverone.tbbotv2.enums.AnnEnum;
import by.itoverone.tbbotv2.enums.BotEnum;
import by.itoverone.tbbotv2.service.ButtonService;
import by.itoverone.tbbotv2.service.MessageService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.List;

import static by.itoverone.tbbotv2.botData.BotLoginProperties.*;

@Component
public class Bot extends TelegramLongPollingBot {
    private final BotStatusService botStatusService;
    private final DBService dbService;
    private Dbup dbup;
    private BotStatus botStatus;
    private final ButtonService buttonService;
    private final MessageService messageService;

    public Bot(BotStatusService botStatusService, DBService dbService, ButtonService buttonService, MessageService messageService) {
        this.botStatusService = botStatusService;
        this.dbService = dbService;
        this.buttonService = buttonService;
        this.messageService = messageService;
    }


    @SneakyThrows
    @Override
    public synchronized void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            String message = update.getMessage().getText();
            if (message != null) {
                String chatId = update.getMessage().getChatId().toString();
                if (message.startsWith("/start")) {
                    showMenu(chatId);
                } else if (botStatus != null && update.getMessage().hasText()) {
                    String id = dbup.getId();
                    botStatus = botStatusService.findFirstByChatId(chatId);
                    if (botStatus.getStatus().equals(BotEnum.ASK_MODEL.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_YEAR.toString());
                        dbService.updateBrand(id, message);
                        askAboutModel(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_YEAR.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_MILEAGE.toString());
                        dbService.updateModel(id, message);
                        askAboutYear(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_MILEAGE.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_PRICE.toString());
                        dbService.updateYear(id, message);
                        askAboutMileage(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_PRICE.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_PHOTO.toString());
                        dbService.updateMileage(id, message);
                        askAboutPrice(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_PHOTO.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_DESCRIPTION.toString());
                        dbService.updatePrice(id, message);
                        askAboutPhoto(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_DESCRIPTION.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.ASK_TELEPHONE.toString());
                        askAboutDescription(chatId);

                    } else if (botStatus.getStatus().equals(BotEnum.ASK_TELEPHONE.toString())) {
                        botStatusService.updateBotStatus(chatId, BotEnum.FINISH.toString());
                        dbService.updateDescription(id, message);
                        askAboutTelephone(chatId);
                    } else if (botStatus.getStatus().equals(BotEnum.FINISH.toString())) {
                        dbService.updateTelephone(id, message);
                        dbService.updateStatus(id, AnnEnum.WAITING.toString());
                        botStatusService.deleteBotStatus(chatId);
                        warnAboutCreationAd(chatId);
                    }
                }
            }
        }
        else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            String button = update.getCallbackQuery().getData();

            if (button.startsWith("1")) {
                botStatus = botStatusService.findById(chatId);
                if (botStatus != null) {
                    botStatusService.deleteBotStatus(chatId);
                    dbService.deleteAd(dbup.getId());
                }
                botStatus = new BotStatus();
                dbup = new Dbup();
                botStatus.setChatId(chatId);
                botStatus.setStatus(BotEnum.ASK_MODEL.toString());
                botStatus = botStatusService.save(botStatus);
                dbup.setChatId(chatId);
                dbService.saveAd(dbup);
                askAboutBrand(chatId);
            } else if (button.startsWith("2") || button.startsWith("3") || button.startsWith("4")) {
                List<Dbup> dbUpList;
                String role;

                if (button.startsWith("2")) {
                    dbUpList = dbService.getByStatus(AnnEnum.APPROVED.toString());
                    role = "ALL_ANN";
                } else if (button.startsWith("3")) {
                    dbUpList = dbService.findByChatId(chatId);
                    role = "MY_ANN";
                } else {
                    dbUpList = dbService.getByStatus(AnnEnum.WAITING.toString());
                    role = "ADMIN";
                }

                messageService.createMessage(chatId, dbUpList.toString());
                showAds(chatId, dbUpList, role);

            } else if (button.startsWith("hidden")) {

                String[] buttonText = buttonService.splitCallback(button);
                String id = buttonText[1];
                dbService.updateStatus(id, AnnEnum.HIDDEN.toString());
                execute(messageService.createEditMessage(
                        chatId,
                        update.getCallbackQuery().getMessage().getMessageId(),
                        buttonService.createShowMyAdButton("show " + id, id)));

            } else if (button.startsWith("show")) {

                String[] buttonText = buttonService.splitCallback(button);
                String id = buttonText[1];
                dbService.updateStatus(id, AnnEnum.APPROVED.toString());
                execute(messageService.createEditMessage(
                        chatId,
                        update.getCallbackQuery().getMessage().getMessageId(),
                        buttonService.createHiddenMyAdButton("hidden " + id, id)));

            } else if (button.startsWith("approve")) {
                String[] buttonText = buttonService.splitCallback(button);
                dbService.updateStatus(buttonText[1], AnnEnum.APPROVED.toString());
                execute(messageService.deleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId()));

            } else if (button.startsWith("reject") || button.startsWith("remove")) {
                String[] buttonText = buttonService.splitCallback(button);
                dbService.deleteAd(buttonText[1]);
                execute(messageService.deleteMessage(chatId, update.getCallbackQuery().getMessage().getMessageId()));
            }
        }
    }

    @SneakyThrows
    private void showMenu(String chatId) {
        SendMessage sendMessage;
        sendMessage = messageService.createMessage(chatId, MessageService.getStartMessage());
        if (chatId.equals(ADMIN_ID)) {
            sendMessage.setReplyMarkup(buttonService.createAdminMenuButtons());
        } else {
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            markup.setKeyboard(buttonService.createButtons());
            sendMessage.setReplyMarkup(markup);
        }
        execute(sendMessage);
    }

    @SneakyThrows
    private void showAds(String chatId, List<Dbup> dbupList, String role) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        for (Dbup dbup : dbupList) {
            sendMessage.setText(
                    dbup.getDescription() + "\n"
                            + "Brand: " + dbup.getBrand() + "\n"
                            + "Model: " + dbup.getModel() + "\n"
                            + "Year: " + dbup.getYear() + "\n"
                            + "Mileage: " + dbup.getMileage() + "\n"
                            + "Price: " + dbup.getPrice() + "\n"
                            + "Contact number: " + dbup.getTelephone() + "\n"
            );
            if (chatId.equals(ADMIN_ID) && role.equals("ADMIN")) {
                sendMessage.setReplyMarkup(buttonService.createApproveAndRejectButtons(
                        "approve " + dbup.getId(), "reject " + dbup.getId()));
            } else if (role.equals("MY_ANN")) {
                if (dbService.getStatusById(dbup.getId()).equals(AnnEnum.APPROVED.toString())) {
                    sendMessage.setReplyMarkup(buttonService.createHiddenMyAdButton("hidden " + dbup.getId(), dbup.getId()));
                } else if (dbService.getStatusById(dbup.getId()).equals(AnnEnum.HIDDEN.toString())) {
                    sendMessage.setReplyMarkup(buttonService.createShowMyAdButton("show " + dbup.getId(), dbup.getId()));
                } else {
                    sendMessage.setReplyMarkup(buttonService.createSingleDeleteMyAdButton(chatId));
                }
            }
            execute(sendMessage);
        }
    }

    @SneakyThrows
    private void askAboutBrand(String chatId) {
        execute(messageService.createMessage(chatId, "Enter brand"));
    }

    @SneakyThrows
    private void askAboutModel(String chatId) {
        execute(messageService.createMessage(chatId, "Enter model"));
    }

    @SneakyThrows
    private void askAboutMileage(String chatId) {
        execute(messageService.createMessage(chatId, "Enter auto mileage"));
    }

    @SneakyThrows
    private void askAboutYear(String chatId) {
        execute(messageService.createMessage(chatId, "Enter year of manufactured"));
    }

    @SneakyThrows
    private void askAboutPrice(String chatId) {
        execute(messageService.createMessage(chatId, "Put price"));
    }

    @SneakyThrows
    private void askAboutDescription(String chatId) {
        execute(messageService.createMessage(chatId, "Enter auto properties"));
    }

    @SneakyThrows
    private void askAboutPhoto(String chatId) {
        execute(messageService.createMessage(chatId, "Upload photo"));
    }

    @SneakyThrows
    private void askAboutTelephone(String chatId) {
        execute(messageService.createMessage(chatId, "Input your number"));
    }

    @SneakyThrows
    private void warnAboutCreationAd(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(buttonService.createButtons());
        SendMessage sendMessage = new SendMessage();
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
        sendMessage.setChatId(chatId);
        sendMessage.setText("Announcement create successful. Please wait approve");
        execute(sendMessage);
    }


    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

}
