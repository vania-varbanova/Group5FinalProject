package integration;

import models.api.request.PostRequests;
import models.api.request.SkillsRequests;
import models.api.requestModel.SkillsRequestModel;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.SkillsResponseModel;
import models.api.responseModel.UserResponseModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.DatabaseService;

import java.sql.SQLException;

public class SkillIntegrationTests extends BaseIntegrationTest {
    private SkillsRequests skillsRequests;

    @Test
    public void skillSuccessfullyCreated_when_sendRequestWithValidBody() {

        SkillsRequestModel skillsRequestModel = apiDataGenerator.creatSkill();
        SkillsRequests skillsRequests = new SkillsRequests();
        SkillsResponseModel skillsResponseModel = skillsRequests.createSkill(skillsRequestModel);

        // Assertion 1: Check if skillId is not null
        Assertions.assertNotNull(skillsResponseModel.getSkillId());

        // Assertion 2: Check if professionId is not null
        Assertions.assertNotNull(skillsResponseModel.getCategory());

        // Assertion 3: Check if skill is not null or empty
        Assertions.assertNotNull(skillsResponseModel.getSkill());
        Assertions.assertFalse(skillsResponseModel.getSkill().isEmpty());

        // Assertion 4: Check if professionId in response matches the generated one
        String generatedProfessionId = String.valueOf(skillsRequestModel.getCategory());
        String actualProfessionId = String.valueOf(skillsResponseModel.getCategory());
        Assertions.assertEquals(generatedProfessionId, actualProfessionId);

        // Step 2: Clean up - Delete the created skill
        skillsRequests.deleteSkill(String.valueOf(skillsResponseModel.getSkillId()));
    }
}
