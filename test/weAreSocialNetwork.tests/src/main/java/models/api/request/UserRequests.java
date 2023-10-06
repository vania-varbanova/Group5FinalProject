package models.api.request;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import models.api.requestModel.UserRequestModel;

public class UserRequests extends BaseRequest {

    public void createUser(UserRequestModel userRequestModel) {
        String requestBody = jsonParser.toJson(userRequestModel);



        RestAssured.baseURI = "http://localhost:8081/api";
            var response = RestAssured
                    .given()
                    .body(requestBody)
                    .post("users/");



//        ResponseBody body = response.body();
//        body.toString();
    }
}
