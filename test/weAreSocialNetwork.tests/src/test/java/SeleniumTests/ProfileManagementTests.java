package SeleniumTests;

import annotations.IssueLink;
import models.api.request.UserRequests;
import models.api.requestModel.UserRequestModel;
import models.api.responseModel.UserResponseModel;
import models.ui.PersonalProfileUiModel;
import models.ui.SkillUserUiModel;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.EditPersonalProfilePage;
import pages.MainPage;
import pages.PersonalProfilePage;
import services.DatabaseService;
import testFramework.CustomWebDriverManager;
import utils.ApiDataGenerator;
import utils.UiDataGenerator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ProfileManagementTests extends BaseSystemTest {

    private UserRequests userRequests;
    private ApiDataGenerator apiDataGenerator;
    private UiDataGenerator uiDataGenerator;
    private DatabaseService databaseService;
    private UserResponseModel userResponseModel;
    private UserRequestModel userRequestModel;
    private MainPage mainPage;
    private PersonalProfilePage personalProfilePage;
    private EditPersonalProfilePage editPersonalProfilePage;
    private PersonalProfileUiModel personalProfileUiModel;
    private SkillUserUiModel skillUserUiModel;

    @Override
    @BeforeEach
    public void beforeEach() {
        super.beforeEach();
        userRequests = new UserRequests();
        apiDataGenerator = new ApiDataGenerator();
        uiDataGenerator = new UiDataGenerator();
        personalProfileUiModel = uiDataGenerator.createPersonalProfile(true);
        databaseService = new DatabaseService();
        userRequestModel = apiDataGenerator.createUserWithRoleUser();
        userResponseModel = userRequests.createUser(userRequestModel);
        loginPage.navigateToPage();
        loginPage.enterLoginCredentials(userRequestModel.getUsername(), userRequestModel.getPassword());
        mainPage = new MainPage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        mainPage.navigateToPage();
        personalProfilePage = new PersonalProfilePage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        personalProfilePage.navigateToPersonalProfilePage();
        personalProfilePage.editProfileButton().click();
        editPersonalProfilePage = new EditPersonalProfilePage(CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver());
        editPersonalProfilePage.waitForPageToLoad();
        skillUserUiModel = uiDataGenerator.createSkills();

    }

    @Override
    @AfterEach
    public void afterEach() {
        super.afterEach();
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-171")
    public void userSuccessfullyEditPersonalData_when_validCredentials() {

        editPersonalProfilePage.updateProfessionalInformation(personalProfileUiModel);
        personalProfilePage.navigateToPersonalProfilePage();

        String expectedResultName = String.format("%s %s", personalProfileUiModel.getFirstName(), personalProfileUiModel.getLastName());
        personalProfilePage.assertColumnValueEquals("Name", expectedResultName);
        String expectedResultEmail = personalProfileUiModel.getEmail();
        personalProfilePage.assertColumnValueEquals("Email", expectedResultEmail);

    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-19")
    public void userSuccessfullyEditFirstAndLastNames() {
        editPersonalProfilePage.updateProfessionalInformation(personalProfileUiModel);
        personalProfilePage.navigateToPersonalProfilePage();

        String expectedResult = String.format("%s %s", personalProfileUiModel.getFirstName(), personalProfileUiModel.getLastName());
        personalProfilePage.assertColumnValueEquals("Name", expectedResult);
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-22")
    public void userSuccessfullyEditEmail() {
        editPersonalProfilePage.updateProfessionalInformation(personalProfileUiModel);
        personalProfilePage.navigateToPersonalProfilePage();

        String expectedResult = personalProfileUiModel.getEmail();
        personalProfilePage.assertColumnValueEquals("Email", expectedResult);
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-23")
    public void userSuccessfullyEditBirthday() throws ParseException {
        editPersonalProfilePage.updateProfessionalInformation(personalProfileUiModel);
        personalProfilePage.navigateToPersonalProfilePage();
        DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String expectedResult = personalProfileUiModel.getBirthYear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        LocalDate date = LocalDate.parse(expectedResult, formatter);
        String formattedString = date.format(sdf);

        personalProfilePage.assertColumnValueEquals("birthday", formattedString);
    }


    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-95")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-157")
    public void popupDisplayed_when_enterDateInFuture() {

    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-146")
    public void userSuccessfullyUpdateSkills() {
        editPersonalProfilePage.updateSkillsInformation(skillUserUiModel);
        personalProfilePage.assertSkillIsVisible(skillUserUiModel.getFirstSkill());
        personalProfilePage.assertWeeklyAvailability(skillUserUiModel.getWeeklyAvailability());
    }

    @Test
    @Tag("System")
    @Tag("ProfileManagementActions")
    @IssueLink(jiraLink = "https://wearesocialfinalproject.atlassian.net/browse/WSFP-147")
    @Ignore(value = "Bug reported: https://wearesocialfinalproject.atlassian.net/browse/WSFP-158")
    public void userSuccessfullyUpdateAvailability() {

    }


}
