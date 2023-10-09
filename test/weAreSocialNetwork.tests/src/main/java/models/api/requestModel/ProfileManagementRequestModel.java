package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProfileManagementRequestModel {
 @SerializedName("birthYear")
 private String birthYear;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("location")
    private String locationId ;
    @SerializedName("city")
    private String cityId ;
    @SerializedName("picturePrivacy")
    private String picture;
    @SerializedName("sex")
    private String gender;
}
