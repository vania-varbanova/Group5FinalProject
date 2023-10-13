package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testFramework.Driver;
import utils.ConfigPropertiesReader;
import utils.UiDataGenerator;
import utils.UiPropertiesReader;

public class LoginPage extends BasePage{

    UiDataGenerator uiDataGenerator;

    public LoginPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.loginPage"));
    }

    public void enterLoginCredentials(String username, String password) {
        usernameInputField().sendKeys(username);
        passwordInputField().sendKeys(password);
        loginButton().click();
    }

    public WebElement usernameInputField(){
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldUsername")));
    }

    public WebElement passwordInputField(){
        return driver.findElement(By.id(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.fieldPassword")));
    }
    public WebElement loginButton(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.Login.loginButton")));
    }


    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }
}
