package system;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.MainPage;
import testFramework.CustomWebDriverManager;
import testFramework.Driver;

public class BaseSystemTest {
    private WebDriver webDriver;
    private Driver driver;
    protected LoginPage loginPage;
    protected MainPage mainPage;

    @BeforeEach
    public void beforeEach() {
        webDriver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        driver = new Driver(webDriver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }
}
