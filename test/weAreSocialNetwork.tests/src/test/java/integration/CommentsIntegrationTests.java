package integration;

import models.api.request.AuthenticateRequests;
import models.api.request.CommentRequest;
import models.api.request.PostRequests;
import models.api.requestModel.CommentRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.CommentResponseModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class CommentsIntegrationTests extends BaseIntegrationTest {

    private CommentRequest commentRequest;

    @Test
    public void createCommentUnderPost() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserResponseModel userResponseModel = userRequests.createUser(userRequestModel);
        AuthenticateRequests authenticateRequests = new AuthenticateRequests();
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(), postResponseModel.getId());
        CommentRequest commentRequest = new CommentRequest();
        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel, cookie);
        Assertions.assertNotNull(commentResponseModel.getCommentId());
        Assertions.assertEquals(commentRequestModel.getContentComment(), commentResponseModel.getContent(), formatErrorMessage("comment content"));
        Assertions.assertFalse(commentResponseModel.isLiked());
//        LocalDateTime myObj = LocalDateTime.now();
//        Assertions.assertEquals(commentResponseModel.getDate(),myObj,"error date");
    }
    @Test
    public void postSuccessfullyLiked() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);

//       postRequests.deletePost(postId, cookie);
//              databaseService.deleteUserWithId(userResponseModel.getId());


    }

//    @Test
//    public void commentSuccessfullyDispliked() throws SQLException {
//        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
//        userRequests.createUser(userRequestModel);
//        String cookie = authenticateRequests.authenticateUser(userRequestModel);
//        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
//        PostRequests postRequests = new PostRequests();
//        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
//        int postId = Integer.parseInt(postResponseModel.getId());
//        //лайкваме пост
//        //postRequests.LikeMyPost(postId,cookie);
//        //дислайк
//        // лайковете са нула
//        postRequests.deletePost(postId, cookie);
//        databaseService.deleteUserWithId(userResponseModel.getId());
//    }

}