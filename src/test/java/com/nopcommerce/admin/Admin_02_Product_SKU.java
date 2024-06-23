package com.nopcommerce.admin;

import com.nopcommerce.common.Common_02_Login_Admin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminProductPageObject;

@Feature("Admin Product SKU")
public class Admin_02_Product_SKU extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        productSKU = "LE_IC_600";
        productName = "Lenovo IdeaCentre 600 All-in-One PC";
        driver = getBrowserDriver(browserName, browserVersion, environment.adminUrl());
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.setCookies(Common_02_Login_Admin.loggedCookies);
        adminLoginPage.refreshCurrentPage();
        adminLoginPage.clickToButtonByText("Log in");
        adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
        verifyTrue(adminDashboardPage.isContentHeaderDisplayedByHeaderAdminPage("Dashboard"));
        adminDashboardPage.clickToDynamicNavTreeViewLinkAdminPage("Catalog");
        adminDashboardPage.clickToDynamicNavLinkByParentSectionAdminPage("Catalog", "Products");
        adminProductPage = PageGeneratorManager.getAdminProductPage(driver);
        verifyTrue(adminProductPage.isContentHeaderDisplayedByHeaderAdminPage("Product"));
    }

    @Description("Verify the product can show properly with Product SKU")
    @Test
    public void Product_01_Go_Product_SKU() {
        adminProductPage.enterToDynamicTextboxById("Product SKU", "GoDirectlyToSku", productSKU);
        adminProductPage.clickToButtonByName("go-to-product-by-sku");
        verifyTrue(adminProductPage.isProductDetailPageReachedByProductName(productName));
        verifyEquals(adminProductPage.getValueTextboxById("Product Name", "Name"), productName);
    }

    @AfterClass
    public void afterClass() {
        closeBrowserDriver();

    }

    private WebDriver driver;
    private String productSKU, productName;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private AdminProductPageObject adminProductPage;
}
