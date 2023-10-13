package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import models.api.helpers.Location;

import java.time.LocalDate;

@Data
//@Builder
public class ProfileManagementRequestModel {
    @SerializedName("birthYear")
    private String birthYear;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("location")
    private Location location;
    @SerializedName("picturePrivacy")
    private boolean isPicturePrivate;
    @SerializedName("sex")
    private String gender;
}
