package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.BaseUser;
import models.api.helpers.Category;

@Data
public class UserRequestModel extends BaseUser {
    @SerializedName("authorities")
    private String[] authorities;
    @SerializedName("category")
    private Category category;
    @SerializedName("confirmPassword")
    private String confirmPassword;

}
