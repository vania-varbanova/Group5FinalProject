package models.api.request;

import io.restassured.RestAssured;
import io.restassured.response.ResponseOptions;
import models.api.requestModel.SkillsRequestModel;
import models.api.responseModel.SkillsResponseModel;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class SkillsRequests extends BaseRequest{
    public SkillsResponseModel createSkill(SkillsRequestModel skillsRequestModel) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(skillsRequestModel);
        ConsoleLogger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post("/skill/create");

        SkillsResponseModel skillsResponseModel = jsonParser.fromJson(response.body().prettyPrint(), SkillsResponseModel.class);
        return skillsResponseModel;
    }
    public ResponseOptions deleteSkill(String skillId) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var response = RestAssured
                .given()
                .queryParam("skillId", skillId)
                .put("/skill/delete");

        return  response;
    }
}
