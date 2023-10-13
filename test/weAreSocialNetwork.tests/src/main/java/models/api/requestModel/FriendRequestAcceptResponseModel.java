package models.api.requestModel;

import lombok.Data;

@Data
public class FriendRequestAcceptResponseModel {
    private String senderUsername;
    private String receiverUsername;
}
