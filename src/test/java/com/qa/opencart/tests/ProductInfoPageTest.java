package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ProductInfoPageTest extends BaseTest {
    @BeforeClass
    public void productInfoPageSetup() {
        accountPage = loginPage.doLogin(properties.getProperty("userName"), properties.getProperty("password"));
    }

    @Test
    public void productHeaderTest() {
        searchResultsPage = accountPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        String proHeaderName = productInfoPage.productHeaderName();
        System.out.println(proHeaderName);
        Assert.assertEquals(proHeaderName, "MacBook Pro");
    }

    @DataProvider
    public Object[][] productImagevaialble() {
        return new Object[][]{
                {"macbook", "MacBook Pro", 4},
                {"iMac", "iMac", 3},
                {"Samsung", "Samsung SyncMaster", 1}

        };
    }

    @Test(dataProvider = "productImagevaialble")
    public void productImageCount(String searchPorductName, String selectProductName, int totalImages) {
        searchResultsPage = accountPage.doSearch(searchPorductName);
        productInfoPage = searchResultsPage.selectProduct(selectProductName);
        int acctImages = productInfoPage.productImaneCount();
        Assert.assertEquals(acctImages, totalImages);
    }

    @Test
    public void productInfoTest() {
        searchResultsPage = accountPage.doSearch("macbook");
        productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
        Map<String, String> prodtInfo = productInfoPage.productData();
        System.out.print(prodtInfo);

        softAssert.assertEquals(prodtInfo.get("Brand"), "Apple");
        softAssert.assertEquals(prodtInfo.get("Reward Points"), "800");
        softAssert.assertAll();
    }
}
