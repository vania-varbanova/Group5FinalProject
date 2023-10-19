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
import utils.ApiDataGenerator;
import utils.UiPropertiesReader;

@Feature("Operations related to posts")
public class PostsTests extends BaseSystemTest {

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
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
        actions.waitFor(500);
    }

    @Test
    public void userCanViewCreatePostPageSuccessfully() {
        mainPage.assertPageHeadingEquals(CREATE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-42")
    @Description("As a registered user of WEare social network, I want to publish a post with private visibility")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-42")
    public void userCanCreatePrivatePostSuccessfully() {
        postsPage.cretePrivatePost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertPrivatePostCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-40")
    @Description("As a registered user of WEare social network, I want to publish a post with public visibility.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-40")
    public void userCanCreatePublicPostSuccessfully() {
        postsPage.cretePublicPost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.assertPublicPostCreated"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-67")
    @Description("As a registered user of the WEare social network, I want to like the posts of other users")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-67")
    public void userCanLikePostSuccessfully() {
        postsPage.cretePublicPost();
        latestPostsPage.likePost();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.explorePostsPage.dislikeButton"));
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-68")
    @Description("As a registered user of the WEare social network, I want to dislike already-liked from my posts.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-68")
    public void userCanDislikePostSuccessfully() {
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
    @Description("As a registered user of WEare social network, I want to delete my post.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-45")
    public void userCanDeletePostSuccessfully() {
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.deletePost();

        mainPage.assertPageHeadingEquals(DELETE_POST_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-44")
    @Description("As a registered user of WEare social network, I want to delete my post.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-44")
    public void userCanEditPostSuccessfully() {
        postsPage.cretePublicPost();
        latestPostsPage.navigateToExplorePostPage();
        explorePostsPage.editPost();

        mainPage.assertPageHeadingEquals(EXPLORE_POST_PAGE_HEADING);
    }
}
