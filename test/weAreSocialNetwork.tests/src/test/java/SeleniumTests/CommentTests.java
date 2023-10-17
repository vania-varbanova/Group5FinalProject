package SeleniumTests;

import annotations.Issue;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import services.DatabaseService;
import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.UiPropertiesReader;

public class CommentTests extends BaseSystemTest {

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();
        actions = new UserActions();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        mainPage.navigateToPage();
        postsPage.navigateToCretePostsPage();
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.createComment();
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
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-62")
    public void userCanEditCommentSuccessfully() {
        explorePostsPage.editComment();

        mainPage.assertPageHeadingEquals(EXPLORE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-63")
    public void userCanLikeCommentSuccessfully() {
        explorePostsPage.likeComment();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentLiked"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-64")
    public void userCanDislikeCommentSuccessfully() {
        explorePostsPage.likeComment();
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentLiked"));

        explorePostsPage.dislikeComment();
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentDisliked"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-61")
    public void userCanDeleteCommentSuccessfully() {
        explorePostsPage.deleteComment();

        mainPage.assertPageHeadingEquals(DELETE_COMMENT_PAGE_HEADING);
    }
}
