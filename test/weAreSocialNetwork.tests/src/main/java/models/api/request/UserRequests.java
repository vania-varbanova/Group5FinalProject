package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import utils.ConsoleLogger;
import utils.LoggerApiMessages;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UserRequests extends BaseRequest {
    private final int USERNAME_INDEX = 3;
    private final int USER_ID_INDEX = 6;

    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        String requestBody = jsonParser.toJson(userRequestModel);
        logger.logLineSeparator();
        logger.log(String.format(LoggerApiMessages.REQUEST_BODY, "Create user", requestBody));

        var response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users/");

        assertSuccessStatusCode(response);

        String responseBody = response.body().asPrettyString();
        UserResponseModel parsedResponse = getUserResponseModelFromResponseBody(responseBody);
        logger.logSuccessfullMessage(responseBody);
        logger.logLineSeparator();
        return parsedResponse;
    }

    public UserResponseModel getUserResponseModelFromResponseBody(String responseBody) {
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String name = parsedResponseBody.get(USERNAME_INDEX);
        String id = parsedResponseBody.get(USER_ID_INDEX);
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(id);
        userResponseModel.setName(name);
        return userResponseModel;
    }
}
