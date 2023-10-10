package integration;

import models.api.request.ConnectionSendRequest;
import models.api.request.PostRequests;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.requestModel.SenderUserRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.ConnectionSendResponseModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
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
        ConnectionSendRequest connectionSendRequest = new ConnectionSendRequest();
        ConnectionSendRequestModel receiver=new ConnectionSendRequestModel();
        receiver.setReceiverId(receiverResponseModel.getId() );
        receiver.setReceiverUsername(receiverResponseModel.getName());
        ConnectionSendResponseModel connectionSendResponseModel = connectionSendRequest.sendRequest(receiver, senderCookie);

        //sender - ApiDataGenerator.createUser()
        //receiver  - AppDataGenerator,.createUser()
        //senderResponseModel = userRequest.createUser(sender)
        //receiverResponseModel = userRequest.createUser(sender)
        //String senderCookie =authenticate(sender)
        //String message= sendConnection(cookie, receiverResponseModel)
    }

    public void getUserRequestSend() {
        //sender - ApiDataGenerator.createUser()
        //receiver  - AppDataGenerator,.createUser()
        //senderResponseModel = userRequest.createUser(sender)
        //receiverResponseModel = userRequest.createUser(sender)
        //String senderCookie =authenticate(sender)
        //String message= sendConnection(cookie, receiverResponseModel)

        //String reciverCookie =authenticate(receiver)
        //
    }
}
