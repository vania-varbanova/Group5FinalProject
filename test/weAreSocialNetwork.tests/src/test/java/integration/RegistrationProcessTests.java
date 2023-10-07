package integration;

import models.api.request.AuthenticateRequests;
import models.api.request.CommentRequestModel;
import models.api.request.PostRequests;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.devtools.v85.database.Database;
import services.DatabaseService;
import utils.ApiDataGenerator;
import utils.IssueKey;

import javax.xml.crypto.Data;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationProcessTests extends ApiBaseTest {
    @Test
    @Tag("Integration")
    @IssueKey(key = "WSFP-13")
    public void userSuccessfullyCreated_when_validInformation() throws SQLException {
        //Arrange
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        DatabaseService databaseService = new DatabaseService();
        //Act
        UserResponseModel userResponseModel = userRequests.createUser(userRequestModel);
        //Assert
        assertEquals(userRequestModel.getUsername(), userResponseModel.getName(), "Actual username is different from expected username");
        assertNotNull(userResponseModel.getId());
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void postSuccesfullyCreate() {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        AuthenticateRequests authenticateRequests = new AuthenticateRequests();
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        PostRequestModel postRequestModel = apiDataGenerator.createPublicPost();
        PostRequests postRequests = new PostRequests();
        PostResponseModel postResponseModel = postRequests.createPost(postRequestModel, cookie);
        Assertions.assertNotNull(postResponseModel.getId());
        Assertions.assertEquals(postRequestModel.getContent(), postResponseModel.getContent(), formatErrorMessage("post content"));
        Assertions.assertEquals(postRequestModel.isPublic(), postResponseModel.isPublic(), "Actual post visibility is different from expected post visibility");
    }

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
    }


    private String formatErrorMessage(String field) {
        return String.format("Actual %s is different from expected %s", field, field);
    }
}
