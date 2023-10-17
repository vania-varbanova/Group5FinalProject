package RESTAssuredTests;

import annotations.IssueLink;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileManagementIntegrationTests extends BaseIntegrationTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        cookieValue = authenticateRequests.authenticateUser(userRequestModel);
        profileManagementRequestModel = apiDataGenerator.updateProfile(true);
    }

    @Test
    @Tag("Integration")
    @Tag("ProfileManagementAction")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-171")
    public void userUpdateSuccessfullyCreated_when_sendRequestWithValidData() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedString = currentDate.format(formatter);
        profileManagementResponseModel = profileManagementRequest.updateUser(userResponseModel.getId(), cookieValue, profileManagementRequestModel);
        Assertions.assertEquals(profileManagementRequestModel.getBirthYear(), profileManagementResponseModel.getBirthYear(), formatErrorMessage("Birth Year"));
        Assertions.assertEquals(profileManagementRequestModel.getFirstName(), profileManagementResponseModel.getFirstName(), formatErrorMessage("first name"));
        Assertions.assertEquals(profileManagementRequestModel.getLastName(), profileManagementResponseModel.getLastName(), formatErrorMessage("last name"));
        Assertions.assertEquals(profileManagementRequestModel.getGender(), profileManagementResponseModel.getGender(), formatErrorMessage("gender"));
        Assertions.assertEquals(formattedString, profileManagementResponseModel.getMemberSince(), "date since");
        Assertions.assertNotNull(profileManagementResponseModel.getLocation().getLocationId());
    }
    @Test
    @Tag("Integration")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-23")
    public void userSuccessfullyEditBirthday() {
        profileManagementResponseModel = profileManagementRequest.updateUser(userResponseModel.getId(), cookieValue, profileManagementRequestModel);
        Assertions.assertEquals(profileManagementRequestModel.getBirthYear(), profileManagementResponseModel.getBirthYear(), formatErrorMessage("Birth Year"));
    }
    @Test
    @Tag("Integration")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-19")
    public void userSuccessfullyEditName() {
        profileManagementResponseModel = profileManagementRequest.updateUser(userResponseModel.getId(), cookieValue, profileManagementRequestModel);

        Assertions.assertEquals(profileManagementRequestModel.getFirstName(), profileManagementResponseModel.getFirstName(), formatErrorMessage("first name"));
        Assertions.assertEquals(profileManagementRequestModel.getLastName(), profileManagementResponseModel.getLastName(), formatErrorMessage("last name"));
    }

}
