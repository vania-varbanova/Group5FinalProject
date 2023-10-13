package RESTAssuredTests;


import io.restassured.response.ResponseOptions;
import lombok.SneakyThrows;
import models.api.request.*;
import models.api.requestModel.CommentRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.CommentResponseModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.ProfileManagementResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import services.DatabaseService;
import utils.ApiDataGenerator;

public class BaseIntegrationTest {
    protected ApiDataGenerator apiDataGenerator;
    protected DatabaseService databaseService;
    protected String cookieValue;
    //Request
    protected UserRequests userRequests;
    protected AuthenticateRequests authenticateRequests;
    protected ProfileManagementRequest profileManagementRequest;
    protected PostRequests postRequests;
    protected CommentRequest commentRequest;
    protected ConnectionRequests connectionRequests;
    //End of Requests


    //Request model
    protected UserRequestModel userRequestModel;
    protected ProfileManagementRequestModel profileManagementRequestModel;
    protected PostRequestModel postRequestModel;
    protected CommentRequestModel commentRequestModel;

    // End of request models

    //Response models
    protected UserResponseModel userResponseModel;
    protected ProfileManagementResponseModel profileManagementResponseModel;
    protected PostResponseModel postResponseModel;
    protected CommentResponseModel commentResponseModel;

    //End of response models

    @SneakyThrows
    public void beforeEach() {
        Thread.sleep(3000);
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();
        userRequests = new UserRequests();
        authenticateRequests = new AuthenticateRequests();
        postRequests = new PostRequests();
        commentRequest = new CommentRequest();
        profileManagementRequest = new ProfileManagementRequest();
        connectionRequests = new ConnectionRequests();
    }

    @SneakyThrows
    public void afterEach() {
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    protected String formatErrorMessage(String field) {
        return String.format("Actual %s is different from expected %s", field, field);
    }

    protected void assertOkStatusCode(ResponseOptions responseOptions) {
        Assertions.assertEquals(200, responseOptions.getStatusCode());
    }
}
