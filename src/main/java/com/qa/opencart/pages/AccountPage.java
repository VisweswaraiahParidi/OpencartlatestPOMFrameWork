package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.qa.opencart.constants.AppConstants.ACCOUNT_PAGE_TITLE;
import static com.qa.opencart.constants.AppConstants.SHORT_TIME_OUT;

public class AccountPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By logOutlink = By.linkText("Logout");
    private By accountHeaders = By.cssSelector("div#content h2");
    private By serachTextField = By.xpath("//input[@name='search']");
    private By searchBtn = By.cssSelector("div#search button");


    public AccountPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public String getAccoutPageTite() {
        return elementUtil.waitForPageTitle(ACCOUNT_PAGE_TITLE, SHORT_TIME_OUT);
    }

    public boolean islogOutLinkExists() {
        return elementUtil.waitForElementPresence(logOutlink, SHORT_TIME_OUT).isDisplayed();
    }

    public List<String> getAccountPageHeader() {
        List<WebElement> heardersList = elementUtil.waitForElementsPresence(accountHeaders, SHORT_TIME_OUT);
        List<String> headersValueList = new ArrayList<String>();
        for (WebElement e : heardersList) {
            String headText = e.getText();
            headersValueList.add(headText);
        }
        System.out.println("actualHeardsTextList: ==>  " + headersValueList);
        return headersValueList;
    }

    public int getAccountPageHeaderCount() {
        return elementUtil.waitForElementsVisible(accountHeaders, SHORT_TIME_OUT).size();
    }

    public SearchResultsPage doSearch(String searchKey) {
        elementUtil.waitForElementVisible(serachTextField, SHORT_TIME_OUT).clear();
        elementUtil.waitForElementVisible(serachTextField, SHORT_TIME_OUT).sendKeys(searchKey);
        elementUtil.doClick(searchBtn);
        return new SearchResultsPage(driver);

    }
}
