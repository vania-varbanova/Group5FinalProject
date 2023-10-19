package RESTAssuredTests;

import annotations.Issue;
import io.restassured.response.Response;
import models.api.requestModel.EditSkillRequestModel;
import models.api.responseModel.SkillsResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import utils.ConsoleLogger;

import java.util.List;


public class SkillIntegrationTests extends BaseIntegrationTest {

    private ConsoleLogger logger = new ConsoleLogger();
    private static final String DELETION_MESSAGE = "Skill deletion was not successful. Status code: ";
    private static final String EDIT_SKILL_MESSAGE = "Skill edit was not successful. Status code: ";
    private static final String EDITED_SKILL_VALUE_MESSAGE = "Edited skill value does not match expected value.";
    private static final String CATEGORY_IS_NULL = "Category is null";
    private static final String SKILL_IS_NULL = "Skill value is null";
    private static final String RETRIEVED_SKILL_VALUE_MESSAGE = "Retrieved skill value does not match expected value.";
    private static final String RETRIEVED_SKILL_ID_MESSAGE = "Retrieved skill id does not match expected value.";


    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        skillsRequestModel = apiDataGenerator.createSkill();
        skillsResponseModel = skillsRequests.createSkill(skillsRequestModel);
        Assertions.assertNotNull(skillsResponseModel.getCategory(), CATEGORY_IS_NULL);
        Assertions.assertNotNull(skillsResponseModel.getSkill(), SKILL_IS_NULL);
    }

    @Test
    @Tag("Integration")
    @Tag("ProfileManagementAction")
    @Issue(key = "WSFP-146")
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

        Assertions.assertEquals(200, deleteResponse.getStatusCode(), DELETION_MESSAGE + deleteResponse.getStatusCode());
    }


    @Test
    public void FindAllSkillsRequestSuccessfullyProvidesAllSkills() {
        List<SkillsResponseModel> skillsResponseModels = skillsRequests.getAllSkills();

        for (SkillsResponseModel skillsResponseModel : skillsResponseModels) {
            Assertions.assertNotNull(skillsResponseModel.getSkill(), SKILL_IS_NULL);
            Assertions.assertNotNull(skillsResponseModel.getCategory(), CATEGORY_IS_NULL);

            skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
        }
    }

    @Test
    public void GetOneSkillRequestSuccessfullyProvidesOneSkill() {
        SkillsResponseModel retrievedSkill = skillsRequests.getOneSkill(skillsResponseModel.getSkillId());

        Assertions.assertNotNull(retrievedSkill);
        Assertions.assertEquals(skillsResponseModel.getSkill(), retrievedSkill.getSkill(), RETRIEVED_SKILL_VALUE_MESSAGE);
        Assertions.assertEquals(skillsResponseModel.getSkillId(), retrievedSkill.getSkillId(), RETRIEVED_SKILL_ID_MESSAGE);

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }

    @Test
    public void skillSuccessfullyEdited() {
        EditSkillRequestModel editSkillRequestModel = apiDataGenerator.editSkill();
        String skillId = String.valueOf(skillsResponseModel.getSkillId());
        Response editResponse = skillsRequests.editSkill(skillId, editSkillRequestModel.getSkill());

        SkillsResponseModel updatedSkill = skillsRequests.getOneSkill(skillId);

        Assertions.assertEquals(200, editResponse.getStatusCode(), EDIT_SKILL_MESSAGE + editResponse.getStatusCode());
        Assertions.assertEquals(editSkillRequestModel.getSkill(), updatedSkill.getSkill(), EDITED_SKILL_VALUE_MESSAGE);

        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
}
