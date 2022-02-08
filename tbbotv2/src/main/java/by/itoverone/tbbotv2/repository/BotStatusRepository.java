package by.itoverone.tbbotv2.repository;

import by.itoverone.tbbotv2.entity.BotStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BotStatusRepository extends CrudRepository<BotStatus, String> {
    @Modifying
    @Query("update BotStatus set status =:status where chatId =:chatId")
    void updateBotStatus(@Param("chatId") String chatId, @Param("status") String status);

    @Modifying
    @Query("delete BotStatus where chatId =:chatId")
    void deleteBotStatus(@Param("chatId") String chatId);

    @Query("from BotStatus where chatId =:chatId")
    BotStatus getById(@Param("chatId") String chatId);

    BotStatus findFirstByChatId(String chatId);
}
