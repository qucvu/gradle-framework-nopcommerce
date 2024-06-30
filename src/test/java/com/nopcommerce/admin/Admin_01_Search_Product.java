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

@Feature("Admin Search Product")
public class Admin_01_Search_Product extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        productSearchName = "Lenovo IdeaCentre 600 All-in-One PC";
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

    @Description("Verify admin can search with Product Name")
    @Test
    public void Search_01_Product_Name() {
        adminProductPage.enterToDynamicTextboxById("Product Name", "SearchProductName", productSearchName);
        adminProductPage.clickToSearchButtonAdminPage();
        verifyEquals(adminProductPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminProductPage.isProductNameDisplayedAtTable(productSearchName));
    }

    @Description("Verify admin can search with Product Name + Parent Category + Unchecked")
    @Test
    public void Search_02_Product_Name() {
        adminProductPage.enterToDynamicTextboxById("Product Name", "SearchProductName", productSearchName);
        adminProductPage.selectToDefaultDropdownByName("Search Category", "SearchCategoryId", "Computers");
        adminProductPage.clickToSearchButtonAdminPage();
        verifyTrue(adminProductPage.isEmptyDataTableMessageDisplayedAdminPage());

    }

    @Description("Verify admin can search with Product Name + Parent Category + Checked")
    @Test
    public void Search_03_Product_Name() {
        adminProductPage.enterToDynamicTextboxById("Product Name", "SearchProductName", productSearchName);
        adminProductPage.selectToDefaultDropdownByName("Search Category", "SearchCategoryId", "Computers");
        adminProductPage.checkToDefaultCheckboxRadioByLabelAdminPage("Search subcategories");
        adminProductPage.clickToSearchButtonAdminPage();
        verifyEquals(adminProductPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminProductPage.isProductNameDisplayedAtTable(productSearchName));


    }


    @Description("Verify admin can search with Product Name + Child category")
    @Test
    public void Search_04_Product_Name() {
        adminProductPage.enterToDynamicTextboxById("Product Name", "SearchProductName", productSearchName);
        adminProductPage.selectToDefaultDropdownByName("Search Category", "SearchCategoryId", "Computers >> Desktops");
        adminProductPage.unCheckToDefaultCheckboxRadioByLabelAdminPage("Search subcategories");
        adminProductPage.clickToSearchButtonAdminPage();
        verifyEquals(adminProductPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminProductPage.isProductNameDisplayedAtTable(productSearchName));

    }

    @Description("Verify admin can search with Product Name + Manufacturer")
    @Test
    public void Search_05_Product_Name() {
        adminProductPage.enterToDynamicTextboxById("Product Name", "SearchProductName", productSearchName);
        adminProductPage.selectToDefaultDropdownByName("Search Category", "SearchCategoryId", "All");
        adminProductPage.unCheckToDefaultCheckboxRadioByLabelAdminPage("Search subcategories");
        adminProductPage.selectToDefaultDropdownByName("Manufacturer", "SearchManufacturerId", "Apple");
        adminProductPage.clickToSearchButtonAdminPage();
        verifyTrue(adminProductPage.isProductNameDisplayedAtTable(productSearchName));
    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();

    }

    private WebDriver driver;
    private String productSearchName;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private AdminProductPageObject adminProductPage;
}
