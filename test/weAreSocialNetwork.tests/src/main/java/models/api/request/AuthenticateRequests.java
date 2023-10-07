package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.UserRequestModel;
import utils.ConfigPropertiesReader;

public class AuthenticateRequests extends BaseRequest {

    public String authenticateUser(UserRequestModel userRequestModel) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl");
        var response = RestAssured
                .given()
                .contentType("multipart/form-data")
                .multiPart("username", userRequestModel.getUsername())
                .multiPart("password", userRequestModel.getPassword())
                .when()
                .post("/authenticate");
        var result = response.cookies().get("JSESSIONID");
        return result;
    }
}
