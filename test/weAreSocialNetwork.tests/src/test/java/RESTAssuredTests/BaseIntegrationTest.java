package RESTAssuredTests;

import io.qameta.allure.Step;
import io.restassured.response.ResponseOptions;
import lombok.SneakyThrows;
import models.api.request.*;
import models.api.requestModel.*;
import models.api.responseModel.*;
import org.junit.jupiter.api.Assertions;
import services.DatabaseService;
import utils.ApiDataGenerator;

public class BaseIntegrationTest {
    protected ApiDataGenerator apiDataGenerator;
    protected DatabaseService databaseService;
    protected String cookieValue;

    protected UserRequests userRequests;
    protected AuthenticateRequests authenticateRequests;
    protected ProfileManagementRequest profileManagementRequest;
    protected PostRequests postRequests;
    protected CommentRequest commentRequest;
    protected ConnectionRequests connectionRequests;
    protected SkillsRequests skillsRequests;
    protected UserRequestModel userRequestModel;
    protected ProfileManagementRequestModel profileManagementRequestModel;
    protected PostRequestModel postRequestModel;
    protected CommentRequestModel commentRequestModel;
    protected SkillsRequestModel skillsRequestModel;
    protected UserResponseModel userResponseModel;
    protected ProfileManagementResponseModel profileManagementResponseModel;
    protected PostResponseModel postResponseModel;
    protected CommentResponseModel commentResponseModel;
    protected SkillsResponseModel skillsResponseModel;

    @SneakyThrows
    @Step("Initializing required data")
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
        skillsRequests = new SkillsRequests();
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
