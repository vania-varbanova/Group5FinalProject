package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    public void afterEach() {
        driver.quit();
    }
}