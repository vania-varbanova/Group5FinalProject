package RESTAssuredTests;

import io.restassured.response.ResponseOptions;
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
import services.DatabaseService;

import java.sql.SQLException;

public class CommentsIntegrationTests extends BaseIntegrationTest {

    private CommentRequest commentRequest;
    private DatabaseService databaseService = new DatabaseService();
    private UserResponseModel userResponseModel;

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
    public void commentSuccessfullyLiked_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(),postResponseModel.getId());
        CommentRequest commentRequest = new CommentRequest();
        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel,cookie);
        int commentId = Integer.parseInt(commentResponseModel.getCommentId());
        CommentResponseModel updateCommentModel = commentRequest.likeComment(commentId, cookie);
        Assertions.assertNotNull(commentResponseModel.getLikes());
        Assertions.assertTrue(updateCommentModel.isLiked());
        commentRequest.deleteComment(commentResponseModel.getCommentId(),cookie);
        postRequests.deletePost(postResponseModel.getId(), cookie);
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void commentSuccessfullyDisliked_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(),postResponseModel.getId());
        CommentRequest commentRequest = new CommentRequest();
        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel,cookie);
        int commentId = Integer.parseInt(commentResponseModel.getCommentId());
        commentRequest.likeComment(commentId, cookie);
        CommentResponseModel updatedCommentModel = commentRequest.dislikeComment(commentId, cookie);
        Assertions.assertFalse(updatedCommentModel.isLiked());
        commentRequest.deleteComment(commentResponseModel.getCommentId(),cookie);
        postRequests.deletePost(postResponseModel.getId(), cookie);
        postRequests.deletePost(postResponseModel.getId(), cookie);
    }

    @Test
    public void commentEditSuccessfully_when_sendRequestWithValidBody() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(),postResponseModel.getId());
        CommentRequest commentRequest = new CommentRequest();
        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel,cookie);
        int commentId = Integer.parseInt(commentResponseModel.getCommentId());
        ResponseOptions responseOptions = commentRequest.editComment(commentId,cookie,commentRequestModel);

        assertOkStatusCode(responseOptions);
    }

    @Test
    public void commentSuccessfullyDeleted_when_sendRequestWithValidBody() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(),postResponseModel.getId());
        CommentRequest commentRequest = new CommentRequest();
        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel,cookie);
        ResponseOptions responseOptions = commentRequest.deleteComment(commentResponseModel.getCommentId(), cookie);
        assertOkStatusCode(responseOptions);
    }

    @Test
    public void statusCodeOk_when_sendAll() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        ResponseOptions responseOption = commentRequest.getAllComment(cookie);

        assertOkStatusCode(responseOption);
    }

}