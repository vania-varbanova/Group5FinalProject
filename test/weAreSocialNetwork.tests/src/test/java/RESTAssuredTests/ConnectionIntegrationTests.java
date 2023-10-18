package RESTAssuredTests;

import annotations.Issue;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import models.api.helpers.GetUserRequest;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.FriendRequestAcceptRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.ConnectionSendResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.*;
@Epic("Connections tests")
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
    @Step("Initializing required data")
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
//        databaseService.deleteUserWithId(senderResponseModel.getId());
//        databaseService.deleteUserWithId(receiverResponseModel.getId());
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToConnectPeople")
    @Issue(key = "WSFP-50")
    @Tag("Allure")
    @Story("User tries to login the system with empty username and invalid password.")
    @Description("Invalid Login Test with Empty Username and Invalid Password.")
    public void connectionSuccessfullySend_when_serverReturnsStatusCode200() {
        Assertions.assertEquals(senderResponseModel.getName(), connectionSendResponseModel.getSenderUsername(), "sender user name");
        Assertions.assertEquals(receiverResponseModel.getName(), connectionSendResponseModel.getReceiverUsername(), "receiver user name");
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToConnectPeople")
    @Issue(key = "WSFP-52")
    @Tag("Allure")
    public void getUserRequestSend() {
        cookieValue = authenticateRequests.authenticateUser(receiverUser);
        GetUserRequest[] request = connectionRequests.getRequests(receiverResponseModel.getId(), cookieValue);
        GetUserRequest takeRequest = request[0];

        Assertions.assertNotNull(takeRequest.getRequestId(), "Friend request id is not null.");
    }

    @Test
    @Tag("Integration")
    @Tag("OperationsToConnectPeople")
    @Issue(key = "WSFP-52")
    public void approveSendRequest() {
        cookieValue = authenticateRequests.authenticateUser(receiverUser);

        GetUserRequest[] request = connectionRequests.getRequests(receiverResponseModel.getId(), cookieValue);
        GetUserRequest takeRequest = request[0];
        friendRequestAcceptRequestModel = apiDataGenerator.createAcceptFriendRequest(receiverResponseModel.getId(), takeRequest.getRequestId());
        var result = connectionRequests.acceptRequest(friendRequestAcceptRequestModel, cookieValue);

        Assertions.assertEquals(senderUser.getUsername(), result.getSenderUsername());
        Assertions.assertEquals(receiverUser.getUsername(), result.getReceiverUsername());
    }
}
