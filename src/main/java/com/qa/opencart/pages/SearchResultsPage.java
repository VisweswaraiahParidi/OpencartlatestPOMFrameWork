package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.qa.opencart.constants.AppConstants.MEDIUM_TIME_OUT;
import static com.qa.opencart.constants.AppConstants.SHORT_TIME_OUT;

public class SearchResultsPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By productResults = By.cssSelector("div.product-layout");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public int serachResultsProductCount() {
        return elementUtil.waitForElementsVisible(productResults, MEDIUM_TIME_OUT).size();
    }

    public ProductInfoPage selectProduct(String productName) {
        elementUtil.clickElementWhenReady(By.partialLinkText(productName), MEDIUM_TIME_OUT);
        return new ProductInfoPage(driver);
    }

}
