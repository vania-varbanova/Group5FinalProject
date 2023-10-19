package RESTAssuredTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Feature("Operations related to comments")
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
    @Issue(key = "WSFP-58")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-58")
    public void commentSuccessfullyCreated_when_serverReturnsStatusCode200() {
        assertNotNull(commentResponseModel.getId(), "");
        assertEquals(commentRequestModel.getContentComment(), commentResponseModel.getContent(), formatErrorMessage("comment content"));
        assertFalse(commentResponseModel.isLiked());
    }

    @Test
    @Issue(key = "WSFP-63")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-63")
    public void commentSuccessfullyLiked_when_serverReturnsStatusCode200() {
        var updateCommentModel = commentRequest.likeComment(commentResponseModel.getId(), cookieValue);

        assertNotNull(updateCommentModel.getLikes());
        assertTrue(updateCommentModel.isLiked());
    }

    @Test
    @Issue(key = "WSFP-64")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-64")
    public void commentSuccessfullyDisliked_when_serverReturnsStatusCode200() {
        commentRequest.likeComment(commentResponseModel.getId(), cookieValue);
        var updatedCommentModel = commentRequest.dislikeComment(commentResponseModel.getId(), cookieValue);

        assertFalse(updatedCommentModel.isLiked());
    }

    @Test
    @Issue(key = "WSFP-62")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-62")
    public void commentEditedSuccessfully_when_serverReturnsStatusCode200() {
        var responseOptions = commentRequest.editComment(commentResponseModel.getId(), cookieValue, commentRequestModel);

        assertOkStatusCode(responseOptions);
    }

    @Test
    @Issue(key = "WSFP-61")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-62")
    public void commentSuccessfullyDeleted_when_sendRequestWithValidBody() {
        ResponseOptions responseOptions = commentRequest.deleteComment(commentResponseModel.getId(), cookieValue);

        assertOkStatusCode(responseOptions);
    }
}