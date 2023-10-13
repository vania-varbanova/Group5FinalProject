package SeleniumTests;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.LatestPostsPage;
import pages.MainPage;
import pages.PersonalProfilePage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;

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
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        mainPage.navigateToAboutUsPage();
    }
    @Test
    public void userCanSuccessfullyViewLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        //mainPage.assertButtonByLinkTextIsVisible("Explore all posts");
    }
    @Test
    public void userCanSuccessfullyViewCertainCategoryLatestPostsPage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        LatestPostsPage latestPostsPage = new LatestPostsPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        latestPostsPage.navigateToLatestPostsPage();
        latestPostsPage.selectCategory();
    }
    @Test
    public void userCanSuccessfullyViewPersonalProfilePage() {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        PersonalProfilePage personalProfilePage = new PersonalProfilePage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        personalProfilePage.navigateToPersonalProfilePage();
    }
}
