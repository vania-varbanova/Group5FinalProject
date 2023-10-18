package SeleniumTests;

import annotations.Issue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pages.LatestPostsPage;
import pages.LoginPage;
import pages.MainPage;
import testFramework.CustomWebDriverManager;
import testFramework.Driver;

public class AnonymousUserTests {
    private Driver driver;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected LatestPostsPage latestPostsPage;
    @BeforeEach
    public void beforeEach() {
        WebDriver webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        driver = new Driver(webDriver);
        mainPage = new MainPage(driver);
        latestPostsPage = new LatestPostsPage(driver);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void anonymousUserCanSuccessfullyViewAboutUsPage() {
        mainPage.navigateToPage();
        mainPage.navigateToAboutUsPage();

        mainPage.assertPageHeadingEquals("About us");
    }

    @Test
    @Tag("System")
    @Tag("OperationsRelatedPosts")
    @Issue(key = "WSFP-39")
    public void anonymousUserCanSuccessfullyViewLatestPostsPage() {
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();

        mainPage.assertPageHeadingEquals("Explore all posts");
    }

    @Test
    public void anonymousUserCanSuccessfullyViewCertainCategoryLatestPostsPage() {
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        latestPostsPage.selectCategory();

        mainPage.assertPageHeadingEquals("Explore all posts");
    }
}
