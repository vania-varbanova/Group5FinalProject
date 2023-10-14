package models.api.request;
import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.api.requestModel.EditSkillRequestModel;
import models.api.requestModel.SkillsRequestModel;
import models.api.responseModel.SkillsResponseModel;
import utils.ConfigPropertiesReader;


import java.util.Arrays;
import java.util.List;

public class SkillsRequests extends BaseRequest{
    private Faker faker = new Faker();
    public SkillsResponseModel createSkill(SkillsRequestModel skillsRequestModel) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");
        String requestBody = jsonParser.toJson(skillsRequestModel);
        logger.log(String.format("Request body: %s", requestBody));
        var response = RestAssured
                .given()
                .contentType("application/json")
                .body(requestBody)
                .post("/skill/create");

        SkillsResponseModel skillsResponseModel = jsonParser.fromJson(response.body().prettyPrint(), SkillsResponseModel.class);
        return skillsResponseModel;
    }
    public List<SkillsResponseModel> getAllSkills() {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        Response response = RestAssured
                .given()
                .contentType("application/json")
                .get("/skill");

        String responseBody = response.body().prettyPrint();
        SkillsResponseModel[] skillsArray = jsonParser.fromJson(responseBody, SkillsResponseModel[].class);

        return Arrays.asList(skillsArray);
    }


    public SkillsResponseModel getOneSkill(Object skillId) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var response = RestAssured
                .given()
                .contentType("application/json")
                .queryParam("skillId", skillId)
                .get("/skill/getOne");

        SkillsResponseModel skillsResponseModel = jsonParser.fromJson(response.body().prettyPrint(), SkillsResponseModel.class);

        return skillsResponseModel;
    }
    public Response editSkill(Object skillId, String skill) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var response = RestAssured
                .given()
                .contentType("application/json")
                .queryParam("skill", skill)
                .queryParam("skillId", skillId)
                .put("/skill/edit");

        return response;
    }

    public Response deleteSkill(String skillId) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        Response response = RestAssured
                .given()
                .queryParam("skillId", skillId)
                .put("/skill/delete");

        return response;
    }
}
