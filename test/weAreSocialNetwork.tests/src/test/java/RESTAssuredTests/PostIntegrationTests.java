package RESTAssuredTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.EditPostRequestModel;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class PostIntegrationTests extends BaseIntegrationTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        cookieValue = authenticateRequests.authenticateUser(userRequestModel);
        postRequestModel = apiDataGenerator.createPublicPost();
        postResponseModel = postRequests.createPost(postRequestModel, cookieValue);
    }

    @Override
    @AfterEach
    public void afterEach() {
        postRequests.deletePost(postResponseModel.getId(), cookieValue);
        super.afterEach();
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToPost")
    @Issue(key = "WSFP-40")
    @Description("As a registered user of WEare social network, I want to publish a post with public visibility.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-40")
    public void postSuccessfullyCreated_when_sendRequestWithValidBody() {
        assertNotNull(postResponseModel.getId());
        assertEquals(postRequestModel.getContent(), postResponseModel.getContent(), formatErrorMessage("post content"));
        assertEquals(postRequestModel.isPublic(), postResponseModel.isPublic(), formatErrorMessage("post visibility"));
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToPost")
    @Issue(key = "WSFP-67")
    @Description("As a registered user of the WEare social network, I want to like the posts of other users")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-67")
    public void postSuccessfullyLiked_when_sendRequestWithValidBody() {
        var updatedPostModel = postRequests.likePost(postResponseModel.getId(), cookieValue);

        assertNotNull(postResponseModel.getLikes());
        assertTrue(updatedPostModel.isLiked());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToPost")
    @Issue(key = "WSFP-68")
    @Description("As a registered user of the WEare social network, I want to dislike already-liked from my posts.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-68")
    public void postSuccessfullyDisliked_when_sendRequestWithValidData() {
        postRequests.likePost(postResponseModel.getId(), cookieValue);
        var updatedPostModel = postRequests.dislikePost(postResponseModel.getId(), cookieValue);

        assertFalse(updatedPostModel.isLiked());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToPost")
    @Issue(key = "WSFP-44")
    @Description("As a registered user of WEare social network, I want to edit my post.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-44")
    public void postEditSuccessfully_when_sendRequestWithValidData() {
        EditPostRequestModel editPostRequestModel = apiDataGenerator.editPost(true);
        ResponseOptions responseOptions = postRequests.editPost(postResponseModel.getId(), cookieValue, editPostRequestModel);

        assertOkStatusCode(responseOptions);
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToPost")
    @Issue(key = "WSFP-45")
    @Description("As a registered user of WEare social network, I want to delete my post.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-45")
    public void postSuccessfullyDeleted_when_sendRequestWithValidData() {
        var responseOptions = postRequests.deletePost(postResponseModel.getId(), cookieValue);

        assertOkStatusCode(responseOptions);
    }
}
