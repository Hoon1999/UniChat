package webchat.unichat.form;

import org.springframework.web.multipart.MultipartFile;

public class ChattingRoomEditForm {
    private String roomId;
    private String roomName;
    private MultipartFile roomImage;
    private String originalFileName;
    private String storedFileName;
    private int fileAttached; // file 첨부 여부. (첨부: 1, 미첨부: 0)


    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public MultipartFile getRoomImage() {
        return roomImage;
    }

    public void setRoomImage(MultipartFile roomImage) {
        this.roomImage = roomImage;
    }

    public void setFileAttached(int fileAttached) {
        this.fileAttached = fileAttached;
    }
}
