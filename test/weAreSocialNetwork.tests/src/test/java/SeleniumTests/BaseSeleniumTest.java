package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.LoginPage;
import pages.MainPage;
import testFramework.CustomWebDriverManager;
import testFramework.UserActions;
import utils.ApiDataGenerator;

import static testFramework.UserActions.quitDriver;

public class BaseSeleniumTest {
    @BeforeEach
    public void setUp() {
        UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
    }

    @AfterEach
    public void tearDown() {
        quitDriver();
    }
    public void RegisterAndLogin() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        UserRequests userRequests = new UserRequests();
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
        LoginPage loginPage = new LoginPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(),userRequestModel.getPassword());
        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
    }
}
