package RESTAssuredTests;

import models.api.request.ProfileManagementRequest;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.ProfileManagementResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.DatabaseService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileManagementTests extends BaseIntegrationTest {
    private UserResponseModel userResponseModel;
    private DatabaseService databaseService = new DatabaseService();
    @Test
    public void userUpdateSuccessfullyCreated_when_sendRequestWithValidBody() throws SQLException {
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        String cookie = authenticateRequests.authenticateUser(userRequestModel);
        ProfileManagementRequestModel profileManagementRequestModel = apiDataGenerator.createProfile(true);
        ProfileManagementRequest profileManagementRequest = new ProfileManagementRequest();
        ProfileManagementResponseModel profileManagementResponseModel = profileManagementRequest.updateUser(userResponseModel.getId(), cookie, profileManagementRequestModel);
        System.out.println(profileManagementResponseModel);
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = currentDate.format(formatter);

        Assertions.assertEquals(profileManagementRequestModel.getBirthYear(), profileManagementResponseModel.getBirthYear(), formatErrorMessage("Birth Year"));
        Assertions.assertEquals(profileManagementRequestModel.getFirstName(), profileManagementResponseModel.getFirstName(), formatErrorMessage("first name"));
        Assertions.assertEquals(profileManagementRequestModel.getLastName(), profileManagementResponseModel.getLastName(), formatErrorMessage("last name"));
        Assertions.assertEquals(profileManagementRequestModel.getGender(), profileManagementResponseModel.getGender(), formatErrorMessage("gender"));
        Assertions.assertEquals(formattedString, profileManagementResponseModel.getMemberSince(), "date since");
        Assertions.assertNotNull(profileManagementResponseModel.getLocation().getLocationId());
        //databaseService.deleteUserWithId(userResponseModel.getId());
    }
}
