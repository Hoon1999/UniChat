package webchat.knung.dto;

import java.time.LocalDateTime;

public interface ChatRoomDto {
    Long getChatRoom_id();
    String getChat_name();
    Long getChat_seq();
    String getImg_link();
    LocalDateTime getLastDate();
}
