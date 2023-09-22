package com.qa.opencart.factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Properties;

public class OptionsManager {

    private ChromeOptions chromeOptions;
    private FirefoxOptions firefoxOptions;
    private EdgeOptions edgeOptions;

    private Properties properties;

    public OptionsManager(Properties properties) {
        this.properties = properties;
    }

    ;

    public ChromeOptions getChromeOption() {
        chromeOptions = new ChromeOptions();
        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            chromeOptions.addArguments("--headless=new");
        }
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            chromeOptions.addArguments("--incognito");
        }
        return chromeOptions;
    }

    public FirefoxOptions getCFirefoxOption() {
        firefoxOptions = new FirefoxOptions();
        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            firefoxOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            firefoxOptions.addArguments("--incognito");
        }
        return firefoxOptions;
    }

    public EdgeOptions getEdgeOptions() {
        edgeOptions = new EdgeOptions();
        if (Boolean.parseBoolean(properties.getProperty("headless"))) {
            edgeOptions.addArguments("--headless");
        }
        if (Boolean.parseBoolean(properties.getProperty("incognito"))) {
            edgeOptions.addArguments("--incognito");
        }
        return edgeOptions;
    }
}
