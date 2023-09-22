package com.qa.opencart.tests;

import com.aventstack.extentreports.ExtentReports;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.listeners.ExtentReportListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;

//@Listeners(ExtentReportListener.class)  --" use if don't want to add listner class in testng
@Epic("Epic Id: 100: login page")
@Story("Story no: 200: Implementing login functionality for Opercart")
public class LoginPageTest extends BaseTest {

    @Description("Login Page test")
    @Severity(SeverityLevel.MINOR)
    @Test(priority = 1)
    public void loginPageTitleTest() {
        String accTitle = loginPage.getLoginPageTitle();
        Assert.assertEquals(accTitle, LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void loginPageUrlTest() {
        String acctUrl = loginPage.getLoginPageUrl();
        Assert.assertTrue(acctUrl.contains(LOGIN_PAGE_URL_FRACTION));
    }

    @Description("forgot password")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 3)
    public void isForgotPwdLinkExistTest() {
        Boolean flag = loginPage.isForgotPwdLinkExist();
        Assert.assertTrue(flag);
    }

    @Test(priority = 4)
    public void loginTest() {
        accountPage = loginPage.doLogin(properties.getProperty("userName"),properties.getProperty("password"));
        String accPgeTitle = accountPage.getAccoutPageTite();
        Assert.assertEquals(accPgeTitle, ACCOUNT_PAGE_TITLE);
        Assert.assertEquals(accountPage.islogOutLinkExists(), true);
    }
}
