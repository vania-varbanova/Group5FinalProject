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
    }
    public void selectCategory() {
        waitForPageToLoad();

        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.categorySelection");
        actions.selectOptionFromDropdown("weAreSocialNetwork.latestPostsPage.categorySelection", "All");

        actions.waitForElementClickable("weAreSocialNetwork.latestPostsPage.browseButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.browseButton");
    }
    public void likePost() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.likeButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.likeButton");
    }
    public void navigateToExplorePostPage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.latestPostsPage.explorePostButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.explorePostButton");
    }
}
