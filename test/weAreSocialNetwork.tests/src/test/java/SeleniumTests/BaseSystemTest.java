package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import testFramework.CustomWebDriverManager;
import testFramework.Driver;
import testFramework.UserActions;
import utils.ApiDataGenerator;

public class BaseSystemTest {
    private WebDriver webDriver;
    private Driver driver;
    protected LoginPage loginPage;
    protected MainPage mainPage;

    public void beforeEach() {
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        driver = new Driver(webDriver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        //UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
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