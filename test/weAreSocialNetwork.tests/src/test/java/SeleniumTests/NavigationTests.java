package SeleniumTests;

import org.junit.jupiter.api.Test;
import pages.LatestPostsPage;
import pages.MainPage;
import testFramework.CustomWebDriverManager;

public class NavigationTests extends BaseSeleniumTest {
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
    @Test
    public void userCanSuccessfullyViewAboutUsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        RegisterAndLogin();
        mainPage.navigateToPage();
        mainPage.navigateToAboutUsPage();
    }
    @Test
    public void userCanSuccessfullyViewLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        RegisterAndLogin();
        latestPostsPage.navigateToLatestPostsPage();
        //mainPage.assertButtonByLinkTextIsVisible("Explore all posts");
    }
    @Test
    public void userCanSuccessfullyViewCertainCategoryLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        RegisterAndLogin();
        latestPostsPage.navigateToLatestPostsPage();
        latestPostsPage.selectCategory();
    }
}
