package models.api.responseModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CommentResponseModel {
    @SerializedName("commentId")
    private String commentId;
    @SerializedName("content")
    private String content;
    @SerializedName("date")
    private String date;
    @SerializedName("liked")
    private boolean isLiked;

}
