package integration;


import models.api.request.UserRequests;
import org.junit.jupiter.api.BeforeEach;
import utils.ApiDataGenerator;

public class ApiBaseTest {
    protected UserRequests userRequests;
    protected ApiDataGenerator apiDataGenerator;

    @BeforeEach
    public void beforeEach(){
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
    }
}
