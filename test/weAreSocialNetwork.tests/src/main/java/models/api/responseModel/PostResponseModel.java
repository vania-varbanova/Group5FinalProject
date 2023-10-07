package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostResponseModel {
    @SerializedName("postId")
    private String id;
    private String content;
    private String picture;
    @SerializedName("date")
    private String date;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("liked")
    private boolean isLiked;
}
