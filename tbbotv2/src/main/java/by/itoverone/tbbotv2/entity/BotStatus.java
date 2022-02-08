package by.itoverone.tbbotv2.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "bot_status")
@NoArgsConstructor
@Getter
public class BotStatus {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String id;
    @Setter
    private String chatId;
    @Setter
    private String status;

//    public BotStatus() {
//    }
//
//    public BotStatus(String id, String chatId, String status) {
//        this.id = id;
//        this.chatId = chatId;
//        this.status = status;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getChatId() {
//        return chatId;
//    }
//
//    public void setChatId(String chatId) {
//        this.chatId = chatId;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        BotStatus botStatus = (BotStatus) o;
//        return Objects.equals(id, botStatus.id) && Objects.equals(chatId, botStatus.chatId) && Objects.equals(status, botStatus.status);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, chatId, status);
//    }
//
//    @Override
//    public String toString() {
//        return "BotStatus{" +
//                "id='" + id + '\'' +
//                ", chatId='" + chatId + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
}
