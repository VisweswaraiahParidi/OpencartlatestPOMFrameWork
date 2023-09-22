package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.io.FileNotFoundException;
import java.util.Properties;

public class BaseTest {

    protected WebDriver driver;
    protected Properties properties;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;
    protected AccountPage accountPage;
    protected SearchResultsPage searchResultsPage;
    protected ProductInfoPage productInfoPage;
    DriverFactory driverFactory;
    protected SoftAssert softAssert;

    @BeforeTest
    public void setup() throws FileNotFoundException {
        driverFactory = new DriverFactory();
        properties = driverFactory.initProp();
        driver = driverFactory.initDriver(properties);
        loginPage = new LoginPage(driver);
        softAssert = new SoftAssert();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
