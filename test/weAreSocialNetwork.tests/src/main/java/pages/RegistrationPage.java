package pages;

import models.ui.UserUiModel;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.registrationPage"));
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }

    public void enterRegistrationCredentials(UserUiModel userUiModel) {
        usernameInputField().sendKeys(userUiModel.getUsername());
        emailInputField().sendKeys(userUiModel.getEmail());
        passwordInputField().sendKeys(userUiModel.getPassword());
        confirmInputField().sendKeys(userUiModel.getConfirmationPassword());
        categoryDropDown().selectByValue(userUiModel.getProfession());
        registerButton().click();
        
        actions.waitForElementVisible("weAreSocialNetwork.heading");
    }

    public void assertErrorMessageEquals(String expectedErrorMessage) {
        String actualErrorMessage = errorMessageTextField().getText();
        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    private WebElement usernameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldName")));
    }

    private WebElement emailInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldEmail")));
    }

    private WebElement passwordInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldPassword")));
    }

    private WebElement confirmInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldConfirmPassword")));
    }

    private Select categoryDropDown() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.categoryIdButton"))));
    }

    private WebElement registerButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.registerButton")));
    }

    private WebElement errorMessageTextField() {
        String xpath = UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.errorMessageTextField");
        return driver.findElement(By.xpath(xpath));
    }
}
