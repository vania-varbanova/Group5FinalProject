package SeleniumTests;

import annotations.Bug;
import annotations.Issue;
import models.api.responseModel.UserResponseModel;
import models.ui.AdminUserUiModel;
import models.ui.UserUiModel;
import org.junit.jupiter.api.*;

public class RegistrationTests extends BaseSystemTest {
    private UserUiModel user;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        user = uiDataGenerator.createUser();
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
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-13")
    public void userSuccessfullyRegister_when_validCredentials() {
        registrationPage.enterRegistrationCredentials(user);

        mainPage.assertPageHeadingEquals(MAIN_PAGE_HEADING);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-33")
    public void errorMessageDisplayed_when_invalidEmail() {
        String newEmail = user.getEmail().replace("@", "");
        user.setEmail(newEmail);

        registrationPage.enterRegistrationCredentials(user);

        registrationPage.assertErrorMessageEquals(INVALID_EMAIL_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-87")
    public void errorMessageDisplayed_when_passwordDoesNotMatchConfirmPassword() {
        String newConfirmPassword = user.getConfirmationPassword().concat("ABC");
        user.setConfirmationPassword(newConfirmPassword);

        registrationPage.enterRegistrationCredentials(user);

        registrationPage.assertErrorMessageEquals(INVALID_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-86")
    public void errorMessageDisplayed_when_enterEmptyConfirmPassword() {
        user.setConfirmationPassword("");

        registrationPage.enterRegistrationCredentials(user);

        registrationPage.assertErrorMessageEquals(INVALID_PASSWORD_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-35")
    public void errorMessageDisplayed_when_createANewAccountWithAnAlreadyExistingUsername() {
        UserResponseModel existingUser = userRequests.createUser(apiDataGenerator.createUserWithRoleUser());
        String newUsername = existingUser.getName();
        user.setUsername(newUsername);

        registrationPage.enterRegistrationCredentials(user);

        registrationPage.assertErrorMessageEquals(EXISTING_USER_ERROR_MESSAGE);
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-151")
    public void userAdminSuccessfullyRegister_when_validCredentials() {
        AdminUserUiModel adminUserInformation = uiDataGenerator.createAdminUser();
        registrationPage.enterRegistrationCredentials(adminUserInformation);
        loginPage.navigateToPage();

        loginPage.enterLoginCredentials(adminUserInformation.getUsername(), adminUserInformation.getPassword());

        mainPage.assertAdminZoneButtonIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-27")
    @Disabled(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-96")
    public void errorMessageDisplayed_when_passwordLengthBelowMinimalRequiredLength() {
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-28")
    @Disabled(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-97")
    public void errorMessageDisplayed_when_passwordDoesNotContainDigit() {
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-29")
    @Disabled(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-98")

    public void errorMessageDisplayed_when_passwordDoesNotContainCapitalLetter() {
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-30")
    @Bug(key = "WSFP-100")
    @Disabled
    public void errorMessageDisplayed_when_passwordDoesNotContainSpecialSymbols() {
    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-31")
    @Disabled(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-99")
    public void errorMessageDisplayed_when_passwordDoesNotContainLowerCaseLetter() {

    }

    @Test
    @Tag("System")
    @Tag("RegistrationProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-32")
    @Disabled(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-101")
    public void errorMessageDisplayed_when_userTryToRegisterWithExisitingEmail() {
    }
}
