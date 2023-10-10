package models.api.helpers;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GetUserRequest {
    @SerializedName("id")
    private String requestId;
    @SerializedName("approved")
    private String approved;
    @SerializedName("seen")
    private String seen;
    @SerializedName("timeStamp")
    private String timeStamp;
}

