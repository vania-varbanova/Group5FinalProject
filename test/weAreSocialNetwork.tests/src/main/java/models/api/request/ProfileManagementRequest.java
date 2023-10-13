package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.api.requestModel.ProfileManagementRequestModel;
import models.api.responseModel.ProfileManagementResponseModel;
import utils.LoggerApiMessages;

public class ProfileManagementRequest extends BaseRequest {
    public ProfileManagementResponseModel updateUser(String userId, String cookieValue, ProfileManagementRequestModel profileManagementRequestModel) {
        String requestBody = jsonParser.toJson(profileManagementRequestModel);
        logger.log(String.format(LoggerApiMessages.REQUEST_BODY, "Update user", requestBody));

        var response = RestAssured
                .given()
                .cookie(generateAuthenticationCookieWithValue(cookieValue))
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post(String.format("/users/auth/%s/personal", userId));

        var responseBody = response.body().asPrettyString();
        ProfileManagementResponseModel profileManagementResponseModel = jsonParser.fromJson(responseBody, ProfileManagementResponseModel.class);
        logger.log(String.format(LoggerApiMessages.RESPONSE_BODY, "Update user", requestBody));
        logger.logLineSeparator();

        return profileManagementResponseModel;
    }
}