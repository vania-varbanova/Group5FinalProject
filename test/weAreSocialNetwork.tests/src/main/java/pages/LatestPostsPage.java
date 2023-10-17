package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigPropertiesReader;

public class LatestPostsPage extends BasePage {
    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }

    public LatestPostsPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }
    public void navigateToLatestPostsPage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.homePage.latestPostsButton");
        actions.clickElement("weAreSocialNetwork.homePage.latestPostsButton");
        actions.waitFor(1000);
    }
    public void selectCategory() {
        waitForPageToLoad();

        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.categorySelection");
        actions.selectOptionFromDropdown("weAreSocialNetwork.latestPostsPage.categorySelection", "All");

        actions.waitForElementClickable("weAreSocialNetwork.latestPostsPage.browseButton");
        actions.clickElementWithJavaScript("weAreSocialNetwork.latestPostsPage.browseButton");
        actions.waitFor(1000);
    }
    public void likePost() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.likeButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.likeButton");
        actions.waitFor(1000);
    }
    public void dislikePost() {
        actions.waitForElementVisible("weAreSocialNetwork.explorePostsPage.dislikeButton");
        actions.clickElement("weAreSocialNetwork.explorePostsPage.dislikeButton");
        actions.waitFor(1000);
    }
    public void navigateToExplorePostPage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.explorePostButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.explorePostButton");
        actions.waitFor(1000);
    }
}
