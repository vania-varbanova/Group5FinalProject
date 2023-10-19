package SeleniumTests;

import io.qameta.allure.Feature;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.DatabaseService;
import testFramework.UserActions;
import utils.ApiDataGenerator;
import utils.UiPropertiesReader;

@Feature("Searching functionality")
public class NavigationTests extends BaseSystemTest {
    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private DatabaseService databaseService;
    private UserResponseModel userResponseModel;
    private UserRequestModel userRequestModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();

        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void userCanSuccessfullyViewAboutUsPage() {
        mainPage.navigateToPage();
        mainPage.navigateToAboutUsPage();

        mainPage.assertPageHeadingEquals(ABOUT_US_PAGE_HEADING);
    }

    @Test
    public void userCanSuccessfullyViewLatestPostsPage() {
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
    }

    @Test
    public void userCanSuccessfullyViewCertainCategoryLatestPostsPage() {
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        latestPostsPage.selectCategory();

        mainPage.assertPageHeadingEquals(EXPLORE_ALL_POSTS_PAGE_HEADING);
    }

    @Test
    public void userCanSuccessfullyViewPersonalProfilePage() {
        mainPage.navigateToPage();
        personalProfilePage.navigateToPersonalProfilePage();

        actions.assertElementPresentByXpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.personalProfileButton"));
    }
}
