package system;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class LoginTests extends BaseSystemTest {
    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private DatabaseService databaseService;
    private UserResponseModel userResponseModel;
    private UserRequestModel userRequestModel;

    @Override
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        databaseService = new DatabaseService();

        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
    }

    @Override
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    public void userSuccessfullyLogin_when_enterValidCredentials() throws InterruptedException {
        loginPage.navigateToPage();

        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
    }

//    @Test
//    public void userSuccessfullyRegistration_when_enterValidCredentials() {
//        RegistrationPage registrationPage = new RegistrationPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
//        UiDataGenerator uiDataGenerator = new UiDataGenerator();
//        UserUiModel userUiModel = uiDataGenerator.createUser();
//        registrationPage.navigateToPage();
//        registrationPage.enterRegistrationCredentials(userUiModel);
//        registrationPage.assertMessageByLinkTextIsVisible();
//        registrationPage.assertButtonByLinkTextIsClickable();
//        System.out.println();
//    }
}
