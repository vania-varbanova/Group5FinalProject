package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import testFramework.Driver;
import testFramework.UserActions;
import utils.UiPropertiesReader;

import java.time.Duration;

import static testFramework.Utils.getConfigPropertyByKey;

public abstract class BasePage {

    protected String url;
    protected WebDriver driver;
    protected UserActions actions;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver, String urlKey) {
        String pageUrl = getConfigPropertyByKey(urlKey);
        this.driver = driver;
        this.url = pageUrl;
        actions = new UserActions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void navigateToPage() {
        this.driver.get(url);
        waitForPageToLoad();
    }

    public WebElement mainHeading() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey(("weAreSocialNetwork.heading"))));
    }

    public void assertPageHeadingEquals(String expectedPageHeading) {
        String actualPageHeading = mainHeading().getText();

        Assertions.assertEquals(expectedPageHeading, actualPageHeading, String.format("Expected page heading: %s\n Actual page heading: $s", expectedPageHeading, actualPageHeading));
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
//        Assertions.assertTrue(currentUrl.contains(url),
//                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);url
    }

    public WebElement buttonByLinkText(String text) {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.navigationSectionLinks"), text);
        return driver.findElement(By.xpath(xpath));
    }

    public abstract void waitForPageToLoad();
}
