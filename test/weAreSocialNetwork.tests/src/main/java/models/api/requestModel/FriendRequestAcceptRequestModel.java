package models.api.requestModel;

import lombok.Data;

@Data
public class FriendRequestAcceptRequestModel {
    private String receiverId;
    private String requestId;
}
