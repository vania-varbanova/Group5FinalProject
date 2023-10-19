package SeleniumTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UiDataGenerator;
@Feature("Operations to connect people")
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
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    @Issue(key = "WSFP-50")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-50")
    @Description("As a user of the WEare social network, I would like to be able to send a connection request to another user")
    public void connectionSuccessfullySend_when_clickConnectButton() {
        personalProfilePage.assertConnectMessageIsVisible();

    }

    @Test
    @Issue(key = "WSFP-52")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-52")
    @Description("As a user of the WEare social network, I want to accept a request sent to me by another user")
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
    @Issue(key = "WSFP-51")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-51")
    @Description("As a user of the WEare social network, I would like to be able to stop already existing a connection to another user")
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
    @Issue(key = "WSFP-53")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-53")
    @Description("As a registered user of the WEare social network, I want to see how many active friend connections I currently have.")
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
