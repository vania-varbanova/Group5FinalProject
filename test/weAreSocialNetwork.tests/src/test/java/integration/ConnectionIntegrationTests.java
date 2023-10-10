package integration;

import models.api.helpers.GetUserRequest;
import models.api.request.ConnectionRequests;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.ConnectionSendResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConnectionIntegrationTests extends BaseIntegrationTest {
    private UserResponseModel userResponseModel;

    @Test
    public void connectionSend() {
        UserRequestModel senderUser = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel receiverUser = apiDataGenerator.createUserWithRoleUser();
        UserResponseModel senderResponseModel = userRequests.createUser(senderUser);
        UserResponseModel receiverResponseModel = userRequests.createUser(receiverUser);
        String senderCookie = authenticateRequests.authenticateUser(senderUser);
        System.out.println(senderUser);
        ConnectionRequests connectionSendRequest = new ConnectionRequests();
        ConnectionSendRequestModel receiver = new ConnectionSendRequestModel();
        receiver.setReceiverId(receiverResponseModel.getId());
        receiver.setReceiverUsername(receiverResponseModel.getName());
        ConnectionSendResponseModel connectionSendResponseModel = connectionSendRequest.sendRequest(receiver, senderCookie);
        Assertions.assertEquals(senderResponseModel.getName(), connectionSendResponseModel.getSenderUsername(), "sender user name");
        Assertions.assertEquals(receiverResponseModel.getName(), connectionSendResponseModel.getReceiverUsername(), "receiver user name");
    }

    @Test
    public void getUserRequestSend() {
        UserRequestModel senderUser = apiDataGenerator.createUserWithRoleUser();
        UserRequestModel receiverUser = apiDataGenerator.createUserWithRoleUser();
        UserResponseModel senderResponseModel = userRequests.createUser(senderUser);
        UserResponseModel receiverResponseModel = userRequests.createUser(receiverUser);
        String senderCookie = authenticateRequests.authenticateUser(senderUser);
        ConnectionRequests connectionSendRequest = new ConnectionRequests();
        ConnectionSendRequestModel receiver = new ConnectionSendRequestModel();
        receiver.setReceiverId(receiverResponseModel.getId());
        receiver.setReceiverUsername(receiverResponseModel.getName());
        connectionSendRequest.sendRequest(receiver, senderCookie);
        String receiverCookie = authenticateRequests.authenticateUser(receiverUser);
        ConnectionRequests connectionGetUserRequest = new ConnectionRequests();
        GetUserRequest[] request = connectionGetUserRequest.getRequest(receiverResponseModel.getId(), receiverCookie);
        GetUserRequest takeRequest = request[0];
        //approve rqeust (takeRequest.id, receiverResponseModel.getId(), recieverCookie)
        System.out.println();
        connectionGetUserRequest.acceptRequest(receiverResponseModel.getId(), takeRequest.getRequestId(), receiverCookie);


    }
}
