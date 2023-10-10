package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PostResponseModel {
    @SerializedName("postId")
    private String id;
    @SerializedName("content")
    private String content;
    @SerializedName("picture")
    private String picture;
    @SerializedName("date")
    private String date;
    @SerializedName("public")
    private boolean isPublic;
    @SerializedName("liked")
    private boolean isLiked;
    @SerializedName("likes")
    private Object[] likes;
}
