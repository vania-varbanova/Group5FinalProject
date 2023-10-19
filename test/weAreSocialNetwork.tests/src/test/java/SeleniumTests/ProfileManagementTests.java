package SeleniumTests;

import annotations.Bug;
import annotations.Issue;
import models.api.requestModel.UserRequestModel;
import models.ui.PersonalProfileUiModel;
import models.ui.SkillUserUiModel;
import org.junit.Ignore;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProfileManagementTests extends BaseSystemTest {
    private UserRequestModel user;
    private PersonalProfileUiModel personalProfileInformation;
    private SkillUserUiModel skillInformation;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        personalProfileInformation = uiDataGenerator.createPersonalProfile(true);
        user = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(user);

        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(user.getUsername(), user.getPassword());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.clickEditProfileButton();
        editPersonalProfilePage.updateProfessionalInformation(personalProfileInformation);
        personalProfilePage.navigateToPersonalProfilePage();
    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
        databaseService.deleteUserWithId(userResponseModel.getId());
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-171")
    public void userSuccessfullyEditPersonalData_when_fillFieldsWithValidInformation() {
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());
        String expectedEmail = personalProfileInformation.getEmail();

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
        personalProfilePage.assertColumnValueEquals("Email", expectedEmail);
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-19")
    public void userSuccessfullyUpdateFirstAndLastName_when_fillFirstNameAndLastNameFields() {
        String expectedName = String.format("%s %s", personalProfileInformation.getFirstName(), personalProfileInformation.getLastName());

        personalProfilePage.assertColumnValueEquals("Name", expectedName);
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-22")
    public void userSuccessfullyUpdateEmail_when_fillEmailField() {
        String expectedEmail = personalProfileInformation.getEmail();

        personalProfilePage.assertColumnValueEquals("Email", expectedEmail);
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-23")
    public void userSuccessfullyUpdateBirthDate_when_fillBirthDateField() {
        DateTimeFormatter birthDayFieldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter birthDayUiFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String birthDate = personalProfileInformation.getBirthYear();
        LocalDate date = LocalDate.parse(birthDate, birthDayUiFormat);
        String expectedBirthDate = date.format(birthDayFieldFormat);

        personalProfilePage.assertColumnValueEquals("birthday", expectedBirthDate);
    }


    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-95")
    @Bug(key = "WSFP-157")
    @Disabled
    public void errorMessageDisplayed_when_fillBirthdateWithFutureDate() {
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-146")
    public void userSuccessfullyUpdateSkill_when_fillSkillForm() {
        skillInformation = uiDataGenerator.createSkills();
        personalProfilePage.clickEditProfileButton();

        editPersonalProfilePage.updateSkillsInformation(skillInformation);

        personalProfilePage.assertSkillIsVisible(skillInformation.getFirstSkill());
        personalProfilePage.assertWeeklyAvailability(skillInformation.getWeeklyAvailability());
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @Issue(key = "WSFP-147")
    @Bug(key = "WSFP-158")
    @Disabled
    public void weeklyAvailabilityDisplayed_when_userUpdateTheData() {
    }
}
