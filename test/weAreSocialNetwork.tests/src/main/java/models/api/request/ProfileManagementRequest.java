package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.ProfileManagementResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProfileManagementRequest extends BaseRequest{
    public ProfileManagementResponseModel  updateUser(String userId,ProfileManagementRequestModel profileManagementRequestModel) {
        String requestBody = jsonParser.toJson(profileManagementRequestModel);
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post(String.format("/users/auth/%s/personal"));
        Assertions.assertEquals(200, response.statusCode());
        String responseBody = response.body().print();
//        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
//        String name= parsedResponseBody.get(USERNAME_INDEX);
//        String id = parsedResponseBody.get(USER_ID_INDEX);

        ProfileManagementResponseModel profileManagementResponseModel = jsonParser.fromJson(response.body().prettyPrint(), ProfileManagementResponseModel.class);
        return profileManagementResponseModel;
    }
}
