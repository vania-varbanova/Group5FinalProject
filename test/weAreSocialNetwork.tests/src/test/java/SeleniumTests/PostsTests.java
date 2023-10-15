package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ExplorePostsPage;
import pages.LatestPostsPage;
import pages.MainPage;
import pages.PostsPage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import models.api.responseModel.PostResponseModel;

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
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
    }
    @Test
    public void userCanCreatePrivatePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePrivatePost();
    }
    @Test
    public void userCanCreatePublicPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
    }
    @Test
    public void userCanLikePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.likePost();
    }
    @Test
    public void userCanDeletePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.deletePost();
    }
    @Test
    public void userCanEditPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.editPost();
    }
}
