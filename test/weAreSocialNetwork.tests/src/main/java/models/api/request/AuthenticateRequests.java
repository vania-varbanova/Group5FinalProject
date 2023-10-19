package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.api.requestModel.UserRequestModel;
import utils.ConfigPropertiesReader;

public class AuthenticateRequests extends BaseRequest {
    public String authenticateUser(UserRequestModel userRequestModel) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl");
        String username = userRequestModel.getUsername();
        String password = userRequestModel.getPassword();

        var response = RestAssured
                .given()
                .contentType(ContentType.MULTIPART)
                .multiPart("username", username)
                .multiPart("password", password)
                .when()
                .post("/authenticate");

        logger.logSuccessfullMessage(String.format("User with username: %s successfully authenticated in the system.", username));
        logger.logLineSeparator();
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        return response.cookies().get("JSESSIONID");
    }
}
