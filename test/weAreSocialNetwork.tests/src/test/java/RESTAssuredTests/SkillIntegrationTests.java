package RESTAssuredTests;

import io.restassured.response.Response;
import models.api.request.SkillsRequests;
import models.api.requestModel.EditSkillRequestModel;
import models.api.requestModel.SkillsRequestModel;
import models.api.responseModel.SkillsResponseModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ApiDataGenerator;
import utils.ConsoleLogger;

import java.util.List;


public class SkillIntegrationTests extends BaseIntegrationTest {

    private ConsoleLogger logger = new ConsoleLogger();
    @Override
    @BeforeEach
    public void beforeEach(){
        super.beforeEach();
        skillsRequestModel = apiDataGenerator.createSkill();
        skillsResponseModel = skillsRequests.createSkill(skillsRequestModel);
        Assertions.assertNotNull(skillsResponseModel.getCategory());
        Assertions.assertNotNull(skillsResponseModel.getSkill());
    }

    @Test
    public void skillSuccessfullyCreated() {
        String expectedProfessionId = String.valueOf(skillsRequestModel.getCategory());
        String actualProfessionId = String.valueOf(skillsResponseModel.getCategory());
        String expectedSkill = skillsRequestModel.getSkill();
        String actualSkill = skillsResponseModel.getSkill();

        Assertions.assertEquals(expectedProfessionId, actualProfessionId);
        Assertions.assertEquals(expectedSkill, actualSkill);

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }

    @Test
    public void skillSuccessfullyDeleted() {
        String skillIdToDelete = String.valueOf(skillsResponseModel.getSkillId());

        Response deleteResponse = skillsRequests.deleteSkill(skillIdToDelete);

        Assertions.assertEquals(200, deleteResponse.getStatusCode(), "Skill deletion was not successful. Status code: " + deleteResponse.getStatusCode());
        }


    @Test
    public void FindAllSkillsRequestSuccessfullyProvidesAllSkills() {
        List<SkillsResponseModel> skillsResponseModels = skillsRequests.getAllSkills();

        for (SkillsResponseModel skillsResponseModel : skillsResponseModels) {
            Assertions.assertNotNull(skillsResponseModel.getSkill());
            Assertions.assertNotNull(skillsResponseModel.getCategory());

            skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
        }
    }

    @Test
    public void GetOneSkillRequestSuccessfullyProvidesOneSkill() {
        SkillsResponseModel retrievedSkill = skillsRequests.getOneSkill(skillsResponseModel.getSkillId());

        Assertions.assertNotNull(retrievedSkill);
        Assertions.assertEquals(skillsResponseModel.getSkill(), retrievedSkill.getSkill());
        Assertions.assertEquals(skillsResponseModel.getSkillId(), retrievedSkill.getSkillId());

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }

    @Test
    public void skillSuccessfullyEdited() {
        EditSkillRequestModel editSkillRequestModel = apiDataGenerator.editSkill();
        String skillId = String.valueOf(skillsResponseModel.getSkillId());
        Response editResponse = skillsRequests.editSkill(skillId, editSkillRequestModel.getSkill());

        SkillsResponseModel updatedSkill = skillsRequests.getOneSkill(skillId);

        Assertions.assertEquals(200, editResponse.getStatusCode(), "Skill edit was not successful. Status code: " + editResponse.getStatusCode());
        Assertions.assertEquals(editSkillRequestModel.getSkill(), updatedSkill.getSkill(), "Edited skill value does not match expected value.");

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
}
