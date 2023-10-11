package integration;

import models.api.request.SkillsRequests;
import models.api.requestModel.SkillsRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.SkillsResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.DatabaseService;
import utils.ApiDataGenerator;


public class SkillIntegrationTests {

    @Test
    public void skillSuccessfullyCreated() {
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        SkillsRequests skillsRequests = new SkillsRequests();
        SkillsRequestModel skillsRequestModel = apiDataGenerator.createSkill();
        SkillsResponseModel skillsResponseModel = skillsRequests.createSkill(skillsRequestModel);

        Assertions.assertNotNull(skillsResponseModel.getCategory());
        Assertions.assertNotNull(skillsResponseModel.getSkill());
        Assertions.assertFalse(skillsResponseModel.getSkill().isEmpty());

        String expectedProfessionId = String.valueOf(skillsRequestModel.getCategory());
        String actualProfessionId = String.valueOf(skillsResponseModel.getCategory());
        Assertions.assertEquals(expectedProfessionId, actualProfessionId);

        String expectedSkill = skillsRequestModel.getSkill();
        String actualSkill = skillsResponseModel.getSkill();
        Assertions.assertEquals(expectedSkill, actualSkill);

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
    @Test
    public void skillSuccessfullyDeleted() {
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        SkillsRequests skillsRequests = new SkillsRequests();
        SkillsRequestModel skillsRequestModel = apiDataGenerator.createSkill();
        SkillsResponseModel skillsResponseModel = skillsRequests.createSkill(skillsRequestModel);

        Assertions.assertNotNull(skillsResponseModel.getCategory());
        Assertions.assertNotNull(skillsResponseModel.getSkill());
        Assertions.assertFalse(skillsResponseModel.getSkill().isEmpty());

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
    @Test
    public void FindAllSkillsRequestSuccessfullyProvidesAllSkills() {
        SkillsRequests skillsRequests = new SkillsRequests();

        SkillsResponseModel skillsResponseModel = skillsRequests.getAllSkills();

        Assertions.assertNotNull(skillsResponseModel.getSkill());
        Assertions.assertFalse(skillsResponseModel.getSkill().isEmpty());
        Assertions.assertNotNull(skillsResponseModel.getCategory());

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
    @Test
    public void GetOneSkillRequestSuccessfullyProvidesOneSkill() {
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        SkillsRequests skillsRequests = new SkillsRequests();

        SkillsRequestModel skillsRequestModel = apiDataGenerator.createSkill();
        SkillsResponseModel createdSkill = skillsRequests.createSkill(skillsRequestModel);

        SkillsResponseModel retrievedSkill = skillsRequests.getOneSkill(createdSkill.getSkillId());

        Assertions.assertNotNull(retrievedSkill);

        Assertions.assertEquals(createdSkill.getSkill(), retrievedSkill.getSkill());
        Assertions.assertEquals(createdSkill.getSkillId(), retrievedSkill.getSkillId());

        skillsRequests.deleteSkill(String.valueOf(createdSkill.getSkillId()));
    }
    @Test
    public void skillSuccessfullyEdited() {
        ApiDataGenerator apiDataGenerator = new ApiDataGenerator();
        SkillsRequests skillsRequests = new SkillsRequests();
        SkillsRequestModel skillsRequestModel = apiDataGenerator.createSkill();
        SkillsResponseModel createdSkill = skillsRequests.createSkill(skillsRequestModel);

        String firstSkill = createdSkill.getSkill();
        Assertions.assertFalse(firstSkill.isEmpty());

        createdSkill = skillsRequests.editSkill(createdSkill.getSkillId());

        String secondSkill = createdSkill.getSkill();

        Assertions.assertNotEquals(firstSkill, secondSkill);
        Assertions.assertNotNull(createdSkill.getCategory());
        Assertions.assertNotNull(secondSkill);
        Assertions.assertFalse(secondSkill.isEmpty());

        skillsRequests.deleteSkill(String.valueOf(createdSkill.getSkillId()));
    }
}
