package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.responseModel.ProfileManagementResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class ProfileManagementRequest extends BaseRequest {
    public ProfileManagementResponseModel updateUser(String userId, String cookieValue, ProfileManagementRequestModel profileManagementRequestModel) {
        String requestBody = jsonParser.toJson(profileManagementRequestModel);
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBody)
                .post(String.format("/users/auth/%s/personal", userId));
        // Assertions.assertEquals(200, response.statusCode());
        String responseBody = response.body().print();
//        var parsedResponseBody = Arrays.stream(responseBody.split(" ")).collect(Collectors.toList());
//        String name= parsedResponseBody.get(USERNAME_INDEX);
//        String id = parsedResponseBody.get(USER_ID_INDEX);

        ProfileManagementResponseModel profileManagementResponseModel = jsonParser.fromJson(response.body().prettyPrint(), ProfileManagementResponseModel.class);
        return profileManagementResponseModel;
    }
}
