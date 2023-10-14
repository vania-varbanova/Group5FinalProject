package RESTAssuredTests;

import models.api.request.SkillsRequests;
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
    }
    @AfterEach
    public void afterEach() {
        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
    @Test
    public void skillSuccessfullyCreated() {
        String expectedProfessionId = String.valueOf(skillsRequestModel.getCategory());
        String actualProfessionId = String.valueOf(skillsResponseModel.getCategory());
        String expectedSkill = skillsRequestModel.getSkill();
        String actualSkill = skillsResponseModel.getSkill();

        Assertions.assertNotNull(skillsResponseModel.getCategory());
        Assertions.assertNotNull(skillsResponseModel.getSkill());
        Assertions.assertEquals(expectedProfessionId, actualProfessionId);
        Assertions.assertEquals(expectedSkill, actualSkill);

    }

    @Test
    public void skillSuccessfullyDeleted() {
        Assertions.assertNotNull(skillsResponseModel.getCategory());
        Assertions.assertNotNull(skillsResponseModel.getSkill());

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
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
        // Log the initial skill details
        logger.log(skillsResponseModel.toString());

        // Store the initial skill name for comparison later
        String firstSkill = skillsResponseModel.getSkill();
        Assertions.assertFalse(firstSkill.isEmpty());

        skillsRequestModel = apiDataGenerator.editSkill();
        skillsResponseModel = skillsRequests.editSkill(skillsResponseModel.getSkillId(), String.valueOf(skillsRequestModel));

        // Check if the skill was successfully edited
        if (skillsResponseModel != null) {
            // Retrieve the edited skill name
            String secondSkill = skillsResponseModel.getSkill();

            // Assert that the skill names are not equal after editing
            Assertions.assertNotEquals(firstSkill, secondSkill);

            // Additional checks
            Assertions.assertNotNull(skillsResponseModel.getCategory());
            Assertions.assertNotNull(secondSkill);
            Assertions.assertFalse(secondSkill.isEmpty());

            // Clean up: delete the edited skill
            skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
        } else {
            // Log a message if the edited skill is null
            logger.log("Edited Skill is null");
        }
    }
}
