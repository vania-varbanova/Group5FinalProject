package SeleniumTests;

import annotations.Issue;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import services.DatabaseService;

import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.UiPropertiesReader;


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
        actions = new UserActions();
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

        mainPage.assertPageHeadingEquals(CREATE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-42")
    public void userCanCreatePrivatePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePrivatePost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertPrivatePostCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-40")
    public void userCanCreatePublicPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertPublicPostCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-67")
    public void userCanLikePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.likePost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.dislikeButton"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-68")
    public void userCanDislikePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.likePost();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.dislikeButton"));
        latestPostsPage.dislikePost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.latestPostsPage.likeButton"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-45")
    public void userCanDeletePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.deletePost();

        mainPage.assertPageHeadingEquals(DELETE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-44")
    public void userCanEditPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.editPost();

        mainPage.assertPageHeadingEquals(EXPLORE_POST_PAGE_HEADING);
    }
}
