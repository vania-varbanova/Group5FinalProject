package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ConnectionSendRequestModel {
    @SerializedName("id")
    private String receiverId;
    @SerializedName("username")
    private String receiverUsername;
}
