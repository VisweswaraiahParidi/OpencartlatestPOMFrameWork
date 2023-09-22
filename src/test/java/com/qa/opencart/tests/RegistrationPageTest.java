package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.utils.ExcelUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qa.opencart.constants.AppConstants.*;
import static com.qa.opencart.utils.ExcelUtils.getTestData;

public class RegistrationPageTest extends BaseTest {
    @BeforeClass
    public void regSetup() {
         registrationPage = loginPage.doRegiserBtnExist();
    }

    public String getRandomEmailId() {
        return "openauto"+System.currentTimeMillis()+"@open.com";
    }

    @DataProvider
    public Object[][] getUserRegsterData() {
       return ExcelUtils.getTestData(REGISTER_SHEET_NAME);
    }

    @Test(dataProvider = "getUserRegsterData")
    public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
        Assert.assertTrue(registrationPage.registerUser(firstName, lastName, getRandomEmailId(), telephone,  password,  subscribe));
    }
}
