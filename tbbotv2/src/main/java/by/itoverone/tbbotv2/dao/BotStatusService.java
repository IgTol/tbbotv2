package by.itoverone.tbbotv2.dao;

import by.itoverone.tbbotv2.entity.BotStatus;
import by.itoverone.tbbotv2.repository.BotStatusRepository;
import org.springframework.stereotype.Service;

@Service
public class BotStatusService {
    private final BotStatusRepository repository;

    public BotStatusService(BotStatusRepository repository) {
        this.repository = repository;
    }

    public BotStatus save(BotStatus botStatus) {
        return repository.save(botStatus);
    }

    public void deleteBotStatus(String chatId) {
        repository.deleteBotStatus(chatId);
    }

    public BotStatus findById(String chatId) {
        return repository.getById(chatId);
    }

    public BotStatus findFirstByChatId(String chatId) {
        return repository.findFirstByChatId(chatId);
    }

    public void updateBotStatus(String chatId, String status) {
        repository.updateBotStatus(chatId, status);
    }
}
