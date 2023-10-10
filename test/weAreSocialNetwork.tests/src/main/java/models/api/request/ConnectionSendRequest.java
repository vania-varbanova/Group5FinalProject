package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.ConnectionSendRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.responseModel.CommentResponseModel;
import models.api.responseModel.ConnectionSendResponseModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.UserResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ConnectionSendRequest extends BaseRequest {
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
                .post("/post/auth/request");
        String responseBody = response.body().print();
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String senderUsername = parsedResponseBody.get(0);
        String receiverUsername = parsedResponseBody.get(4);
        ConnectionSendResponseModel connectionSendResponseModel = new ConnectionSendResponseModel();
        connectionSendResponseModel.setSenderUsername(senderUsername);
        connectionSendResponseModel.setReceiverUsername(receiverUsername);

        return connectionSendResponseModel;

    }
}