package SeleniumTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.*;
import testFramework.UserActions;

@Feature("Operations related to admin actions")
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
    @Issue(key = "WSFP-80")
    @Description("This test case verifies that the admin user can indeed disable the profile of an existing registered user in the system.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-80")
    public void adminSuccessfullyDisableUser() {
        personalProfilePage.clickDisableButton();

        personalProfilePage.assertButtonEnableIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "WSFP-81")
    @Description("This test case verifies that the admin user can indeed enable the profile of an existing registered user in the system, that has been disabled.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-81")
    public void adminSuccessfullyEnableUser() {
        personalProfilePage.clickDisableButton();

        personalProfilePage.clickEnableButton();

        personalProfilePage.assertButtonDisableIsVisible();
    }

    @Test
    @Issue(key = "WSFP-70")
    @Description("This test case verifies that the admin user can indeed change the last name of an existing registered user in the system.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-70")
    public void adminSuccessfullyEditNameUser() throws InterruptedException {
        personalProfileInformation = uiDataGenerator.createPersonalProfile(true);
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());
        personalProfilePage.clickEditProfileButton();

        editPersonalProfilePage.updateProfessionalInformation(personalProfileInformation);
        personalProfilePage.navigateToPersonalProfilePage();

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
    }
}
