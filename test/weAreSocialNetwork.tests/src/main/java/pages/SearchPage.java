package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UiPropertiesReader;

public class SearchPage extends BasePage{
    public SearchPage(WebDriver driver) {
        super(driver, "");
    }
    public WebElement seeProfileButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.searchPage.seeProfileButton")));
    }
    @Override
    public void waitForPageToLoad() {

    }
}
