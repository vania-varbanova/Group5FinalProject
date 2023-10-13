package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.PostResponseModel;
import models.api.responseModel.ProfileManagementResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ProfileManagementRequest extends BaseRequest{
    public ProfileManagementResponseModel  updateUser(String userId,String cookieValue,ProfileManagementRequestModel profileManagementRequestModel) {
        String requestBody = jsonParser.toJson(profileManagementRequestModel);
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBody)
                .post(String.format("/users/auth/%s/personal",userId));
        Assertions.assertEquals(200, response.statusCode());
        String responseBody = response.body().print();

        ProfileManagementResponseModel profileManagementResponseModel = jsonParser.fromJson(response.body().prettyPrint(), ProfileManagementResponseModel.class);
        return profileManagementResponseModel;
    }
}
