package com.qa.opencart.pages;

import com.qa.opencart.utils.ElementUtil;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static com.qa.opencart.constants.AppConstants.*;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil elementUtil;

    /**
     * WebElements of the page class is called as Widgets
     *
     * @param driver
     */

    //1. Private Locators
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotPwdLink = By.xpath("//div[@class='form-group']//a[normalize-space()='Forgotten Password']");
    private By registerLink = By.xpath("//a[@class='list-group-item' and normalize-space()='Register']");
    private By dummyLink = By.xpath("//a[@class='list-group-item' and normalize-space()='Register']");


    //2. Public Page constructors

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        elementUtil = new ElementUtil(driver);
    }

    //3.Public Page actions and methods
    @Step(".......getting LoginPage Title......")
    public String getLoginPageTitle() {
        String title = elementUtil.waitForTitleIs(LOGIN_PAGE_TITLE,SHORT_TIME_OUT);
        System.out.println("Login Page Title is:  " + title);
        return title;
    }

    public String getLoginPageUrl() {
        String url = elementUtil.waitForURLContains(LOGIN_PAGE_URL_FRACTION, SHORT_TIME_OUT);
        System.out.println("Login Page URL is:  " + url);
        return url;
    }

    public boolean isForgotPwdLinkExist() {
        return elementUtil.waitForElementVisible(forgotPwdLink, MEDIUM_TIME_OUT).isDisplayed();
    }

    @Step("login to app with Username {0}, password {1}")
    public AccountPage doLogin(String uname, String pwd) {
        System.out.println("App credentials are " + uname + ":" + pwd);
        elementUtil.doActionsSendKeys(emailId, uname);
        elementUtil.doActionsSendKeys(password, pwd);
        elementUtil.doClick(loginBtn);
       // return elementUtil.waitForTitleIs(LOGIN_PAGE_ACCPAGE_TITLE,SHORT_TIME_OUT);
        return new AccountPage(driver);
    }

    public RegistrationPage doRegiserBtnExist(){
        elementUtil.waitForElementVisible(registerLink,5);
        elementUtil.doClick(registerLink);
        return new RegistrationPage(driver);
    }
}
