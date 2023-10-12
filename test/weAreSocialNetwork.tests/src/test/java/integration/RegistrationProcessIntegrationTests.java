package integration;

import annotations.IssueLink;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationProcessIntegrationTests extends BaseIntegrationTest {
    @Override
    public void beforeEach() {
        super.beforeEach();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
    }

    @Test
    @Tag("Integration")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-23")
    public void userSuccessfullyCreated_when_validInformation() {
        assertEquals(userRequestModel.getUsername(), userResponseModel.getName(), formatErrorMessage("username"));
        assertNotNull(userResponseModel.getId());
    }
}
