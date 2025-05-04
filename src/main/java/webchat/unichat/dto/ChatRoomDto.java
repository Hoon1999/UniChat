package webchat.unichat.dto;

import java.time.LocalDateTime;

public interface ChatRoomDto {
    Long getChat_room_id();
    String getChat_room_name();
    Long getChat_seq();
    String getChat_room_img();
    LocalDateTime getLast_date();
}
