package pages;

import models.ui.UserUiModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class PersonalProfilePage extends BasePage {
    public PersonalProfilePage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }

    public WebElement firstNameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.fieldFirstName")));
    }

    public WebElement lastNameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.fieldLastName")));
    }

    public WebElement birthdayInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.fieldBirthday")));
    }

    public WebElement genderInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.fieldGender")));
    }

    public Select cityDropDownMenuField() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey(""))));
    }

    public WebElement updateMyProfileButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("")));
    }

    public void enterRegistrationCredentials(UserUiModel userUiModel) {
        usernameInputField().sendKeys(userUiModel.getUsername());
        emailInputField().sendKeys(userUiModel.getEmail());
        passwordInputField().sendKeys(userUiModel.getPassword());
        confirmInputField().sendKeys(userUiModel.getPassword());
        categoryDropDown().selectByValue(userUiModel.getProfession());
        registerButton().click();

    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }
    public void navigateToPersonalProfilePage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.homePage.personalProfileButton");
        actions.clickElement("weAreSocialNetwork.homePage.personalProfileButton");
    }
}
