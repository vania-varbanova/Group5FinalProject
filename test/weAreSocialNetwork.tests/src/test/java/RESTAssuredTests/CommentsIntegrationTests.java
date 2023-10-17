package RESTAssuredTests;

import annotations.Issue;
import io.restassured.response.ResponseOptions;
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
    @Tag("OperationsToCommentPost")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-58")
    public void commentSuccessfullyCreated_when_serverReturnsStatusCode200() {
        assertNotNull(commentResponseModel.getId(), "");
        assertEquals(commentRequestModel.getContentComment(), commentResponseModel.getContent(), formatErrorMessage("comment content"));
        assertFalse(commentResponseModel.isLiked());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToCommentPost")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-63")
    public void commentSuccessfullyLiked_when_serverReturnsStatusCode200() {
        var updateCommentModel = commentRequest.likeComment(commentResponseModel.getId(), cookieValue);

        assertNotNull(updateCommentModel.getLikes());
        assertTrue(updateCommentModel.isLiked());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToCommentPost")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-64")
    public void commentSuccessfullyDisliked_when_serverReturnsStatusCode200() {
        commentRequest.likeComment(commentResponseModel.getId(), cookieValue);
        var updatedCommentModel = commentRequest.dislikeComment(commentResponseModel.getId(), cookieValue);

        assertFalse(updatedCommentModel.isLiked());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToCommentPost")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-62")
    public void commentEditedSuccessfully_when_serverReturnsStatusCode200() {
        var responseOptions = commentRequest.editComment(commentResponseModel.getId(), cookieValue, commentRequestModel);

        assertOkStatusCode(responseOptions);
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToCommentPost")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-61")
    public void commentSuccessfullyDeleted_when_sendRequestWithValidBody() {
        ResponseOptions responseOptions = commentRequest.deleteComment(commentResponseModel.getId(), cookieValue);

        assertOkStatusCode(responseOptions);
    }
}