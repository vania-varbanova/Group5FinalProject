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
    ;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected UserActions actions;

    public void beforeEach() {
        UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
    }

    public void afterEach() {
        UserActions.quitDriver();
    }
}