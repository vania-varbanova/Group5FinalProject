package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import models.api.helpers.Category;

@Data
public class ReceiverUserRequestModel {
    @Data
    public class UserRequestModel {
        @SerializedName("authorities")
        private String[] authorities;
        @SerializedName("category")
        private Category category;
        @SerializedName("confirmPassword")
        private String confirmPassword;
        @SerializedName("email")
        private String email;
        @SerializedName("password")
        private String password;
        @SerializedName("username")
        private String receiverUsername;
    }
}
