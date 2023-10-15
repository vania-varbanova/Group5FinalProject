package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.UiPropertiesReader;

public class ConnectionPage extends BasePage{
    public ConnectionPage(WebDriver driver) {
        super(driver,"");
    }

    public WebElement approveButton() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.connectionPage.approveRequestButton")));
    }
    public WebElement noRequestMessage() {
        return driver.findElement(By.xpath(UiPropertiesReader.getValueByKey("weAreSocialNetwork.connectionPage.noRequestMessage")));
    }
    public void assertNoRequestMessageIsVisible(){
        Assertions.assertTrue(noRequestMessage().isDisplayed());
    }

    @Override
    public void waitForPageToLoad() {
        actions.waitForJavascript();

    }
}
