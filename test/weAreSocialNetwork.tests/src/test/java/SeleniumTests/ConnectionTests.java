package SeleniumTests;

import annotations.IssueLink;
import models.api.Authorities;
import models.api.request.AuthenticateRequests;
import models.api.request.ProfileManagementRequest;
import models.api.request.UserRequests;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.ui.AdminUserUiModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.UiDataGenerator;

public class ConnectionTests extends BaseSystemTest {
    AdminUserUiModel adminUserUiModel;
    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        uiDataGenerator = new UiDataGenerator();
        adminUserUiModel = uiDataGenerator.createAdminUser();


    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-50")
    public void ConnectionSendWhenClickSend() {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonConnect().click();
        personalProfilePage.assertConnectMessageIsVisible();

    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-52")
    public void requestSuccessfullyApproved() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonConnect().click();
        mainPage.clickButtonByLinkText("LOGOUT");
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.friendRequestButton().click();
        Thread.sleep(2000);
        connectionPage.approveButton().click();
        Thread.sleep(2000);
        connectionPage.assertNoRequestMessageIsVisible();
        System.out.println();

    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-51")
    public void DisconnectSuccessfullyApprovedRequest() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        ProfileManagementRequest profileManagementRequest = new ProfileManagementRequest();
        AuthenticateRequests authenticateRequests = new AuthenticateRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        var userResposneModel = userRequests.createUser(adminUserRequestModel);


        loginPage.navigateToPage();

        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonConnect().click();
        mainPage.clickButtonByLinkText("LOGOUT");
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.friendRequestButton().click();
        Thread.sleep(2000);
        connectionPage.approveButton().click();
        Thread.sleep(2000);
        mainPage.navigateToPage();
        personalProfilePage.navigateToConcreteUser(userResposneModel.getId());
////        mainPage.searchFieldByName().click();
//        mainPage.searchFieldByName().sendKeys(adminUserRequestModel.getUsername());
//        mainPage.searchButton().click();
//        searchPage.seeProfileButton().click();
//        Thread.sleep(2000);
        personalProfilePage.buttonDisconnect().click();

        personalProfilePage.assertConnectButtonIsVisible();
        System.out.println();

    }

    @Test
    @Tag("System")
    @Tag("OperationsConnectPeople")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-53")
    public void userCanSeeListFriends() throws InterruptedException {
        UserRequests userRequests = new UserRequests();
        UserRequestModel userRequestModel = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel adminUserRequestModel = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(userRequestModel);
        userRequests.createUser(adminUserRequestModel);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(adminUserRequestModel.getUsername(), adminUserRequestModel.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.getUserByName(userRequestModel.getUsername()).click();
        personalProfilePage.buttonConnect().click();
        mainPage.clickButtonByLinkText("LOGOUT");
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.friendRequestButton().click();
        Thread.sleep(2000);
        connectionPage.approveButton().click();
        Thread.sleep(2000);
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.assertColumnValueEquals("Friend list", "1 friends");

    }

}
