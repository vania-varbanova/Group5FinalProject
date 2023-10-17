package SeleniumTests;

import annotations.Issue;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UiDataGenerator;

public class ConnectionTests extends BaseSystemTest {
    private UserRequestModel user;
    private UserResponseModel createdUser;
    private UserRequestModel admin;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        uiDataGenerator = new UiDataGenerator();

        user = apiDataGenerator.createUserWithRoleUser();
        admin = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(user);
        createdUser = userRequests.createUser(admin);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(admin.getUsername(), admin.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.clickSeeProfileButtonByUsername(user.getUsername());
        personalProfilePage.clickConnectButton();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @Issue(key = "WSFP-50")
    public void connectionSuccessfullySend_when_clickConnectButton() {
        personalProfilePage.assertConnectMessageIsVisible();

    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @Issue(key = "WSFP-52")
    public void requestSuccessfullyApproved_when_clickApproveButton() {
        mainPage.logOut();
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.clickFriendRequestButton();

        connectionPage.clickApproveButton();

        connectionPage.assertNoRequestMessageIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @Issue(key = "WSFP-51")
    public void userSuccessfullyDisconnect_when_clickDisconnectButton() {
        mainPage.logOut();
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.clickFriendRequestButton();
        connectionPage.clickApproveButton();
        mainPage.navigateToPage();

        personalProfilePage.navigateToConcreteUser(createdUser.getId());
        personalProfilePage.clickDisconnectButton();

        personalProfilePage.assertConnectButtonIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @Issue(key = "WSFP-53")
    public void userFriendListUpdated_when_approveFriendRequest() {
        mainPage.logOut();
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.clickFriendRequestButton();
        connectionPage.clickApproveButton();

        personalProfilePage.navigateToPersonalProfilePage();

        personalProfilePage.assertColumnValueEquals("Friend list", "1 friends");
    }
}
