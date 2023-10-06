package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.api.helpers.Category;

@Data
public class UserRequestModel {
    @SerializedName("authorities")
    private String[] authorities;
    @SerializedName("category")
    private Category category;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("email")
    private String email;
    @SerializedName("confirmPassword")
    private String confirmPassword;
}
