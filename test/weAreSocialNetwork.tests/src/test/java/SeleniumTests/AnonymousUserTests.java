package SeleniumTests;

import annotations.Issue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import testFramework.UserActions;

public class AnonymousUserTests extends BaseSystemTest {
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        actions = new UserActions();
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
