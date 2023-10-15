package SeleniumTests;

import org.openqa.selenium.WebDriver;
import pages.*;
import testFramework.CustomWebDriverManager;
import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class BaseSystemTest {
    private WebDriver webDriver;
    ;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected LatestPostsPage latestPostsPage;
    protected PostsPage postsPage;
    protected UserActions actions;
    protected RegistrationPage registrationPage;
    protected UiDataGenerator uiDataGenerator;
    protected ApiDataGenerator apiDataGenerator;

    public void beforeEach() {
        UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        postsPage = new PostsPage(webDriver);
        latestPostsPage = new LatestPostsPage(webDriver);

        uiDataGenerator = new UiDataGenerator();
        apiDataGenerator = new ApiDataGenerator();
    }

    public void afterEach() {
        UserActions.quitDriver();
    }
}