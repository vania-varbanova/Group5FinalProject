package system;

import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegistrationPage;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class LoginTests {
    @Test
    public void userSuccessfullyLogin_when_enterValidCredentials() throws InterruptedException {
        MainPage mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        UserRequests userRequests = new UserRequests();
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userRequests.createUser(userRequestModel);
       LoginPage loginPage = new LoginPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
       loginPage.navigateToPage();
       loginPage.enterLoginCredentials(userRequestModel.getUsername(),userRequestModel.getPassword());
        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
//       mainPage.assertButtonByLinkTextIsVisible("Personal Profile");
//       mainPage.assertButtonByLinkTextIsVisible("Add New post");
//
    }
    @Test
    public void userSuccessfullyRegistration_when_enterValidCredentials(){
        RegistrationPage registrationPage = new RegistrationPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        UiDataGenerator uiDataGenerator = new UiDataGenerator();
        UserUiModel userUiModel = uiDataGenerator.createUser();
        registrationPage.navigateToPage();
        registrationPage.enterRegistrationCredentials(userUiModel);
        registrationPage.assertMessageByLinkTextIsVisible();
        registrationPage.assertButtonByLinkTextIsClickable();
        System.out.println();
    }
}
