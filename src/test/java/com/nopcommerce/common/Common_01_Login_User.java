package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserRegisterPageObject;
import utilities.DataHelper;

import java.util.Set;

public class Common_01_Login_User extends BaseTest {
    @Parameters({"browser", "runConfig", "osName", "hubPort"})
    @BeforeTest
    public void beforeClass(String browserName, String runConfig, @Optional("windows") String osName, @Optional("4444") String port) {
        DataHelper dataHelper = DataHelper.getDataHelper();
        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        email = dataHelper.getUserEmail();
        password = dataHelper.getPassword();
        if (runConfig.equals("local")) {
            driver = getBrowserDriver(browserName, environment.endUserUrl());
        } else {
            driver = getBrowserDriver(browserName, environment.endUserUrl(), osName, port);
        }
        homePage = PageGeneratorManager.getUserHomePage(driver);
        Allure.step("Pre-condition: REGISTER success the email: " + email);
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToDynamicTextboxById("First name", "FirstName", firstName);
        registerPage.enterToDynamicTextboxById("Last name", "LastName", lastName);
        registerPage.enterToDynamicTextboxById("Email", "Email", email);
        registerPage.enterToDynamicTextboxById("Password", "Password", password);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", password);
        registerPage.clickToRegisterButton();
        verifyTrue(registerPage.isSuccessRegisterMessageDisplayed());
        loggedCookies = registerPage.getAllCookies();
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String firstName, lastName, email;
    public static String password;

    public static Set<Cookie> loggedCookies;
    private UserRegisterPageObject registerPage;
    private UserHomePageObject homePage;

}
