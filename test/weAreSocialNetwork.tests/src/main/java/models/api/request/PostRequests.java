package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.PostRequestModel;
import models.api.responseModel.PostResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class PostRequests extends BaseRequest {

    public PostResponseModel createPost(PostRequestModel postRequestModel, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(postRequestModel);
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBody)
                .post("/post/auth/creator");

        PostResponseModel postResponseModel = jsonParser.fromJson(response.body().prettyPrint(), PostResponseModel.class);
       return postResponseModel;
    }
}
