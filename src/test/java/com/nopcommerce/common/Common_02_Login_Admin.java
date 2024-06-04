package com.nopcommerce.common;

import commons.BaseTest;
import commons.PageGeneratorManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;

import java.util.Set;

public class Common_02_Login_Admin extends BaseTest {
    @Parameters({"browserName", "browserVersion"})
    @BeforeTest
    public void beforeClass(String browserName, String browserVersion) {
        driver = getBrowserDriver(browserName, browserVersion, environment.adminUrl());
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.enterToDynamicTextboxById("Email", "Email", environment.adminEmail());
        adminLoginPage.enterToDynamicTextboxById("Password", "Password", environment.adminPassword());
        adminLoginPage.clickToButtonByText("Log in");
        adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
        verifyTrue(adminDashboardPage.isContentHeaderDisplayedByHeaderAdminPage("Dashboard"));
        loggedCookies = adminDashboardPage.getAllCookies();
        driver.quit();
    }

    private WebDriver driver;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    public static Set<Cookie> loggedCookies;

}
