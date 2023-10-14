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
        userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
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
        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
    }

    @Test
    @Tag("System")
    @Tag("Authentication process")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-18")
    public void userSuccessfullyLogout_when_clickLogoutButton() throws InterruptedException {
        mainPage.clickButtonByLinkText("LOGOUT");
        Thread.sleep(3000);
        loginPage.assertMainHeadingIsVisible();
    }
}
