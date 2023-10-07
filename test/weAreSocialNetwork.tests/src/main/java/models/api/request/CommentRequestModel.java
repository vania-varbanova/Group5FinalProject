package models.api.request;

import lombok.Data;

@Data
public class CommentRequestModel {
    private String userId;
    private String postId;
    private String content; // GENERATE WITH FAKER
}
