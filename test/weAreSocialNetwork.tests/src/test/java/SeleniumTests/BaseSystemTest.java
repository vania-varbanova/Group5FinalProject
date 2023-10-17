package SeleniumTests;

import org.openqa.selenium.WebDriver;
import pages.*;
import testFramework.CustomWebDriverManager;
import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class BaseSystemTest {
    private WebDriver webDriver;

    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected LatestPostsPage latestPostsPage;
    protected PostsPage postsPage;
    protected UserActions actions;
    protected AdminViewAllUsersPage adminViewAllUsersPage;
    protected RegistrationPage registrationPage;
    protected UiDataGenerator uiDataGenerator;
    protected ApiDataGenerator apiDataGenerator;
    protected ExplorePostsPage explorePostsPage;
    protected PersonalProfilePage personalProfilePage;
    protected EditPersonalProfilePage editPersonalProfilePage;
    protected ConnectionPage connectionPage;
    protected SearchPage searchPage;

    public void beforeEach() {
        UserActions.loadBrowser("weAreSocialNetwork.baseUrl");
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        postsPage = new PostsPage(webDriver);
        latestPostsPage = new LatestPostsPage(webDriver);
        adminViewAllUsersPage=new AdminViewAllUsersPage(webDriver);
        personalProfilePage = new PersonalProfilePage(webDriver);
        editPersonalProfilePage = new EditPersonalProfilePage(webDriver);
        connectionPage=new ConnectionPage(webDriver);
        explorePostsPage = new ExplorePostsPage(webDriver);
        personalProfilePage = new PersonalProfilePage(webDriver);
        searchPage = new SearchPage(webDriver);

        uiDataGenerator = new UiDataGenerator();
        apiDataGenerator = new ApiDataGenerator();
    }

    public void afterEach() {
        UserActions.quitDriver();
    }
}