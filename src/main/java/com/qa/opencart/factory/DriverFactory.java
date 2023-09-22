package com.qa.opencart.factory;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.io.FileHandler.*;


/**
 * This class is to initilize the driver and intput stream
 *
 * @author viswa
 */
public class DriverFactory {
    WebDriver driver;
    Properties properties;
    OptionsManager optionsManager;
    public static String highlight;
    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<WebDriver>();

    /**
     * This is to Initilize Driver
     *
     * @param properties
     * @return driver
     */
    public WebDriver initDriver(Properties properties) {
        String browserName = properties.getProperty("browser");
        System.out.println("Browser Name is:  " + browserName);

        highlight = properties.getProperty("highlight");
        optionsManager = new OptionsManager(properties);

        switch (browserName.trim().toLowerCase()) {
            case "chrome":
                // driver = new ChromeDriver(optionsManager.getChromeOption());
                threadLocal.set(new ChromeDriver(optionsManager.getChromeOption()));
                break;

            case "firefox":
                //   driver = new FirefoxDriver(optionsManager.getCFirefoxOption());
                threadLocal.set(new FirefoxDriver(optionsManager.getCFirefoxOption()));
                break;
            case "edge":
                // driver = new EdgeDriver(optionsManager.getEdgeOptions());
                threadLocal.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                break;
            default:
                System.out.println("Enter correct Browser Name" + browserName);
        }
        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();
        getDriver().get(properties.getProperty("url"));
        return getDriver();
    }

    public static WebDriver getDriver() {
        return threadLocal.get();
    }

    /**
     * This Method is used to initialize the Properties
     *
     * @return Properties
     */
    public Properties initProp() {

        FileInputStream fileInputStream = null;
        properties = new Properties();

        String envName = System.getProperty("env");
        System.out.println("env name : " + envName);
        try {
            if (envName == null) {
                System.out.println("no env given hence running on QA");
                fileInputStream = new FileInputStream("C:\\Selenium Projects\\March2023POMwithFrameWork-master\\March2023POMwithFrameWork-master\\src\\test\\resources\\config\\config.properties");
            } else {

                switch (envName.toLowerCase().trim()) {
                    case "qa":
                        fileInputStream = new FileInputStream(".\\src\\test\\resources\\config\\qa.config.properties");
                        break;
                    case "dev":
                        fileInputStream = new FileInputStream(".\\src\\test\\resources\\config\\dev.config.properties");
                        break;
                    case "uat":
                        fileInputStream = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
                        break;
                    case "prod":
                        fileInputStream = new FileInputStream(".\\src\\test\\resources\\config\\uat.config.properties");
                        break;
                    default:
                        System.out.println("please pass correct env...not" + envName);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return properties;

    }

    /**
     * Take Screenshot
     *
     * @param methodName
     * @return
     */

    public static String getScreenshot(String methodName) {
        File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis() + ".png";
        System.out.println("user directory: " + System.getProperty("user.dir"));
        System.out.println("screenshot path: " + path);
        File destination = new File(path);

        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

}


