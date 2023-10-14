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

    public void enterLoginCredentials(String username, String password) {
        usernameInputField().sendKeys(username);
        passwordInputField().sendKeys(password);
        loginButton().click();
    }

    public WebElement usernameInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldUsername")));
    }

    public WebElement passwordInputField() {
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldPassword")));
    }

    public WebElement loginButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.loginButton")));
    }

    private WebElement mainHeading() {
        actions.waitForElementPresent("weAreSocialNetwork.Login.Heading");
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.Heading")));
    }

    public void assertMainHeadingIsVisible() {
        actions.assertElementPresent(mainHeading());
        Assertions.assertEquals("Login Page", mainHeading().getText());
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }
}
