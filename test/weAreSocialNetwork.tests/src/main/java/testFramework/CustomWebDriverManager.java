package testFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.BrowserType;

import static utils.BrowserType.*;

public class CustomWebDriverManager {

    public enum CustomWebDriverManagerEnum {
        INSTANCE;
        private WebDriver driver;

        public void quitDriver() {
            if (driver != null) {
                driver.quit();
                driver = null;
            }
        }

        public WebDriver getDriver(BrowserType browserType) {
            if (driver == null) {
                setupBrowser(browserType);
            }
            return driver;
        }
        private   WebDriver startBrowser(BrowserType browserType) {
            // Setup Browser
            switch (browserType){
                case CHROME:
                    ChromeOptions chromeOptions = new ChromeOptions();
                    return new ChromeDriver(chromeOptions);
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    return new FirefoxDriver(firefoxOptions);
                case EDGE:
                    EdgeOptions edgeOptions = new EdgeOptions();
                    return new EdgeDriver(edgeOptions);
            }

            return null;
        }

        private WebDriver setupBrowser(BrowserType browserType) {
            WebDriver driver = startBrowser(browserType);
            driver.manage().window().maximize();
            this.driver = driver;
            return driver;
        }

    }
}
