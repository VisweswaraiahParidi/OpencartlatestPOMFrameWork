package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static com.qa.opencart.constants.AppConstants.*;

@Epic("Epic Id: 100: Account page")
@Story("Story no: 200: Implementing login functionality for Account page")
public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accountPage = loginPage.doLogin(properties.getProperty("userName"), properties.getProperty("password"));
    }

    @Test
    public void accountPageTtitleTest() {
        String accPageTitle = accountPage.getAccoutPageTite();
        Assert.assertEquals(accPageTitle, ACCPAGE_TITLE);
    }

  //  @Test
    public void isLogoutLinkExists() {
        Boolean flag = accountPage.islogOutLinkExists();
        Assert.assertTrue(flag);
        System.out.println("Exists");
    }

    @Test
    public void accoutPageHeaderCountTest() {
        int totalCounts = accountPage.getAccountPageHeaderCount();
        System.out.println("Actual Account Page Headre count ==> " + totalCounts);
        Assert.assertEquals(totalCounts, ACCPAGE_HEDERS_COUNT);
    }

    @Test
    public void accHeadersTest() {
        List<String> accHeadersTextList = accountPage.getAccountPageHeader();
        Assert.assertEquals(accHeadersTextList, EXPECTED_ACC_PAGE_HEADERS_LIST);
    }

    @DataProvider
    public Object[][] getSearchKey() {
        return new Object[][]{
                {"macbook", 3},
                {"Imac", 1},
                {"Samsung", 2}
        };
    }

    @Test(dataProvider = "getSearchKey")
    public void doSearchTest(String productName, int totalProductCount) {
        searchResultsPage = accountPage.doSearch(productName);
        int acctResultCount = searchResultsPage.serachResultsProductCount();
        System.out.println("Total Product Count:  " + productName + "  ===>" + acctResultCount);
        Assert.assertEquals(acctResultCount, totalProductCount);
    }
}
