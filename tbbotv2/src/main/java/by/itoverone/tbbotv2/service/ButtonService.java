package by.itoverone.tbbotv2.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Service
public class ButtonService {
    public List<List<InlineKeyboardButton>> createButtons() {
        InlineKeyboardButton createAd = new InlineKeyboardButton();
        createAd.setText("Create announcement");
        createAd.setCallbackData("CR");
        InlineKeyboardButton showAd = new InlineKeyboardButton();
        showAd.setText("Show announcements");
        showAd.setCallbackData("SH");
        InlineKeyboardButton myAd = new InlineKeyboardButton();
        myAd.setText("My announcements");
        myAd.setCallbackData("MA");
        List<InlineKeyboardButton> firstRow = new ArrayList<>();
        List<InlineKeyboardButton> secondRow = new ArrayList<>();
        List<InlineKeyboardButton> thirdRow = new ArrayList<>();
        firstRow.add(createAd);
        secondRow.add(showAd);
        thirdRow.add(myAd);
        return new ArrayList<>(List.of(
                firstRow,
                secondRow, thirdRow
        ));
    }

    public InlineKeyboardMarkup createShowMyAdButton(String callback, String id) {
        InlineKeyboardButton hiddenAd = new InlineKeyboardButton();
        hiddenAd.setText("Show");
        hiddenAd.setCallbackData(callback);
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(hiddenAd);
        row.add(createDeleteMyAdButton(id));
        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
        buttonList.add(row);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttonList);
        return markup;
    }

    public String[] splitCallback(String button) {
        return button.split("\\s");
    }

    public InlineKeyboardMarkup createHiddenMyAdButton(String callback, String id) {
        InlineKeyboardButton hiddenAd = new InlineKeyboardButton();
        hiddenAd.setText("Stopped shows");
        hiddenAd.setCallbackData(callback);
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(hiddenAd);
        row.add(createDeleteMyAdButton(id));
        List<List<InlineKeyboardButton>> buttonList = new ArrayList<>();
        buttonList.add(row);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttonList);
        return markup;
    }

    public InlineKeyboardMarkup createAdminMenuButtons() {
        InlineKeyboardButton adAdministration = new InlineKeyboardButton();
        adAdministration.setText("Manage announcement");
        adAdministration.setCallbackData("AD");
        List<InlineKeyboardButton> foreRow = new ArrayList<>();
        foreRow.add(adAdministration);
        List<List<InlineKeyboardButton>> inlineKeyboards = createButtons();
        inlineKeyboards.add(foreRow);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(inlineKeyboards);
        return markup;
    }

    public InlineKeyboardButton createDeleteMyAdButton(String id) {
        InlineKeyboardButton deleteMyAdButton = new InlineKeyboardButton();
        deleteMyAdButton.setText("Delete");
        deleteMyAdButton.setCallbackData("remove " + id);
        return deleteMyAdButton;
    }

    public InlineKeyboardMarkup createSingleDeleteMyAdButton(String id) {
        List<InlineKeyboardButton> deleteMyAddButton = new ArrayList<>();
        deleteMyAddButton.add(createDeleteMyAdButton(id));
        List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
        buttons.add(deleteMyAddButton);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(buttons);
        return markup;
    }

    public InlineKeyboardMarkup createApproveAndRejectButtons(String approveCallback, String rejectCallback) {
        InlineKeyboardButton approveButton = new InlineKeyboardButton();
        approveButton.setText("Approve");
        approveButton.setCallbackData(approveCallback);

        InlineKeyboardButton rejectButton = new InlineKeyboardButton();
        rejectButton.setText("Deny");
        rejectButton.setCallbackData(rejectCallback);

        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();
        buttonsRow.add(approveButton);
        buttonsRow.add(rejectButton);
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        inlineButtons.add(buttonsRow);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(inlineButtons);
        return markup;
    }
    public InlineKeyboardMarkup createLicenseApproveAndRejectButtons(String approveLicenseCallback, String rejectLicenseCallback) {
        InlineKeyboardButton approveLicenseButton = new InlineKeyboardButton();
        approveLicenseButton.setText("Approve");
        approveLicenseButton.setCallbackData(approveLicenseCallback);
        InlineKeyboardButton rejectLicenseButton = new InlineKeyboardButton();
        rejectLicenseButton.setText("Deny");
        rejectLicenseButton.setCallbackData(rejectLicenseCallback);

        List<InlineKeyboardButton> licButtonRow = new ArrayList<>();
        licButtonRow.add(approveLicenseButton);
        licButtonRow.add(rejectLicenseButton);
        List<List<InlineKeyboardButton>> inlineLicButtonRow = new ArrayList<>();
        inlineLicButtonRow.add(licButtonRow);
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.setKeyboard(inlineLicButtonRow);
        return markup;
    }
}
