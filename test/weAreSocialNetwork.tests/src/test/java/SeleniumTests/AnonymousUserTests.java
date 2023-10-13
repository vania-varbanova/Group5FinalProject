package SeleniumTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
    @BeforeEach
    public void beforeEach() {
        WebDriver webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        driver = new Driver(webDriver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }
    @AfterEach
    public void afterEach() {
        driver.quit();
    }
    @Test
    public void anonymousUserCanSuccessfullyViewAboutUsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        mainPage.navigateToAboutUsPage();
        //mainPage.assertButtonByLinkTextIsVisible("About us");
    }
    @Test
    public void anonymousUserCanSuccessfullyViewLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        //mainPage.assertButtonByLinkTextIsVisible("Explore all posts");
    }
    @Test
    public void anonymousUserCanSuccessfullyViewCertainCategoryLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        latestPostsPage.selectCategory();
    }
}
