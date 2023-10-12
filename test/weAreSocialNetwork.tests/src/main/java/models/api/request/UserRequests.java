package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UserRequests extends BaseRequest {
    private static final int USERNAME_INDEX = 3;
    private static final int USER_ID_INDEX = 6;
    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        String requestBody = jsonParser.toJson(userRequestModel);

        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post("/users/");

        Assertions.assertEquals(200, response.statusCode());
        String responseBody = response.body().print();
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String name= parsedResponseBody.get(USERNAME_INDEX);
        String id = parsedResponseBody.get(USER_ID_INDEX);
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(id);
        userResponseModel.setName(name);
        ConsoleLogger.log(userResponseModel.toString());
        return userResponseModel;
    }

}
