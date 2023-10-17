package pages;

import models.ui.PersonalProfileUiModel;
import models.ui.SkillUserUiModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.UiPropertiesReader;

public class EditPersonalProfilePage extends BasePage {
    public EditPersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }

    public WebElement firstNameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldFirstName")));
    }

    public WebElement lastNameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldLastName")));
    }

    public WebElement birthdayInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldBirthday")));
    }

    public Select genderDropDownMenu() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldGender"))));
    }

    public WebElement emailInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldEmail")));
    }

    private Select cityDropDownMenu() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldCity"))));
    }

    private WebElement updateMyProfileButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.updateMyProfileButton")));
    }

    private WebElement firstSkillInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldFirstSkills")));
    }

    private WebElement availabilityDropDownMenu() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldsAvailability")));
    }
    private Select categoryDropDownMenu() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.fieldCategory"))));
    }
    private WebElement updateProfessionCategoryButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.updateCategoryButton")));
    }
    private WebElement updateSkillsButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.EditPersonalProfile.updateSkillButton")));
    }

    public void updateSkillsInformation(SkillUserUiModel skillUserUiModel) {
        actions.scrollUntilElementVisible("weAreSocialNetwork.EditPersonalProfile.serviceForm");
        firstSkillInputField().sendKeys(skillUserUiModel.getFirstSkill());
        availabilityDropDownMenu().click();
        availabilityDropDownMenu().clear();
        availabilityDropDownMenu().sendKeys(skillUserUiModel.getWeeklyAvailability());
        updateSkillsButton().click();
        actions.waitFor(1000);
    }

    public void updateProfessionalInformation(PersonalProfileUiModel personalProfileUiModel) {
        firstNameInputField().sendKeys(personalProfileUiModel.getFirstName());
        lastNameInputField().sendKeys(personalProfileUiModel.getLastName());
        birthdayInputField().sendKeys(personalProfileUiModel.getBirthYear());
        genderDropDownMenu().selectByValue(personalProfileUiModel.getGender());
        emailInputField().clear();
        emailInputField().sendKeys(personalProfileUiModel.getEmail());
        cityDropDownMenu().selectByIndex(4);
        updateMyProfileButton().click();
        actions.waitForElementClickable("weAreSocialNetwork.EditPersonalProfile.updateMyProfileButton");
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }
}
