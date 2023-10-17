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

public class CommentTests extends BaseSystemTest {
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
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-58")
    public void userCanCreateCommentUnderPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-62")
    public void userCanEditCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.editComment();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-63")
    public void userCanLikeCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.likeComment();
    }
    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-61")
    public void userCanDeleteCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.deleteComment();
    }
}
