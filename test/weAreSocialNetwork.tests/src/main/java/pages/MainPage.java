package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import testFramework.Driver;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
    }

    public WebElement buttonByLinkText(String text) {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.navigationSectionLinks"), text);
        return driver.findElement(By.xpath(xpath));
    }
    public void assertAdminZoneButtonIsVisible(){
        Assertions.assertTrue(adminZoneButton().isDisplayed());
    }

    public WebElement adminZoneButton(){
        return driver.findElement(By.xpath((UiPropertiesReader.getValueByKey("weAreSocialNetwork.homePage.adminZoneButton"))));
    }
    public WebElement viewUserButton(){
        return driver.findElement(By.xpath((UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.viewUsersButton"))));
    }
    public WebElement searchFieldByName(){
        return driver.findElement(By.xpath((UiPropertiesReader.getValueByKey("weAreSocialNetwork.homePage.searchFieldByName"))));
    }
    public WebElement searchButton(){
        return driver.findElement(By.xpath((UiPropertiesReader.getValueByKey("weAreSocialNetwork.homePage.searchButton"))));
    }
    public void clickButtonByLinkText(String text) {
        buttonByLinkText(text).click();
    }

    public void assertButtonByLinkTextIsVisible(String text) {
        WebElement webElement = buttonByLinkText(text);
        actions.assertElementPresent(webElement);
    }

    public void assertTitleIsVisible(String title) {
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();
    }

    public void navigateToAboutUsPage() {
        waitForPageToLoad();
        actions.waitForElementVisible("weAreSocialNetwork.homePage.aboutUsButton");
        actions.clickElement("weAreSocialNetwork.homePage.aboutUsButton");
        actions.waitForElementVisible("weAreSocialNetwork.aboutUsPage.title");
    }
}
