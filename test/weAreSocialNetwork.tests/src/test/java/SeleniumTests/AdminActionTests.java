package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.AdminUserUiModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EditPersonalProfilePage;
import pages.MainPage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;


public class AdminActionTests extends BaseSystemTest {
    private static final String PAGE_HEADING = "Welcome to our community.";
    private AdminUserUiModel adminUserUiModel;
    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private UiDataGenerator uiDataGenerator;
    private DatabaseService databaseService;
    private UserResponseModel userResponseModel;
    private UserRequestModel userRequestModel;
    private MainPage mainPage;

    private EditPersonalProfilePage editPersonalProfilePage;
    private PersonalProfileUiModel personalProfileUiModel;

    private UserRequestModel adminUserRequestModel;



    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        uiDataGenerator = new UiDataGenerator();
        apiDataGenerator=new ApiDataGenerator();
        adminUserUiModel = uiDataGenerator.createAdminUser();
        UserRequests userRequests = new UserRequests();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);


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
    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-75")
    public void adminSuccessfullyEditProfessionUser() throws InterruptedException {
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
