package RESTAssuredTests;


import io.restassured.response.ResponseOptions;
import models.api.request.AuthenticateRequests;
import models.api.request.UserRequests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import utils.ApiDataGenerator;

public class BaseIntegrationTest {
    protected UserRequests userRequests;
    protected ApiDataGenerator apiDataGenerator;
    protected AuthenticateRequests authenticateRequests;

    @BeforeEach
    public void beforeEach() {
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        authenticateRequests = new AuthenticateRequests();
    }
    protected String formatErrorMessage(String field) {
        return String.format("Actual %s is different from expected %s", field, field);
    }

    protected void assertOkStatusCode(ResponseOptions responseOptions){
        Assertions.assertEquals(200, responseOptions.getStatusCode());
    }
}
