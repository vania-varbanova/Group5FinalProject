package pages;

import org.openqa.selenium.WebDriver;
import utils.ConfigPropertiesReader;

public class PersonalProfilePage extends BasePage {
    public PersonalProfilePage(WebDriver driver) {
        super(driver, ConfigPropertiesReader.getValueByKey("weAreSocialNetwork.baseUrl"));
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
