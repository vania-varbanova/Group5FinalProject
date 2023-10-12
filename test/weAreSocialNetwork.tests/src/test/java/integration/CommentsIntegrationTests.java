package integration;

import annotations.IssueLink;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CommentsIntegrationTests extends BaseIntegrationTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        cookieValue = authenticateRequests.authenticateUser(userRequestModel);
        postRequestModel = apiDataGenerator.createPublicPost();
        postResponseModel = postRequests.createPost(postRequestModel, cookieValue);
        commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(), postResponseModel.getId());
        commentResponseModel = commentRequest.createComment(commentRequestModel, cookieValue);
    }

    @Override
    @AfterEach
    public void afterEach() {
        commentRequest.deleteComment(commentResponseModel.getId(), cookieValue);
        postRequests.deletePost(postResponseModel.getId(), cookieValue);
        super.afterEach();
    }

    @Test
    @Tag("Integration")
    @IssueLink(jiraLink = "")
    public void commentSuccessfullyCreated_when_serverReturnsStatusCode200() {
        assertNotNull(commentResponseModel.getId(), "");
        assertEquals(commentRequestModel.getContentComment(), commentResponseModel.getContent(), formatErrorMessage("comment content"));
        assertFalse(commentResponseModel.isLiked());
    }

//    @Test
//    public void commentSuccessfullyLiked_when_serverReturnsStatusCode200() {
//        var updateCommentModel = commentRequest.likeComment(commentResponseModel.getId(), cookieValue);
//
//        assertNotNull(commentResponseModel.getLikes());
//        assertTrue(updateCommentModel.isLiked());
//    }

//    @Test
//    public void commentSuccessfullyDisliked_when_sendRequestWithValidBody() throws SQLException {
//        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
//        userResponseModel = userRequests.createUser(userRequestModel);
//        String cookie = authenticateRequests.authenticateUser(userRequestModel);
//        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
//        PostRequests postRequests = new PostRequests();
//        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
//        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(), postResponseModel.getId());
//        CommentRequest commentRequest = new CommentRequest();
//        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel, cookie);
//        int commentId = Integer.parseInt(commentResponseModel.getId());
//        commentRequest.likeComment(commentId, cookie);
//        CommentResponseModel updatedCommentModel = commentRequest.dislikeComment(commentId, cookie);
//        assertFalse(updatedCommentModel.isLiked());
//        commentRequest.deleteComment(commentResponseModel.getId(), cookie);
//        postRequests.deletePost(postResponseModel.getId(), cookie);
//        postRequests.deletePost(postResponseModel.getId(), cookie);
//    }
//
//    @Test
//    public void commentEditSuccessfully_when_sendRequestWithValidBody() {
//        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
//        userRequests.createUser(userRequestModel);
//        String cookie = authenticateRequests.authenticateUser(userRequestModel);
//        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
//        PostRequests postRequests = new PostRequests();
//        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
//        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(), postResponseModel.getId());
//        CommentRequest commentRequest = new CommentRequest();
//        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel, cookie);
//        int commentId = Integer.parseInt(commentResponseModel.getId());
//        ResponseOptions responseOptions = commentRequest.editComment(commentId, cookie, commentRequestModel);
//
//        assertOkStatusCode(responseOptions);
//    }
//
//    @Test
//    public void commentSuccessfullyDeleted_when_sendRequestWithValidBody() {
//        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
//        userRequests.createUser(userRequestModel);
//        String cookie = authenticateRequests.authenticateUser(userRequestModel);
//        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
//        PostRequests postRequests = new PostRequests();
//        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
//        CommentRequestModel commentRequestModel = apiDataGenerator.createComment(userResponseModel.getId(), postResponseModel.getId());
//        CommentRequest commentRequest = new CommentRequest();
//        CommentResponseModel commentResponseModel = commentRequest.createComment(commentRequestModel, cookie);
//        ResponseOptions responseOptions = commentRequest.deleteComment(commentResponseModel.getId(), cookie);
//        assertOkStatusCode(responseOptions);
//    }
//
//    @Test
//    public void statusCodeOk_when_sendAll() {
//        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
//        userRequests.createUser(userRequestModel);
//        String cookie = authenticateRequests.authenticateUser(userRequestModel);
//        ResponseOptions responseOption = commentRequest.getAllComment(cookie);
//
//        assertOkStatusCode(responseOption);
//    }

}