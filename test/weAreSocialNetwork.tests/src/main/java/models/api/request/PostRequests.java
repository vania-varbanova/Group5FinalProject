package models.api.request;

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

    public ResponseOptions deletePost(String postId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("postId", postId)
                .cookie(String.valueOf(cookie))
                .delete("/post/auth/manager");
       return  response;
    }

    public PostResponseModel likePost(int postId, String cookieValue) {
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

    public ResponseOptions GetAllPost( String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .get("/post/");
        return response;
    }
}
