package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.loginPage"));
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
        String locatorKey = "weAreSocialNetwork.Login.loginButton";
        actions.waitForElementVisible(locatorKey);
        actions.waitForElementClickable(locatorKey);
        actions.waitForElementVisible("weAreSocialNetwork.Login.loginForm");
    }

    public void enterLoginCredentials(String username, String password) {
        waitForPageToLoad();
        usernameInputField().sendKeys(username);
        passwordInputField().sendKeys(password);
        loginButton().click();
    }

    public void assertErrorMessageEquals(String expectedErrorMessage) {
        String actualErrorMessage = errorMessageTextField().getText();

        Assertions.assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    private WebElement usernameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldUsername")));
    }

    private WebElement passwordInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldPassword")));
    }

    private WebElement loginButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.loginButton")));
    }

    private WebElement errorMessageTextField() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.errorMessage")));
    }
}
