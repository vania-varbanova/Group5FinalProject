package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class AdminViewAllUsersPage extends BasePage{
    public AdminViewAllUsersPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.admin.viewAllUsers"));
    }

    public WebElement getUserByName(String username){
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.getUserByName"),username);
        return driver.findElement(By.xpath(xpath));
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();


    }
}
