package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class LoginTests extends BaseSystemTest {
    private static final String INVALID_DETAILS_ERROR_MESSAGE = "Wrong username or password.";
    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private DatabaseService databaseService;

    private UserRequestModel userRequestModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        var userRequestModel1 = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel1);
        userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();

    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
//        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    @Tag("System")
    @Tag("Authentication process")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-17")
    public void userSuccessfullyLogin_when_enterValidCredentials() {
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());

        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
    }

    @Test
    @Tag("System")
    @Tag("Authentication process")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-18")
    public void userSuccessfullyLogout_when_clickLogoutButton() throws InterruptedException {
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());

        mainPage.clickButtonByLinkText("LOGOUT");
        Thread.sleep(3000);
        loginPage.assertPageHeadingEquals("Login Page");
    }

    @Test
    @Tag("System")
    @Tag("Authentication process")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-34")
    public void errorMessageDisplayed_when_enterInvalidPassword() {
        userRequestModel.setPassword(userRequestModel.getPassword() + "qwe");

        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());

        loginPage.assertErrorMessageEquals(INVALID_DETAILS_ERROR_MESSAGE);
    }
}
