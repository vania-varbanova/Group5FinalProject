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

    public WebElement usernameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldName")));
    }

    public WebElement emailInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldEmail")));
    }

    public WebElement passwordInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldPassword")));
    }

    public WebElement confirmInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.fieldConfirmPassword")));
    }

    public Select categoryDropDown() {
        return new Select(driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.categoryIdButton"))));
    }

    public WebElement registerButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Registration.registerButton")));
    }

    public void enterRegistrationCredentials(UserUiModel userUiModel) {
        usernameInputField().sendKeys(userUiModel.getUsername());
        emailInputField().sendKeys(userUiModel.getEmail());
        passwordInputField().sendKeys(userUiModel.getPassword());
        confirmInputField().sendKeys(userUiModel.getConfirmationPassword());
        categoryDropDown().selectByValue(userUiModel.getProfession());
        registerButton().click();

    }

    public WebElement errorMessage() {
        String xpath = "";
        return driver.findElement(By.xpath(xpath));
    }

    public WebElement messageByLinkText() {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.navigationSectionText"));
        return driver.findElement(By.xpath(xpath));
    }

    public void assertMessageByLinkTextIsVisible() {
        WebElement webElement = messageByLinkText();
        actions.assertElementPresent(webElement);
    }

    public WebElement buttonByLinkText() {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.navigationSectionButton"));
        return driver.findElement(By.xpath(xpath));
    }

    public void assertButtonByLinkTextIsClickable() {
        WebElement webElement = buttonByLinkText();
        actions.assertElementPresent(webElement);
    }

    public void assertErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = errorMessage().getText();

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }
}
