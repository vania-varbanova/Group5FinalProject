package RESTAssuredTests;

import annotations.Issue;
import io.qameta.allure.*;
import models.api.helpers.GetUserRequest;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.FriendRequestAcceptRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.ConnectionSendResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.*;

@Feature("Operations to connect people")
public class ConnectionIntegrationTests extends BaseIntegrationTest {
    private UserRequestModel senderUser;
    private UserRequestModel receiverUser;
    private UserResponseModel senderResponseModel;
    private UserResponseModel receiverResponseModel;
    private ConnectionSendRequestModel connectionSendRequestModel;
    private ConnectionSendResponseModel connectionSendResponseModel;
    private FriendRequestAcceptRequestModel friendRequestAcceptRequestModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        senderUser = apiDataGenerator.createUserWithRoleUser();
        receiverUser = apiDataGenerator.createUserWithRoleUser();
        apiDataGenerator.createUserWithRoleUser();
        senderResponseModel = userRequests.createUser(senderUser);
        receiverResponseModel = userRequests.createUser(receiverUser);
        cookieValue = authenticateRequests.authenticateUser(senderUser);
        connectionSendRequestModel = apiDataGenerator.createConnection(receiverResponseModel.getId(), receiverResponseModel.getName());
        connectionSendResponseModel = connectionRequests.sendRequest(connectionSendRequestModel, cookieValue);
    }

    @Override
    @AfterEach
    public void afterEach() {
        databaseService.deleteUserWithId(senderResponseModel.getId());
        databaseService.deleteUserWithId(receiverResponseModel.getId());
    }

    @Test
    @Issue(key = "WSFP-50")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-50")
    @Description("As a user of the WEare social network, I would like to be able to send a connection request to another user")
    public void connectionSuccessfullySend_when_serverReturnsStatusCode200() {
        Assertions.assertEquals(senderResponseModel.getName(), connectionSendResponseModel.getSenderUsername(), "sender user name");
        Assertions.assertEquals(receiverResponseModel.getName(), connectionSendResponseModel.getReceiverUsername(), "receiver user name");
    }

    @Test
    @Issue(key = "WSFP-52")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-52")
    public void userSuccessfullyGetRequests_when_serverReturnsStatusCode200() {
        cookieValue = authenticateRequests.authenticateUser(receiverUser);
        GetUserRequest[] request = connectionRequests.getRequests(receiverResponseModel.getId(), cookieValue);
        GetUserRequest takeRequest = request[0];

        Assertions.assertNotNull(takeRequest.getRequestId(), "Friend request id is not null.");
    }

    @Test
    @Issue(key = "WSFP-52")
    @Link(url = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-52")
    public void requestSuccessfullyApproved_when_serverReturnsStatusCode200() {
        cookieValue = authenticateRequests.authenticateUser(receiverUser);

        GetUserRequest[] request = connectionRequests.getRequests(receiverResponseModel.getId(), cookieValue);
        GetUserRequest takeRequest = request[0];
        friendRequestAcceptRequestModel = apiDataGenerator.createAcceptFriendRequest(receiverResponseModel.getId(), takeRequest.getRequestId());
        var result = connectionRequests.acceptRequest(friendRequestAcceptRequestModel, cookieValue);

        Assertions.assertEquals(senderUser.getUsername(), result.getSenderUsername());
        Assertions.assertEquals(receiverUser.getUsername(), result.getReceiverUsername());
    }
}
