package integration;

import models.api.request.AuthenticateRequests;
import models.api.request.CommentRequest;
import models.api.request.PostRequests;
import models.api.request.UserRequests;
import models.api.requestModel.CommentRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.CommentResponseModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import services.DatabaseService;
import annotations.IssueKey;
import utils.ApiDataGenerator;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationProcessTests {


    @Test
    @Tag("Integration")
    @IssueKey(key = "WSFP-13")
    public void userSuccessfullyCreated_when_validInformation() throws SQLException {
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        UserRequests userRequests = new UserRequests();
        DatabaseService databaseService = new DatabaseService();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        //Act
        UserResponseModel userResponseModel = userRequests.createUser(userRequestModel);
        //Assert
        assertEquals(userRequestModel.getUsername(), userResponseModel.getName(), "Actual username is different from expected username");
        assertNotNull(userResponseModel.getId());
        databaseService.deleteUserWithId(userResponseModel.getId());
    }





}
