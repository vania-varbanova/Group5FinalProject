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
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-58")
    public void userCanCreateCommentUnderPostSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-62")
    public void userCanEditCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.editComment();

        mainPage.assertPageHeadingEquals(EXPLORE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-63")
    public void userCanLikeCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.likeComment();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentLiked"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-64")
    public void userCanDislikeCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.likeComment();
        explorePostsPage.dislikeComment();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentDisliked"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-61")
    public void userCanDeleteCommentSuccessfully() {
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
        explorePostsPage.deleteComment();

        mainPage.assertPageHeadingEquals(DELETE_COMMENT_PAGE_HEADING);
    }
}
