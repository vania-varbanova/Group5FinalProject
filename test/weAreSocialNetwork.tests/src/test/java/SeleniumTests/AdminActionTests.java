package SeleniumTests;

import annotations.Issue;
import models.api.requestModel.UserRequestModel;
import models.ui.PersonalProfileUiModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class AdminActionTests extends BaseSystemTest {
    private UserRequestModel admin;
    private UserRequestModel user;
    private PersonalProfileUiModel personalProfileInformation;


    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        user = apiDataGenerator.createUserWithRoleUser();
        admin = apiDataGenerator.createUserWithRoleAdmin();
        userRequests.createUser(user);
        userRequests.createUser(admin);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(admin.getUsername(), admin.getPassword());
        adminViewAllUsersPage.navigateToPage();
        adminViewAllUsersPage.clickSeeProfileButtonByUsername(user.getUsername());
    }


    @Override
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-80")
    public void adminSuccessfullyDisableUser() throws InterruptedException {
        personalProfilePage.clickDisableButton();

        personalProfilePage.assertButtonEnableIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-81")
    public void adminSuccessfullyEnableUser() {
        personalProfilePage.clickDisableButton();

        personalProfilePage.clickEnableButton();

        personalProfilePage.assertButtonDisableIsVisible();
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-70")
    public void adminSuccessfullyEditNameUser() throws InterruptedException {
        personalProfileInformation = uiDataGenerator.createPersonalProfile(true);
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());
        personalProfilePage.clickEditProfileButton();

        editPersonalProfilePage.updateProfessionalInformation(personalProfileInformation);
        personalProfilePage.navigateToPersonalProfilePage();

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
    }

    @Test
    @Tag("System")
    @Tag("AdminActionProcess")
    @Issue(key = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-75")
    public void adminSuccessfullyEditProfessionUser() throws InterruptedException {
        personalProfileInformation = uiDataGenerator.createPersonalProfile(true);
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());
        personalProfilePage.clickEditProfileButton();
        var skillInformation = uiDataGenerator.createSkills();


        editPersonalProfilePage.updateProfessionalInformation(personalProfileInformation);
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.clickEditProfileButton();
        editPersonalProfilePage.updateSkillsInformation(skillInformation);
        personalProfilePage.navigateToPersonalProfilePage();

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
    }
}
