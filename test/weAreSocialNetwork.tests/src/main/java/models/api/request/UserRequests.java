package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class UserRequests extends BaseRequest {
    private static final int USERNAME_INDEX = 3;
    private static final int USER_ID_INDEX = 6;

    public UserResponseModel createUser(UserRequestModel userRequestModel) {
        String requestBody = jsonParser.toJson(userRequestModel);
        var x = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        ConsoleLogger.logLineSeparator();
        ConsoleLogger.log(String.format("User create request body: \n%s", requestBody));

        var response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/users/");

        assertSuccessStatusCode(response);

        String responseBody = response.body().asPrettyString();
        ConsoleLogger.logSuccessfullMessage(responseBody);
        ConsoleLogger.logLineSeparator();
        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
        String name = parsedResponseBody.get(USERNAME_INDEX);
        String id = parsedResponseBody.get(USER_ID_INDEX);
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(id);
        userResponseModel.setName(name);
        return userResponseModel;
    }

}
