package system;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.Test;
import utils.ApiDataGenerator;

public class RegistrationProcessTests extends ApiBaseTest {
    @Test
    public void userSuccessfullyRegistered_when_validInformation() {
        UserRequestModel userRequestModel = ApiDataGenerator.createUser(false);
        UserRequests userRequests1 = new UserRequests();
        userRequests1.createUser(userRequestModel);
    }
}
