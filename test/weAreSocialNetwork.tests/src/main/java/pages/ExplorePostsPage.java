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
    }
}
