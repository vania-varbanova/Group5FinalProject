package models.api.request;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import models.api.requestModel.SkillsRequestModel;
import models.api.responseModel.SkillsResponseModel;
import utils.ConfigPropertiesReader;
import utils.ConsoleLogger;

public class SkillsRequests extends BaseRequest{
    private Faker faker = new Faker();
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
    public SkillsResponseModel getAllSkills() {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var response = RestAssured
                .given()
                .contentType("application/json")
                .get("/skill");

        SkillsResponseModel skillsResponseModel = jsonParser.fromJson(response.body().prettyPrint(), SkillsResponseModel.class);

        return skillsResponseModel;
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
    public SkillsResponseModel editSkill(Object skillId){
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var response = RestAssured
                .given()
                .contentType("application/json")
                .queryParam("skill", faker.job().keySkills())
                .queryParam("skillId", skillId)
                .put("/skill/edit");

        SkillsResponseModel skillsResponseModel = jsonParser.fromJson(response.body().prettyPrint(), SkillsResponseModel.class);

        return skillsResponseModel;
    }
    public void deleteSkill(String skillId) {
        RestAssured.baseURI = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.api.baseUrl");

        var request = RestAssured
                .given()
                .queryParam("skillId", skillId)
                .put("/skill/delete");

    }
}
