package RESTAssuredTests;

import io.restassured.response.ResponseOptions;
import models.api.request.PostRequests;
import models.api.requestModel.EditPostRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.DatabaseService;

import java.sql.SQLException;

public class PostIntegrationTests extends BaseIntegrationTest {
    private PostRequests postRequests;
    private DatabaseService databaseService = new DatabaseService();
    private UserResponseModel userResponseModel;


    @Test
    public void postSuccessfullyCreated_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        Assertions.assertNotNull(postResponseModel.getId());
        Assertions.assertEquals(postRequestModel.getContent(), postResponseModel.getContent(), formatErrorMessage("post content"));
        Assertions.assertEquals(postRequestModel.isPublic(), postResponseModel.isPublic(), formatErrorMessage("post visibility"));
        postRequests.deletePost(postResponseModel.getId(), cookie);
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void postSuccessfullyLiked_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        int postId = Integer.parseInt(postResponseModel.getId());
        PostResponseModel updatedPostModel = postRequests.likePost(postId, cookie);
        Assertions.assertNotNull(postResponseModel.getLikes());
        Assertions.assertTrue(updatedPostModel.isLiked());
        postRequests.deletePost(postResponseModel.getId(), cookie);
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void postSuccessfullyDisliked_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        int postId = Integer.parseInt(postResponseModel.getId());
        postRequests.likePost(postId, cookie);
        PostResponseModel updatedPostModel = postRequests.dislikePost(postId, cookie);
        Assertions.assertFalse(updatedPostModel.isLiked());

        postRequests.deletePost(postResponseModel.getId(), cookie);
    }

    @Test
    public void postEditSuccessfully_when_sendRequestWithValidBody() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        int postId = Integer.parseInt(postResponseModel.getId());
        EditPostRequestModel editPostRequestModel = apiDataGenerator.editPost(true);
        ResponseOptions responseOptions = postRequests.editPost(postId, cookie, editPostRequestModel);

        assertOkStatusCode(responseOptions);

    }

    @Test
    public void postSuccessfullyDeleted_when_sendRequestWithValidBody() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        ResponseOptions responseOptions = postRequests.deletePost(postResponseModel.getId(), cookie);
        assertOkStatusCode(responseOptions);
    }

    @Test
    public void statusCodeOk_when_sendAll() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        ResponseOptions responseOption = postRequests.GetAllPost(cookie);

        assertOkStatusCode(responseOption);
    }
}

