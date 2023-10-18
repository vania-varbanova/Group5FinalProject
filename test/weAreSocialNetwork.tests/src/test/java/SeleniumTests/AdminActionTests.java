package SeleniumTests;

import annotations.Issue;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testFramework.UserActions;


public class AdminActionTests extends BaseSystemTest {
    private UserRequestModel admin;
    private UserRequestModel user;
    private PersonalProfileUiModel personalProfileInformation;
    private UserResponseModel adminResponse;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        user = apiDataGenerator.createUserWithRoleUser();
        admin = apiDataGenerator.createUserWithRoleAdmin();
        userResponseModel = userRequests.createUser(user);
        adminResponse = userRequests.createUser(admin);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(admin.getUsername(), admin.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.clickSeeProfileButtonByUsername(user.getUsername());
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.beforeEach();
        UserActions.quitDriver();
        databaseService.deleteUserWithId(adminResponse.getId());
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "WSFP-80")
    public void adminSuccessfullyDisableUser() {
        personalProfilePage.clickDisableButton();

        personalProfilePage.assertButtonEnableIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "WSFP-81")
    public void adminSuccessfullyEnableUser() {
        personalProfilePage.clickDisableButton();

        personalProfilePage.clickEnableButton();

        personalProfilePage.assertButtonDisableIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "WSFP-70")
    public void adminSuccessfullyEditNameUser() throws InterruptedException {
        personalProfileInformation = uiDataGenerator.createPersonalProfile(true);
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());
        personalProfilePage.clickEditProfileButton();

        editPersonalProfilePage.updateProfessionalInformation(personalProfileInformation);
        personalProfilePage.navigateToPersonalProfilePage();

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
    }
}
