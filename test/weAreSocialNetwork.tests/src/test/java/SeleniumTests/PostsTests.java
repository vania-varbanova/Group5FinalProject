package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import services.DatabaseService;

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
        //databaseService.deleteUserWithId(userResponseModel.getId());
    }
    @Test
    public void userCanViewCreatePostPageSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-42")
    public void userCanCreatePrivatePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePrivatePost();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-40")
    public void userCanCreatePublicPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-67")
    public void userCanLikePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.likePost();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-45")
    public void userCanDeletePostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.deletePost();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-44")
    public void userCanEditPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.editPost();
    }
}
