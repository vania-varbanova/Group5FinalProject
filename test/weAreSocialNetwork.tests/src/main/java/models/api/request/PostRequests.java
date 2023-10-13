package models.api.request;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.EditPostRequestModel;
import models.api.requestModel.PostRequestModel;
import models.api.responseModel.PostResponseModel;
import utils.LoggerApiMessages;

public class PostRequests extends BaseRequest {
    public PostResponseModel createPost(PostRequestModel postRequestModel, String cookieValue) {
        String requestBody = jsonParser.toJson(postRequestModel);
        logger.log(String.format(LoggerApiMessages.REQUEST_BODY, "Create post", requestBody));

        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/post/auth/creator");

        var responseBody = response.body().asPrettyString();
        PostResponseModel postResponseModel = jsonParser.fromJson(responseBody, PostResponseModel.class);

        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Create post", responseBody));
        logger.logLineSeparator();

        return postResponseModel;
    }

    public PostResponseModel likePost(String id, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Sending like request for post with id: %s \n", id));

        var response = RestAssured
                .given()
                .queryParam("postId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .post("/post/auth/likesUp");
        assertSuccessStatusCode(response);

        var responseBody = response.body().asPrettyString();
        PostResponseModel updatedPostModel = jsonParser.fromJson(responseBody, PostResponseModel.class);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Like post", responseBody));
        logger.logLineSeparator();

        return updatedPostModel;
    }

    public PostResponseModel dislikePost(String id, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Sending dislike request for post with id: %s \n", id));
        var response = RestAssured
                .given()
                .queryParam("postId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .post("/post/auth/likesUp");
        assertSuccessStatusCode(response);
        String responseBody = response.asPrettyString();
        PostResponseModel dislikePostModel = jsonParser.fromJson(responseBody, PostResponseModel.class);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Dislike post", responseBody));
        logger.logLineSeparator();

        return dislikePostModel;
    }

    public ResponseOptions editPost(String id, String cookieValue, EditPostRequestModel editPostRequestModels) {
        logger.logLineSeparator();
        String requestBodyEdit = jsonParser.toJson(editPostRequestModels);
        logger.logSuccessfullMessage(String.format("Request bodyEdit: %s", requestBodyEdit));

        var response = RestAssured
                .given()
                .queryParam("postId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .body(requestBodyEdit)
                .put("/post/auth/editor");

        return response;
    }

    public ResponseOptions deletePost(String id, String cookieValue) {
        var response = RestAssured
                .given()
                .queryParam("postId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .delete("/post/auth/manager");
        if (response.statusCode() != 200) {
            return response;
        }
        logger.logSuccessfullMessage(String.format(LoggerApiMessages.ENTITY_SUCCESSFULLY_DELETED, "Post", id));
        logger.logLineSeparator();
        return response;
    }
}
