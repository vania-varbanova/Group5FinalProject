package models.api.requestModel;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class CommentRequestModel {
    @SerializedName("content")
    private String contentComment;
    @SerializedName("postId")
    private String  postId;
    @SerializedName("userId")
    private String  userId;
}
