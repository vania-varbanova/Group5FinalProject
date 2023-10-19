package SeleniumTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import models.api.requestModel.UserRequestModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Feature("Authentication process")
public class LoginTests extends BaseSystemTest {
    private static final String INVALID_DETAILS_ERROR_MESSAGE = "Wrong username or password.";

    private UserRequestModel user;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        user = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(user);

        loginPage.navigateToPage();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    @Tag("System")
    @Tag("AuthenticationProcess")
    @Issue(key = "WSFP-17")
    @Description("Testing logging in to an already existing account.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-17")
    public void userSuccessfullyLogin_when_enterValidCredentials() {
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());

        mainPage.assertButtonByLinkTextIsVisible("LOGOUT");
    }

    @Test
    @Tag("System")
    @Tag("AuthenticationProcess")
    @Issue(key = "WSFP-18")
    @Description("Testing logging out of an already existing account.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-18")
    public void userSuccessfullyLogout_when_clickLogoutButton() throws InterruptedException {
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());

        mainPage.logOut();
        Thread.sleep(3000);
        loginPage.assertPageHeadingEquals("Login Page");
    }

    @Test
    @Tag("System")
    @Tag("AuthenticationProcess")
    @Issue(key = "WSFP-34")
    @Description("Testing account creation with an already registered email in the system and valid username and password.")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-34")
    public void errorMessageDisplayed_when_enterInvalidPassword() {
        user.setPassword(user.getPassword() + "qwe");

        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());

        loginPage.assertErrorMessageEquals(INVALID_DETAILS_ERROR_MESSAGE);
    }
}
