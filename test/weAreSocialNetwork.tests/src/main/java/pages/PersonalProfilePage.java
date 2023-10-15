package pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    public void assertSkillIsVisible(String skill){
        actions.scrollUntilElementVisible("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability");
        actions.waitForElementVisible("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability");
        Assertions.assertTrue(skillTextFieldByText(skill).isDisplayed());
    }
    public WebElement buttonByDisable(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.disableButton")));
    }
    public WebElement buttonByEnable(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.enableButton")));
    }
    public WebElement buttonConnect(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.connectButton")));
    }
    public WebElement connectMessage(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.sendConnectionMessage")));
    }
    public WebElement friendRequestButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.connectionPage.newFriendRequestButton")));
    }
    public void assertConnectMessageIsVisible(){
        Assertions.assertTrue(connectMessage().isDisplayed());
    }
    public void assertButtonEnableIsVisible(){
        Assertions.assertTrue(buttonByEnable().isDisplayed());
    }
    public void assertButtonDisableIsVisible(){
        Assertions.assertTrue(buttonByDisable().isDisplayed());
    }
    public void assertWeeklyAvailability(String expectedWeeklyAvailability){
        Assertions.assertTrue(weeklyAvailabilityTextField().getText().contains(expectedWeeklyAvailability));
    }
    private WebElement skillTextFieldByText(String skill){
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.skillTextField"),skill);
        return driver.findElement(By.xpath(xpath));
    }
    private WebElement weeklyAvailabilityTextField(){
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability")));
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
