package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.CommentRequestModel;
import models.api.responseModel.CommentResponseModel;
import utils.LoggerApiMessages;

public class CommentRequest extends BaseRequest {
    public CommentResponseModel createComment(CommentRequestModel commentRequestModel, String cookieValue) {
        String requestBody = jsonParser.toJson(commentRequestModel);
        logger.log(String.format(LoggerApiMessages.REQUEST_BODY, "Create comment", requestBody));

        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/comment/auth/creator");

        var responseBody = response.body().asPrettyString();
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Create comment", responseBody));
        CommentResponseModel commentResponseModel = jsonParser.fromJson(response.body().asPrettyString(), CommentResponseModel.class);
        logger.logLineSeparator();

        return commentResponseModel;
    }

    public CommentResponseModel likeComment(String id, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Like comment with id: %s.\n", id));

        var response = RestAssured
                .given()
                .queryParam("commentId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .post("/comment/auth/likesUp");

        var responseBody = response.body().asPrettyString();
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Like comment", responseBody));
        CommentResponseModel updatedCommentModel = jsonParser.fromJson(responseBody, CommentResponseModel.class);
        logger.logLineSeparator();
        return updatedCommentModel;
    }

    public CommentResponseModel dislikeComment(String id, String cookieValue) {
        logger.logSuccessfullMessage(String.format("Dislike comment with id: %s.\n", id));

        var response = RestAssured
                .given()
                .queryParam("commentId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .post("/comment/auth/likesUp");

        String responseBody = response.body().asPrettyString();
        CommentResponseModel dislikeCommentModel = jsonParser.fromJson(responseBody, CommentResponseModel.class);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Dislike comment", responseBody));
        logger.logLineSeparator();

        return dislikeCommentModel;
    }

    public ResponseOptions editComment(String id, String cookieValue, CommentRequestModel commentRequestModel) {
        var response = RestAssured
                .given()
                .queryParam("commentId", id)
                .queryParam("content", commentRequestModel.getContentComment())
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .put("/comment/auth/editor");

        logger.logSuccessfullMessage(String.format("Comment with id: %s successfully edited.", id));
        logger.logLineSeparator();

        return response;
    }

    public ResponseOptions deleteComment(String id, String cookieValue) {

        var response = RestAssured
                .given()
                .queryParam("commentId", id)
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .delete("/comment/auth/manager");

        if (response.statusCode() != 200) {
            return response;
        }

        logger.logSuccessfullMessage(String.format(LoggerApiMessages.ENTITY_SUCCESSFULLY_DELETED, "Comment", id));
        logger.logLineSeparator();
        return response;
    }
}


