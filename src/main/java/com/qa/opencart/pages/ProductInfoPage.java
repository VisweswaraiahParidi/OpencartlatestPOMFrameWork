package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.qa.opencart.constants.AppConstants.SHORT_TIME_OUT;

public class ProductInfoPage {
    private WebDriver driver;
    private ElementUtil elementUtil;

    private By productHeader = By.cssSelector("div#content h1");
    private By productImages = By.cssSelector("ul.thumbnails img");
    private By quantity = By.name("quantity");
    private By addtoCartBtn = By.id("button-cart");
    private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
    private By productPriceMetedata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

    Map<String, String> productMap;

    public ProductInfoPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    public int productImaneCount() {
        int accImagescount = elementUtil.waitForElementsVisible(productImages, SHORT_TIME_OUT).size();
        System.out.println("Total Prodcut Images " + productHeaderName() + " ===>  " + accImagescount);
        return accImagescount;
    }

    public String productHeaderName() {
        return elementUtil.doElementGetText(productHeader);
    }

    private void prodcutMetada() {
        List<WebElement> prodMetaList = elementUtil.waitForElementsVisible(productMetaData, SHORT_TIME_OUT);
        //  Map<String, String> metaMap = new HashMap<String, String>();
        for (WebElement e : prodMetaList) {
            String metaText = e.getText();
            String key = metaText.split(":")[0].trim();
            String value = metaText.split(":")[1].trim();
            productMap.put(key, value);
        }

    }

    private void prodcutPriceMetada() {
        List<WebElement> proceList = elementUtil.waitForElementsVisible(productPriceMetedata, SHORT_TIME_OUT);
        //  Map<String, String> priceMap = new HashMap<String, String>();
        String accPrice = proceList.get(0).getText().trim();
        String exTx = proceList.get(1).getText().split(":")[0].trim();
        String exVlaue = proceList.get(1).getText().split(":")[1].trim();
        productMap.put("price", accPrice);
        productMap.put(exTx, exVlaue);

    }

    public Map<String, String> productData() {
        //   productMap = new HashMap<String,String>(); ** for non Order bsed collection
        //   productMap = new TreeMap<String,String>(); ** for alphabetic  Order bsed collection

        productMap = new LinkedHashMap<String, String>(); // for order based collections
        productMap.put("productheader", productHeaderName());
        prodcutMetada();
        prodcutPriceMetada();
        productMap.put("totalimages", String.valueOf(productImaneCount()));
        return productMap;
    }
}

