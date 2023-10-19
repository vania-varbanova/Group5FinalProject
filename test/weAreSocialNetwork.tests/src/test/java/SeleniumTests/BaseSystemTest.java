package SeleniumTests;

import models.api.request.UserRequests;
import models.api.responseModel.UserResponseModel;
import org.openqa.selenium.WebDriver;
import pages.*;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.BrowserType;
import utils.UiDataGenerator;

public class BaseSystemTest {
    protected final String INVALID_PASSWORD_ERROR_MESSAGE = "Your password is not confirmed";
    protected final String INVALID_EMAIL_ERROR_MESSAGE = "this doesn't look like valid email";
    protected final String EXISTING_USER_ERROR_MESSAGE = "User with this username already exist";
    protected final String MAIN_PAGE_HEADING = "Welcome to our community.";
    protected final String ABOUT_US_PAGE_HEADING = "About us";
    protected final String EXPLORE_ALL_POSTS_PAGE_HEADING = "Explore all posts";
    protected final String EXPLORE_POST_PAGE_HEADING = "Explore post";
    protected final String CREATE_POST_PAGE_HEADING = "Create new post";
    protected final String DELETE_POST_PAGE_HEADING = "Post deleted successfully";
    protected final String DELETE_COMMENT_PAGE_HEADING = "Comment deleted successfully";

    private WebDriver webDriver;
    protected UiDataGenerator uiDataGenerator;

    protected ApiDataGenerator apiDataGenerator;

    protected UserRequests userRequests;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected LatestPostsPage latestPostsPage;
    protected PostsPage postsPage;
    protected UserActions actions;
    protected AdminViewAllUsersPage adminViewAllUsersPage;
    protected RegistrationPage registrationPage;
    protected ExplorePostsPage explorePostsPage;
    protected PersonalProfilePage personalProfilePage;
    protected EditPersonalProfilePage editPersonalProfilePage;
    protected ConnectionPage connectionPage;
    protected DatabaseService databaseService;
    protected UserResponseModel userResponseModel;

    public void beforeEach() {
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver(BrowserType.EDGE);
        actions = new UserActions(webDriver);
        loginPage = new LoginPage(webDriver);
        mainPage = new MainPage(webDriver);
        registrationPage = new RegistrationPage(webDriver);
        postsPage = new PostsPage(webDriver);
        latestPostsPage = new LatestPostsPage(webDriver);
        adminViewAllUsersPage = new AdminViewAllUsersPage(webDriver);
        personalProfilePage = new PersonalProfilePage(webDriver);
        editPersonalProfilePage = new EditPersonalProfilePage(webDriver);
        connectionPage = new ConnectionPage(webDriver);
        explorePostsPage = new ExplorePostsPage(webDriver);
        databaseService = new DatabaseService();
        uiDataGenerator = new UiDataGenerator();
        apiDataGenerator = new ApiDataGenerator();
        userResponseModel = new UserResponseModel();
        userRequests = new UserRequests();

    }

    public void afterEach() {
        UserActions.quitDriver();
    }
}