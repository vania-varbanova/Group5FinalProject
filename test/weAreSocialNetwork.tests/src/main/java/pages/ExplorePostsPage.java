package pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import utils.ConfigPropertiesReader;

public class ExplorePostsPage extends BasePage {
    private Faker faker = new Faker();
    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }

    public ExplorePostsPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }
    public void deletePost() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.deletePostButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.deletePostButton");

        actions.scrollUntilElementVisible("weAreSocialNetwork.deletePostPage.deletePostSelection");
        actions.selectOptionFromDropdown("weAreSocialNetwork.deletePostPage.deletePostSelection", "Delete post");

        actions.waitForElementVisible("weAreSocialNetwork.deletePostPage.submitPostButton");
        actions.clickElementWithJavaScript("weAreSocialNetwork.deletePostPage.submitPostButton");
        actions.waitFor(1000);
    }
    public void editPost() {
        String content = (faker.lorem().characters(10, 20));
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.editPostButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.editPostButton");

        waitForPageToLoad();
        actions.scrollUntilElementVisible("weAreSocialNetwork.createPostPage.postContentField");
        actions.selectOptionFromDropdown("weAreSocialNetwork.createPostPage.postVisibilityButton", "Public post");

        actions.waitForElementVisible("weAreSocialNetwork.createPostPage.postContentField");
        actions.typeValueInField(content, "weAreSocialNetwork.createPostPage.postContentField");

        actions.waitForElementVisible("weAreSocialNetwork.createPostPage.savePostButton");
        actions.clickElement("weAreSocialNetwork.createPostPage.savePostButton");
        actions.waitFor(1000);
    }
    public void createComment() {
        String content = (faker.lorem().characters(10, 20));

        waitForPageToLoad();
        actions.scrollUntilElementVisible("weAreSocialNetwork.explorePostsPage.createCommentField");
        actions.typeValueInField(content, "weAreSocialNetwork.explorePostsPage.createCommentField");

        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.createCommentButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.createCommentButton");
        actions.waitFor(1000);
    }
    private void showComments() {
        waitForPageToLoad();
        actions.scrollUntilElementVisible("weAreSocialNetwork.explorePostsPage.showCommentsButton");
        actions.clickElementWithJavaScript("weAreSocialNetwork.explorePostsPage.showCommentsButton");
        actions.waitFor(1000);
    }
    public void editComment() {
        String content = (faker.lorem().characters(10, 20));

        showComments();
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.editCommentButton");
        actions.waitForElementClickable("weAreSocialNetwork.explorePostsPage.editCommentButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.editCommentButton");

        actions.scrollUntilElementVisible("weAreSocialNetwork.editCommentPage.editCommentField");
        actions.typeValueInField(content, "weAreSocialNetwork.editCommentPage.editCommentField");

        actions.waitForElementVisible("weAreSocialNetwork.editCommentPage.editCommentButton");
        actions.clickElement("weAreSocialNetwork.editCommentPage.editCommentButton");
        actions.waitFor(1000);
    }
    public void deleteComment() {
        showComments();
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.deleteCommentButton");
        actions.waitForElementClickable("weAreSocialNetwork.explorePostsPage.deleteCommentButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.deleteCommentButton");

        actions.scrollUntilElementVisible("weAreSocialNetwork.deleteCommentPage.deleteCommentSelection");
        actions.selectOptionFromDropdown("weAreSocialNetwork.deleteCommentPage.deleteCommentSelection", "Delete comment");

        actions.waitForElementVisible("weAreSocialNetwork.deleteCommentPage.deleteCommentButton");
        actions.clickElementWithJavaScript("weAreSocialNetwork.deleteCommentPage.deleteCommentButton");
        actions.waitFor(1000);
    }
    public void likeComment() {
        showComments();
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.likeCommentButton");
        actions.waitForElementClickable("weAreSocialNetwork.explorePostsPage.likeCommentButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.likeCommentButton");
        actions.waitFor(1000);
    }
    public void dislikeComment() {
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.dislikeCommentButton");
        actions.waitForElementClickable("weAreSocialNetwork.explorePostsPage.dislikeCommentButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.dislikeCommentButton");
        actions.waitFor(1000);
    }
}
