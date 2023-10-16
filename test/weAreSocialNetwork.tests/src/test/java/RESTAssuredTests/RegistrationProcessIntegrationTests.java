package RESTAssuredTests;

import annotations.IssueLink;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegistrationProcessIntegrationTests extends BaseIntegrationTest {
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("Integration")
    @Tag("RegistrationProcess")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-13")
    public void userSuccessfullyCreated_when_validCredentials() {
        assertEquals(userRequestModel.getUsername(), userResponseModel.getName(), formatErrorMessage("username"));
        assertNotNull(userResponseModel.getId());
    }
}
