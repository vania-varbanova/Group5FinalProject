package pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.ConfigPropertiesReader;
import utils.UiPropertiesReader;

public class PersonalProfilePage extends BasePage {


    public PersonalProfilePage(WebDriver driver) {
        super(driver, "");
    }

    private WebElement editProfileButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.editProfileButton")));

    }
    public void clickEnableButton(){
        actions.waitForElementVisible("weAreSocialNetwork.adminPage.enableButton");
        enableButton().click();
    }

    public void clickDisableButton() {
        actions.waitForElementVisible("weAreSocialNetwork.adminPage.disableButton");
        disableButton().click();
    }

    public void clickEditProfileButton() {
        actions.waitForElementVisible("weAreSocialNetwork.PersonalProfile.editProfileButton");
        editProfileButton().click();
    }

    public void clickConnectButton() {
        connectButton().click();
    }

    public void clickDisconnectButton() {
        actions.waitForElementVisible("weAreSocialNetwork.PersonalProfile.disconnectButton");
        disconnectButton().click();
    }

    public void clickFriendRequestButton() {
        friendRequestButton().click();
        actions.waitForJavascript();
    }

    private WebElement textFieldByColumnLabel(String columnLabel) {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.informationTextField"), columnLabel);
        return driver.findElement(By.xpath(xpath));
    }

    public void assertColumnValueEquals(String columnName, String expectedResult) {
        String actualResult = textFieldByColumnLabel(columnName).getText();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    public void assertSkillIsVisible(String skill) {
        actions.scrollUntilElementVisible("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability");
        actions.waitForElementVisible("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability");
        Assertions.assertTrue(skillTextFieldByText(skill).isDisplayed());
    }

    private WebElement disableButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.disableButton")));
    }

    public WebElement enableButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.enableButton")));
    }

    private WebElement connectButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.adminPage.connectButton")));
    }

    public WebElement disconnectButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.disconnectButton")));
    }

    public WebElement connectMessage() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.sendConnectionMessage")));
    }

    public WebElement friendRequestButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.connectionPage.newFriendRequestButton")));
    }

    public void assertConnectButtonIsVisible() {
        actions.waitForElementVisible("weAreSocialNetwork.adminPage.connectButton");
        Assertions.assertTrue(connectButton().isDisplayed());
    }

    public void assertConnectMessageIsVisible() {
        Assertions.assertTrue(connectMessage().isDisplayed());
    }

    public void assertButtonEnableIsVisible() {
        actions.waitForElementVisible("weAreSocialNetwork.adminPage.enableButton");
        Assertions.assertTrue(enableButton().isDisplayed());
    }

    public void assertButtonDisableIsVisible() {
        actions.waitForElementVisible("weAreSocialNetwork.adminPage.disableButton");
        Assertions.assertTrue(disableButton().isDisplayed());
    }

    public void assertWeeklyAvailability(String expectedWeeklyAvailability) {
        Assertions.assertTrue(weeklyAvailabilityTextField().getText().contains(expectedWeeklyAvailability));
    }
    public void assertProfession(String expectedProfession) {
        Assertions.assertTrue(professionTextField().getText().contains(expectedProfession));
    }

    private WebElement skillTextFieldByText(String skill) {
        String xpath = String.format(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.skillTextField"), skill);
        return driver.findElement(By.xpath(xpath));
    }

    private WebElement weeklyAvailabilityTextField() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.skillWeeklyAvailability")));
    }
    private WebElement professionTextField() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.PersonalProfile.professionTextField")));
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

    public void navigateToConcreteUser(String userId) {
        String formatter = ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.concreteUser");
        url = String.format(formatter, userId);
        navigateToPage();
    }

}
