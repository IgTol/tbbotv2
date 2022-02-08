package by.itoverone.tbbotv2.dao;

import by.itoverone.tbbotv2.entity.BotStatus;
import by.itoverone.tbbotv2.repository.BotStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class BotStatusService {
    private final BotStatusRepository botStatusRepository;

    public BotStatusService(BotStatusRepository repository) {
        this.botStatusRepository = repository;
    }

    public BotStatus save(BotStatus botStatus) {
        return botStatusRepository.save(botStatus);
    }

    public void deleteBotStatus(String chatId) {
        botStatusRepository.deleteBotStatus(chatId);
    }

    public BotStatus findById(String chatId) {
        return botStatusRepository.getById(chatId);
    }

    public BotStatus findFirstByChatId(String chatId) {
        return botStatusRepository.findFirstByChatId(chatId);
    }

    public void updateBotStatus(String chatId, String status) {
        botStatusRepository.updateBotStatus(chatId, status);
    }
}
