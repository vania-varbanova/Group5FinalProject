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

    protected static final String INVALID_PASSWORD_ERROR_MESSAGE = "Your password is not confirmed";
    protected static final String INVALID_EMAIL_ERROR_MESSAGE = "this doesn't look like valid email";
    protected static final String EXISTING_USER_ERROR_MESSAGE = "User with this username already exist";
    protected static final String MAIN_PAGE_HEADING = "Welcome to our community.";
    protected static final String ABOUT_US_PAGE_HEADING = "About us";
    protected static final String EXPLORE_ALL_POSTS_PAGE_HEADING = "Explore all posts";
    protected static final String EXPLORE_POST_PAGE_HEADING = "Explore post";
    protected static final String CREATE_POST_PAGE_HEADING = "Create new post";
    protected static final String DELETE_POST_PAGE_HEADING = "Post deleted successfully";
    protected static final String DELETE_COMMENT_PAGE_HEADING = "Comment deleted successfully";


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