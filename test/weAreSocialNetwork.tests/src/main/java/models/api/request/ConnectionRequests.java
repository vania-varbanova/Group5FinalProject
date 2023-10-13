package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.api.helpers.GetUserRequest;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.FriendRequestAcceptRequestModel;
import models.api.requestModel.FriendRequestAcceptResponseModel;
import models.api.responseModel.ConnectionSendResponseModel;
import utils.ConfigPropertiesReader;
import utils.LoggerApiMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConnectionRequests extends BaseRequest {
    public GetUserRequest[] getRequests(String receiverId, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Get friend requests for user with id: %s.\n", receiverId));

        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .get(String.format("/auth/users/%s/request/", receiverId));

        String responseBody = response.asPrettyString();
        GetUserRequest[] getUserFriendsRequestResponseModel = jsonParser.fromJson(responseBody, GetUserRequest[].class);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Get friend requests", responseBody));
        logger.logLineSeparator();

        return getUserFriendsRequestResponseModel;
    }

    public FriendRequestAcceptResponseModel acceptRequest(FriendRequestAcceptRequestModel friendRequestAcceptRequestModel, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Accepting friend requests for friend request with id: %s.\n", friendRequestAcceptRequestModel.getRequestId()));
        var response = RestAssured
                .given()
                .queryParam("requestId", friendRequestAcceptRequestModel.getRequestId())
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .post(String.format("/auth/users/%s/request/approve", friendRequestAcceptRequestModel.getReceiverId()));

        String responseBody = response.asPrettyString();
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Accept friend request", responseBody));
        return getFriendRequestAcceptResponseModelFromResponseBody(responseBody);
    }

    public ConnectionSendResponseModel sendRequest(ConnectionSendRequestModel connectionSendRequestModel, String cookieValue) {
        String requestBody = jsonParser.toJson(connectionSendRequestModel);
        logger.log(String.format(LoggerApiMessages.REQUEST_BODY, "Send connection", requestBody));

        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/auth/request");

        String responseBody = response.body().asPrettyString();

        ConnectionSendResponseModel connectionSendResponseModel = getConnectionSendResponseModelFromResponseBody(responseBody);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Send connection", responseBody));
        logger.logLineSeparator();

        return connectionSendResponseModel;
    }

    private ConnectionSendResponseModel getConnectionSendResponseModelFromResponseBody(String responseBody) {
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String senderUsername = parsedResponseBody.get(0);
        String receiverUsername = parsedResponseBody.get(parsedResponseBody.size() - 1);
        ConnectionSendResponseModel connectionSendResponseModel = new ConnectionSendResponseModel();
        connectionSendResponseModel.setSenderUsername(senderUsername);
        connectionSendResponseModel.setReceiverUsername(receiverUsername);

        return connectionSendResponseModel;
    }

    private FriendRequestAcceptResponseModel getFriendRequestAcceptResponseModelFromResponseBody(String responseBody) {
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String receiverUsername = parsedResponseBody.get(0);
        String senderUsername = parsedResponseBody.get(parsedResponseBody.size() - 1);
        FriendRequestAcceptResponseModel connectionSendResponseModel = new FriendRequestAcceptResponseModel();
        connectionSendResponseModel.setSenderUsername(senderUsername);
        connectionSendResponseModel.setReceiverUsername(receiverUsername);

        return connectionSendResponseModel;
    }
}
