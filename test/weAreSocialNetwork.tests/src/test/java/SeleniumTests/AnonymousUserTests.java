package SeleniumTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Link;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AnonymousUserTests extends BaseSystemTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        actions.waitFor(500);
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
    @Description("As an unregistered user of a WEare social network, I want to see all posts of a selected user.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-39")
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
