package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigPropertiesReader;
import com.github.javafaker.Faker;

public class PostsPage extends BasePage {

    private Faker faker = new Faker();

    public PostsPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }

    @Override
    public void waitForPageToLoad() {
    actions.waitForJavascript();
    }
    public void navigateToCretePostsPage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.createPostPage.createPostButton");
        actions.clickElement("weAreSocialNetwork.createPostPage.createPostButton");
        actions.waitFor(1000);
    }
    public void cretePrivatePost() {
        String content = (faker.lorem().characters(10, 20));
        waitForPageToLoad();
        actions.scrollUntilElementVisible("weAreSocialNetwork.createPostPage.postContentField");
        actions.typeValueInField(content, "weAreSocialNetwork.createPostPage.postContentField");

        actions.waitForElementVisible("weAreSocialNetwork.createPostPage.savePostButton");
        actions.clickElement("weAreSocialNetwork.createPostPage.savePostButton");
        actions.waitFor(1000);
    }
    public void cretePublicPost() {
        String content = (faker.lorem().characters(10, 20));
        waitForPageToLoad();
        actions.scrollUntilElementVisible("weAreSocialNetwork.createPostPage.postContentField");
        actions.selectOptionFromDropdown("weAreSocialNetwork.createPostPage.postVisibilityButton", "Public post");

        actions.scrollUntilElementVisible("weAreSocialNetwork.createPostPage.postContentField");
        actions.typeValueInField(content, "weAreSocialNetwork.createPostPage.postContentField");

        actions.waitForElementVisible("weAreSocialNetwork.createPostPage.savePostButton");
        actions.clickElement("weAreSocialNetwork.createPostPage.savePostButton");
        actions.waitFor(1000);
    }
}
