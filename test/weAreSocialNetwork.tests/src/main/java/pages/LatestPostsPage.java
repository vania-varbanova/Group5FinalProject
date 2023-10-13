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

        actions.waitForElementVisible("//select[@id=\"name\"]");
        actions.selectOptionFromDropdown("//select[@id=\"name\"]", "All");

        actions.waitForElementClickable("weAreSocialNetwork.latestPostsPage.browseButton");
        actions.clickElement("weAreSocialNetwork.latestPostsPage.browseButton");
    }
}
