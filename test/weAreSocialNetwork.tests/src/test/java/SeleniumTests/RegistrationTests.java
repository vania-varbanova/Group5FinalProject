package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.ui.UserUiModel;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UiDataGenerator;

public class RegistrationTests extends BaseSystemTest {
    private final String INVALID_PASSWORD_ERROR_MESSAGE = "Your password is not confirmed";
    private final String INVALID_EMAIL_ERROR_MESSAGE = "this doesn't look like valid email";
    private final String EXISTING_USER_ERROR_MESSAGE = "User with this username already exist";
    private final String PAGE_HEADING = "Welcome to our community.";
    private UserUiModel userUiModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();

        uiDataGenerator = new UiDataGenerator();
        userUiModel = uiDataGenerator.createUser();
        registrationPage.navigateToPage();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-13")
    public void userSuccessfullyRegister_when_validCredentials() throws InterruptedException {
        registrationPage.enterRegistrationCredentials(userUiModel);
        Thread.sleep(3000);
        mainPage.assertPageHeadingEquals(PAGE_HEADING);
        System.out.println();
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-33")
    public void errorMessageDisplayed_when_invalidEmail() {
        String newEmail = userUiModel.getEmail().replace("@", "");
        userUiModel.setEmail(newEmail);

        registrationPage.enterRegistrationCredentials(userUiModel);

        registrationPage.assertErrorMessageEquals(INVALID_EMAIL_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-87")
    public void errorMessageDisplayed_when_passwordDoesNotMatchConfirmPassword() {
        String newConfirmPassword = userUiModel.getConfirmationPassword().concat("ABC");
        userUiModel.setConfirmationPassword(newConfirmPassword);

        registrationPage.enterRegistrationCredentials(userUiModel);

        registrationPage.assertErrorMessageEquals(INVALID_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-86")
    public void errorMessageDisplayed_when_enterEmptyConfirmPassword() {
        userUiModel.setConfirmationPassword("");

        registrationPage.enterRegistrationCredentials(userUiModel);

        registrationPage.assertErrorMessageEquals(INVALID_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-35")
    public void errorMessageDisplayed_when_createANewAccountWithAnAlreadyExistingUsername() {
        UserRequests userRequests = new UserRequests();
        var userRequestModel = apiDataGenerator.createUserWithRoleUser();
        var userResponseModel = userRequests.createUser(userRequestModel);
        String newUsername = userResponseModel.getName();
        userUiModel.setUsername(newUsername);

        registrationPage.enterRegistrationCredentials(userUiModel);

        registrationPage.assertErrorMessageEquals(EXISTING_USER_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-27")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-96")
    public void AttemptCreateNewAccountWithCharacterBelowMinimumAllowedCharactersPassword() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-28")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-97")
    public void AttemptCreateNewAccountWithValidNumberCharactersPasswordButNoDigit() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-29")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-98")
    public void AttemptCreateNewAccountWithValidNumberCharactersPasswordButNoCapitalLetter() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-30")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-100")
    public void AttemptCreateNewAccountWithValidNumberCharactersPasswordButNoSpecialSymbol() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-31")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-99")
    public void AttemptCreateNewAccountWithValidNumberCharactersPasswordButNoLowercaseLetter() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-32")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-101")
    public void AttemptCreateNewAccountWithAlreadyRegisteredEmail() {

    }

}
