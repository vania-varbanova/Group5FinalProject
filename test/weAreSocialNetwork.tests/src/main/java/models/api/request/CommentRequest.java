package models.api.request;

import io.restassured.RestAssured;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.CommentRequestModel;
import models.api.requestModel.EditPostRequestModel;
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
    public CommentResponseModel likeComment(int commentId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("commentId", commentId)
                .cookie(String.valueOf(cookie))
                .post("/post/auth/likesUp");

        CommentResponseModel updatedCommentModel = jsonParser.fromJson(response.prettyPrint(), CommentResponseModel.class);
        System.out.println("Like post responsebody: " + response.prettyPrint());

        return updatedCommentModel;
    }
    public CommentResponseModel dislikeComment(int commentId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("commentId", commentId)
                .cookie(String.valueOf(cookie))
                .post("/post/auth/likesUp");

        CommentResponseModel dislikeCommentModel = jsonParser.fromJson(response.prettyPrint(), CommentResponseModel.class);
        System.out.println("Dislike post responsebody: " + response.prettyPrint());

        return dislikeCommentModel;
    }
    public ResponseOptions editComment(int commentId, String cookieValue, CommentRequestModel commentRequestModel) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("commentId", commentId)
                .queryParam("content",commentRequestModel.getContentComment())
                .cookie(String.valueOf(cookie))
                .contentType("application/json")
                .put("/post/auth/editor");

        return response;
    }

    public ResponseOptions deleteComment(String commentId, String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .queryParam("commentId", commentId)
                .cookie(String.valueOf(cookie))
                .delete("/post/auth/manager");
        return  response;
    }

    public ResponseOptions getAllComment( String cookieValue) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        Cookie cookie = new Cookie("JSESSIONID", cookieValue, "/");
        var response = RestAssured
                .given()
                .cookie(String.valueOf(cookie))
                .get("/comment/");
        return response;
    }
}


