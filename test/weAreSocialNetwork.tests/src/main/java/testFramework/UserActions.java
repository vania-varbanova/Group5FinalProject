package testFramework;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.UiPropertiesReader;

import static java.lang.String.format;

public class UserActions {
    final WebDriver driver;
    final TimeoutSettings timeoutSettings;
    final Actions driverActions;

    public WebDriver getDriver() {
        return driver;
    }

    public UserActions(WebDriver driver) {
        this.driver = driver;
        timeoutSettings = new TimeoutSettings();
        driverActions = new Actions(driver);
    }
//
//    public static void loadBrowser(String baseUrlKey) {
//        Utils..(Utils.getConfigPropertyByKey(baseUrlKey));
//    }

    public static void quitDriver() {
        Utils.tearDownWebDriver();
    }

    public void clickElement(String key, Object... arguments) {
        String locator = getLocatorValueByKey(key, arguments);

        Utils.LOGGER.info("Clicking on element " + key);
        WebElement element = driver.findElement(By.xpath(locator));
        element.click();
    }

    public void typeValueInField(String value, String field, Object... fieldArguments) {
        String locator = getLocatorValueByKey(field, fieldArguments);
        WebElement element = driver.findElement(By.xpath(locator));
        element.sendKeys(value);
    }


    public void dragAndDropElement(String fromElementLocator, String toElementLocator) {
        String fromLocator = getLocatorValueByKey(fromElementLocator);
        WebElement fromElement = driver.findElement(By.xpath(fromLocator));

        String toLocator = getLocatorValueByKey(toElementLocator);
        WebElement toElement = driver.findElement(By.xpath(toLocator));

        Action dragAndDrop = driverActions.clickAndHold(fromElement)
                .moveToElement(toElement)
                .release(toElement)
                .build();
        dragAndDrop.perform();
    }

    //############# WAITS #########
    public void waitForElementVisible(String locatorKey, Object... arguments) {
        waitForElementVisibleUntilTimeout(locatorKey, arguments);
    }

    public void waitForElementClickable(String locatorKey, Object... arguments) {
        waitForElementToBeClickableUntilTimeout(locatorKey, arguments);
    }

    public void waitForJavascript() {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.jsScripts());
        JavascriptExecutor js = (JavascriptExecutor) driver;

        wait.until(webDriver -> (js.executeScript("return document.readyState").equals("complete")));
    }

    //############# WAITS #########
    public void waitForElementPresent(String locator, Object... arguments) {
        waitForElementPresenceUntilTimeout(locator, arguments);
    }

    public void assertElementPresent(WebElement webElement) {
        Assertions.assertNotNull(webElement,
                format("Element with %s doesn't present.", webElement));
    }

    public void assertElementAttribute(String locator, String attributeName, String attributeValue) {
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));
        String value = element.getAttribute(attributeName);
//        Assertions.assertEquals(format("Element with locator %s doesn't match", attributeName), getLocatorValueByKey(attributeValue), value);
    }

    private String getLocatorValueByKey(String locator) {
        return format(Utils.getUIMappingByKey(locator));
    }

    private String getLocatorValueByKey(String locator, Object[] arguments) {
        return String.format(Utils.getUIMappingByKey(locator), arguments);
    }

    private void waitForElementVisibleUntilTimeout(String locator, Object... locatorArguments) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.elementToBeVisible());
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementToBeClickableUntilTimeout(String locator, Object... locatorArguments) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.elementToBeClickable());
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }

    private void waitForElementPresenceUntilTimeout(String locator, Object... locatorArguments) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.elementToBePresent());
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
        } catch (Exception exception) {
            Assertions.fail("Element with locator: '" + xpath + "' was not found.");
        }
    }


    public void hoverElement(String key, Object... arguments) {
        // TODO: Implement the The Logger message
        var element = findElementByXpath(key);
        Utils.LOGGER.info("Hovering element with locator");
        driverActions.moveToElement(element).perform();
    }

    public void switchToIFrame(String iframe) {
        // TODO: Implement the The Logger message
        var iframeElement = findElementByXpath(iframe);
        driver.switchTo().frame(iframeElement);
    }

    public boolean isElementPresent(String locator, Object... arguments) {
        // TODO: Implement the method
        String xpath = getLocatorValueByKey(locator, arguments);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.elementToBePresent());
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isElementVisible(String locator, Object... arguments) {
        // TODO: Implement the method
        String xpath = getLocatorValueByKey(locator, arguments);
        WebDriverWait wait = new WebDriverWait(driver, timeoutSettings.elementToBeVisible());
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

    public void waitFor(long timeOutMilliseconds) {
        try {
            Thread.sleep(timeOutMilliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //############# ASSERTS #########
    public void assertNavigatedUrl(String urlKey) {
        // TODO: Implement the method
        String expectedUrl = Utils.getConfigPropertyByKey(urlKey);
        String actualUrl = driver.getCurrentUrl();

        Assertions.assertEquals(expectedUrl, actualUrl, String.format("Expected url is: %s, but actual url is %s", expectedUrl, actualUrl));
    }

    public void pressKey(Keys key) {
        Utils.LOGGER.info("Sending Key:" + key.toString());
        driverActions.sendKeys(key);
    }

    public String getElementAttribute(String key, String attributeName) {
        WebElement element = findElementByXpath(key);

        var attributeValue = element.getAttribute(attributeName);

        return attributeValue;
    }

    private WebElement findElementByXpath(String locatorKey) {
        String value = Utils.getUIMappingByKey(locatorKey);

        return driver.findElement(By.xpath(value));
    }

    public void selectOptionFromDropdown(String locator, String optionText, Object... locatorArguments) {
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        WebElement dropdownElement = driver.findElement(By.xpath(xpath));

        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(optionText);
    }

    public void scrollUntilElementVisible(String locator) {
        String xpath = getLocatorValueByKey(locator);
        WebElement element = driver.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        waitForElementVisibleUntilTimeout(locator);
    }

    public void clickElementWithJavaScript(String locator, Object... locatorArguments) {
        String xpath = getLocatorValueByKey(locator, locatorArguments);
        WebElement element = driver.findElement(By.xpath(xpath));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void assertElementPresentByXpath(String xpath) {
        try {
            WebElement element = driver.findElement(By.xpath(xpath));
            Assertions.assertNotNull(element, "Element with XPath: '" + xpath + "' was not found.");
        } catch (NoSuchElementException e) {
            Assertions.fail("Element with XPath: '" + xpath + "' was not found.");
        }
    }
}
