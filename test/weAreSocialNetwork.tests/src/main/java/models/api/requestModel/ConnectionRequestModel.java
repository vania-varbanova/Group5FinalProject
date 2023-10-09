package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ConnectionRequestModel {
    @SerializedName("id")
    private String receivedId;
    @SerializedName("username")
    private String receivedUsername;
}
