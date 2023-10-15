package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.ui.AdminUserUiModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ConnectionPage;
import pages.PersonalProfilePage;
import testFramework.CustomWebDriverManager;
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
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-51")
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
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-50")
    public void userFriendList() throws InterruptedException {
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
        personalProfilePage.assertColumnValueEquals("Friend list","1 friends");

    }

}
