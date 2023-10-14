package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

public class RegistrationTests extends BaseSystemTest {
    private UserUiModel userUiModel;
    private UiDataGenerator uiDataGenerator;
    private RegistrationPage registrationPage;
    private UserRequestModel userRequestModel = new UserRequestModel();
    private ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
    private UserResponseModel userResponseModel = new UserResponseModel();
    private UserRequests userRequests = new UserRequests();

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        uiDataGenerator = new UiDataGenerator();
        userUiModel = uiDataGenerator.createUser();
        registrationPage = new RegistrationPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        registrationPage.navigateToPage();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    public void userSuccessfullyRegister_when_validCredentials() {
        registrationPage.enterRegistrationCredentials(userUiModel);

        registrationPage.assertMessageByLinkTextIsVisible();
        registrationPage.assertButtonByLinkTextIsClickable();
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-33")
    public void errorMessageDisplayed_when_invalidEmail() {
        String newEmail = userUiModel.getEmail().replace("@", "");
        userUiModel.setEmail(newEmail);
        registrationPage.enterRegistrationCredentials(userUiModel);
        //validate same email error message
        System.out.println();
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-87")
    public void errorMessageDisplayed_when_passwordDoesNotMatchConfirmPassword() {
        String newConfirmPassword = userUiModel.getConfirmationPassword().concat("ABC");
        userUiModel.setConfirmationPassword(newConfirmPassword);
        registrationPage.enterRegistrationCredentials(userUiModel);
        //validate error messagge
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-35")
    public void errorMessageDisplayed_when_createANewAccountWithAnAlreadyExistingUsername() {
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        String newUsername = userResponseModel.getName();
        userUiModel.setUsername(newUsername);
        registrationPage.enterRegistrationCredentials(userUiModel);
        registrationPage.assertErrorMessage("User with this username already exist");
        //validateErrorMessageExisiting usernmam,e
        System.out.println();
    }

}
