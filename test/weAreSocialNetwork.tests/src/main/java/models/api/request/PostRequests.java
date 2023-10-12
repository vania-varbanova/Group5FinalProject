package models.api.request;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.RestAssured;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.EditPostRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.responseModel.PostResponseModel;
import org.openqa.selenium.Cookie;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class PostRequests extends BaseRequest {

    public PostResponseModel createPost(PostRequestModel postRequestModel, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(postRequestModel);
        ConsoleLogger.log("========================================================================");
        ConsoleLogger.log(String.format("Create post request body:\n%s", requestBody));
        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType("application/json")
                .body(requestBody)
                .post("/post/auth/creator");
        ConsoleLogger.log("========================================================================");

        var responseBody = response.body().asPrettyString();
        ConsoleLogger.log(String.format("Create Post Response body: %s", responseBody));
        PostResponseModel postResponseModel = jsonParser.fromJson(responseBody, PostResponseModel.class);
        return postResponseModel;
    }

    public PostResponseModel likePost(int postId, String cookieValue) {
        var x = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("postId", postId)
                .cookie(String.valueOf(cookie))
                .post("/post/auth/likesUp");

        PostResponseModel updatedPostModel = jsonParser.fromJson(response.prettyPrint(), PostResponseModel.class);
        System.out.println("Like post responsebody: " + response.prettyPrint());

        return updatedPostModel;
    }

    public PostResponseModel dislikePost(int postId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("postId", postId)
                .cookie(String.valueOf(cookie))
                .post("/post/auth/likesUp");

        PostResponseModel dislikePostModel = jsonParser.fromJson(response.prettyPrint(), PostResponseModel.class);
        System.out.println("Dislike post responsebody: " + response.prettyPrint());

        return dislikePostModel;
    }

    public ResponseOptions editPost(int postId, String cookieValue, EditPostRequestModel editPostRequestModels) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        String requestBodyEdit = jsonParser.toJson(editPostRequestModels);
        ConsoleLogger.log(String.format("Request bodyEdit: %s", requestBodyEdit));
        var response = RestAssured
                .given()
                .queryParam("postId", postId)
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .body(requestBodyEdit)
                .put("/post/auth/editor");

        return response;
    }

    public ResponseOptions deletePost(String postId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        var response = RestAssured
                .given()
                .queryParam("postId", postId)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .delete("/post/auth/manager");
        return response;
    }

    public ResponseOptions GetAllPost(String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .get("/post/");
        return response;
    }
}
