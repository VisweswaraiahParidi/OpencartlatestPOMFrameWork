package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.swing.text.Element;

public class DemoTest extends BaseTest {
    ElementUtil elementUtil;

    @BeforeMethod
    public void demoSetUp(){
        driver.get("https://classic.crmpro.com/");
        elementUtil=new ElementUtil(driver);
    }

    @Test
    public void demoTest(){
        elementUtil.doSendKeys(By.name("username"),"testautomation");
        elementUtil.doSendKeys(By.name("password"),"testautomation");
        elementUtil.doClick(By.xpath("//input[@value='Login']"));

    }
}
