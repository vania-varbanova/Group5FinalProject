package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testFramework.Driver;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class MainPage extends BasePage {

    public MainPage(Driver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }

    public WebElement buttonByLinkText(String text) {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.navigationSectionLinks"), text);
        return driver.findElement(By.xpath(xpath));
    }
    public void assertButtonByLinkTextIsVisible(String text){
        WebElement webElement = buttonByLinkText(text);
        actions.assertElementPresent(webElement);
    }
    @Override

    public void waitForPageToLoad() {
    actions.waitForJavascript();
    }
}
