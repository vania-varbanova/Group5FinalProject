package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.ui.AdminUserUiModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EditPersonalProfilePage;
import testFramework.CustomWebDriverManager;
import utils.UiDataGenerator;


public class AdminActionTests extends BaseSystemTest {
    private static final String PAGE_HEADING = "Welcome to our community.";
    AdminUserUiModel adminUserUiModel;


    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        uiDataGenerator = new UiDataGenerator();
        adminUserUiModel = uiDataGenerator.createAdminUser();

    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-151")
    public void userSuccessfullyRegister_when_validCredentials() throws InterruptedException {
        registrationPage.navigateToPage();
        registrationPage.enterAdminRegistrationCredentials(adminUserUiModel);
        Thread.sleep(3000);
        mainPage.assertPageHeadingEquals(PAGE_HEADING);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserUiModel.getUsername(), adminUserUiModel.getPassword());

        mainPage.assertAdminZoneButtonIsVisible();

    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-80")
    public void adminSuccessfullyDisableUser() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonByDisable().click();
        personalProfilePage.assertButtonEnableIsVisible();


    }
    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-81")
    public void adminSuccessfullyEnableUser() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonByDisable().click();
        personalProfilePage.buttonByEnable().click();
        personalProfilePage.assertButtonDisableIsVisible();
    }
    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-70")
    public void adminSuccessfullyEditNameUser() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.editProfileButton().click();
        editPersonalProfilePage = new EditPersonalProfilePage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        editPersonalProfilePage.waitForPageToLoad();
        var personalProfileModel = uiDataGenerator.createPersonalProfile(true);
        editPersonalProfilePage.updateProfessionalInformation(personalProfileModel);
        personalProfilePage.navigateToPersonalProfilePage();
        String expectedResult = String.format("%s %s", personalProfileModel.getFirstName(), personalProfileModel.getLastName());
        personalProfilePage.assertColumnValueEquals("Name", expectedResult);
    }



    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
//        databaseService.deleteUserWithId(userResponseModel.getId());
    }


}
