package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.api.helpers.Location;

import java.time.LocalDate;

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
    @SerializedName("location")
    private Location location ;
    @SerializedName("birthYear")
    private String birthYear;
    @SerializedName("personalReview")
    private String  personalReview;
    @SerializedName("memberSince")
    private String  memberSince;
    @SerializedName("picturePrivacy")
    private String  picturePrivacy;
}
