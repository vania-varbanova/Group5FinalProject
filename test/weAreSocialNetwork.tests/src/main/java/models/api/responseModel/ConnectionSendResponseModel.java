package models.api.responseModel;

import lombok.Data;

@Data
public class ConnectionSendResponseModel {
    private String senderUsername;
    private String receiverUsername;

}
