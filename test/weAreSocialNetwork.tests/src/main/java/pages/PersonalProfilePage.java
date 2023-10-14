package pages;

import models.ui.UserUiModel;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class PersonalProfilePage extends BasePage {


    public PersonalProfilePage(WebDriver driver) {
        super(driver,"");
    }
    public WebElement editProfileButton(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.editProfileButton")));

    }
    private WebElement textFieldByColumnLabel(String columnLabel){
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.informationTextField"),columnLabel);
        return driver.findElement(By.xpath(xpath));
    }
    public void assertColumnValueEquals(String columnName , String expectedResult){
        String actualResult = textFieldByColumnLabel(columnName).getText();
        Assertions.assertEquals(expectedResult,actualResult);

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
