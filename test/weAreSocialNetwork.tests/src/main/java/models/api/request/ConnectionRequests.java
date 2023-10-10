package models.api.request;

import io.restassured.RestAssured;
import models.api.helpers.GetUserRequest;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.responseModel.ConnectionSendResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConnectionRequests extends BaseRequest{
    public GetUserRequest[] getRequest(String receiverId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .get(String.format("/auth/users/%s/request/", receiverId));
        GetUserRequest[] getUserFriendsRequestResponseModel =jsonParser.fromJson(response.prettyPrint(),GetUserRequest[].class);
        ConsoleLogger.log(getUserFriendsRequestResponseModel.toString());

        return getUserFriendsRequestResponseModel;
    }

    public void acceptRequest(String reciverId, String requestId, String cookieValue){
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("requestId", requestId)
                .cookie(String.valueOf(cookie))
                .post(String.format("/auth/users/%s/request/approve", reciverId));


        ConsoleLogger.log(response.prettyPrint());
    }

    public ConnectionSendResponseModel sendRequest(ConnectionSendRequestModel connectionSendRequestModel, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(connectionSendRequestModel);
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBody)
                .post("/auth/request");
        String responseBody = response.body().print();
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String senderUsername = parsedResponseBody.get(0);
        String receiverUsername = parsedResponseBody.get(5);
        ConnectionSendResponseModel connectionSendResponseModel = new ConnectionSendResponseModel();
        connectionSendResponseModel.setSenderUsername(senderUsername);
        connectionSendResponseModel.setReceiverUsername(receiverUsername);
        ConsoleLogger.log(connectionSendResponseModel.toString());


        return connectionSendResponseModel;

    }
}
