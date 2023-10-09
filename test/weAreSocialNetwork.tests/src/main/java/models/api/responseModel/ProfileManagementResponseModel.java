package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ProfileManagementResponseModel {
    @SerializedName("id")
    private String  userId;
    @SerializedName("firstName")
    private String  firstName;
    @SerializedName("lastName")
    private String  lastName;
    @SerializedName("sex")
    private String  gender;
    @SerializedName("locationId")
    private String locationId ;
    @SerializedName("cityId")
    private String  cityId;
    @SerializedName("birthYear")
    private String  birthYear;
    @SerializedName("personalReview")
    private String  personalReview;
    @SerializedName("memberSince")
    private String  memberSince;
    @SerializedName("picturePrivacy")
    private String  picturePrivacy;
}
