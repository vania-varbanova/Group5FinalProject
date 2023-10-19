package SeleniumTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
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
@Feature("Operations related to comments")
public class CommentTests extends BaseSystemTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();
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
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-58")
    @Description("As a registered user of the WEare social network, I want to be able to comment on my posts.")
    public void userCanCreateCommentUnderPostSuccessfully() {
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-62")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-62")
    @Description("As a registered user of the WEare social network, I want to be able to edit my comments")
    public void userCanEditCommentSuccessfully() {
        explorePostsPage.editComment();

        mainPage.assertPageHeadingEquals(EXPLORE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-63")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-63")
    @Description("As a registered user of the WEare social network, I want to see the comments on the posts and Like some of them.")
    public void userCanLikeCommentSuccessfully() {
        explorePostsPage.likeComment();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertCommentLiked"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedComment")
    @Issue(key = "WSFP-64")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-64")
    @Description("As a registered user of the WEare social network, I want to see the comments to the post and Dislike some of them.")
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
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-61")
    @Description("As a registered user of the WEare social network, I want to be able to delete my comments")
    public void userCanDeleteCommentSuccessfully() {
        explorePostsPage.deleteComment();

        mainPage.assertPageHeadingEquals(DELETE_COMMENT_PAGE_HEADING);
    }
}
