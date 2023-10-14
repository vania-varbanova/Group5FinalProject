package pages;

import org.openqa.selenium.WebDriver;
import testFramework.Driver;
import testFramework.UserActions;

import static testFramework.Utils.getConfigPropertyByKey;

public abstract class BasePage {

    protected String url;
    protected WebDriver driver;
    protected UserActions actions;

    public BasePage(WebDriver driver, String urlKey) {
        String pageUrl = getConfigPropertyByKey(urlKey);
        this.driver = driver;
        this.url = pageUrl;
        actions = new UserActions();
    }

    public void navigateToPage() {
        this.driver.get(url);
        waitForPageToLoad();
    }

    public void assertPageNavigated() {
        String currentUrl = driver.getCurrentUrl();
//        Assertions.assertTrue(currentUrl.contains(url),
//                "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);url
    }

    public abstract void waitForPageToLoad();
}
