package models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class BaseUser {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("username")
    private String username;
}
