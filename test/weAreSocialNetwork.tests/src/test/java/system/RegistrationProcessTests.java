package system;

import com.google.gson.Gson;
import models.api.requestModel.UserRequestModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.Test;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class RegistrationProcessTests extends ApiBaseTest{

    @Test
    public void userSuccessfullyRegistered_when_validInformation(){
        // Arrange
        UserRequestModel userRequestModel = ApiDataGenerator.createUser(false);
        String serialized = gson.toJson(userRequestModel);

        // Act - send request and parsing the response

        //Assert - validating the response

        System.out.println();
    }
}
