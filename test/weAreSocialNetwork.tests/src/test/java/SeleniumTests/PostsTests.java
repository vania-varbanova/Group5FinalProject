package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LatestPostsPage;
import pages.MainPage;
import pages.PostsPage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;

public class PostsTests extends BaseSystemTest {
    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private DatabaseService databaseService;
    private UserResponseModel userResponseModel;
    private UserRequestModel userRequestModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();

        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
    }
    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
    }
    @Test
    public void userCanViewCreatePostPageSuccessfully() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        PostsPage postsPage = new PostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
    }
    @Test
    public void userCanCreatePrivatePostSuccessfully() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        PostsPage postsPage = new PostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePrivatePost();
    }
    @Test
    public void userCanCreatePublicPostSuccessfully() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        PostsPage postsPage = new PostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
    }
    @Test
    public void userCanLikePostSuccessfully() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        PostsPage postsPage = new PostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.likePost();
    }
}
