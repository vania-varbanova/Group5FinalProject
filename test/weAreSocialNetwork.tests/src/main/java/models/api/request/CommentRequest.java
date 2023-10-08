package models.api.request;

import io.restassured.RestAssured;
import models.api.requestModel.CommentRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.responseModel.CommentResponseModel;
import models.api.responseModel.PostResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class CommentRequest extends BaseRequest {
    public CommentResponseModel createComment(CommentRequestModel commentRequestModel, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(commentRequestModel);
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBody)
                .post("/comment/auth/creator");
        CommentResponseModel commentResponseModel = jsonParser.fromJson(response.body().prettyPrint(), CommentResponseModel.class);
        return commentResponseModel;
    }
}


