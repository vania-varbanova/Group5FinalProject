package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigPropertiesReader;

public class ExplorePostsPage extends BasePage {
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
        actions.waitForElementClickable("weAreSocialNetwork.deletePostPage.submitPostButton");
        WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit' and @value='Submit']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }
}
