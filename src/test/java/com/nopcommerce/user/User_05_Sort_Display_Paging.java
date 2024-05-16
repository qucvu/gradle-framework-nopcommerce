package com.nopcommerce.user;


import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserProductCategoryPageObject;

@Feature("User sort/display/paging data")
public class User_05_Sort_Display_Paging extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        menu = "Computers";
        subMenu = "Notebooks";
        driver = getBrowserDriver(browserName, browserVersion, environment.endUserUrl());
        Allure.step("Pre-condition: Go to the sub menu of homepage");
        homePage = PageGeneratorManager.getUserHomePage(driver);
        homePage.hoverDynamicHeaderLinkByText(menu);
        homePage.clickDynamicHeaderMenuLinkByText(subMenu);
        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        verifyTrue(productCategoryPage.isActiveLinkDisplayedByText(menu));
        verifyTrue(productCategoryPage.isActiveLinkDisplayedByText(subMenu));
    }

    @Test
    @Feature("Sort with name: A to Z")
    public void Sort_Display_01_Name_A_To_Z() {
        productCategoryPage.selectToDefaultDropdownByName("Product Order", "products-orderby", "Name: A to Z");
        verifyTrue(productCategoryPage.isProductNameSortAsc());
    }

    @Test
    @Feature("Sort with name: Z to A")
    public void Sort_Display_02_Name_Z_To_A() {
        productCategoryPage.selectToDefaultDropdownByName("Product Order", "products-orderby", "Name: Z to A");
        verifyTrue(productCategoryPage.isProductNameSortDesc());
    }


    @Test
    @Feature("Sort with price: Low to high")
    public void Sort_Display_03_Price_Low_To_High() {
        productCategoryPage.selectToDefaultDropdownByName("Product Order", "products-orderby", "Price: Low to High");
        verifyTrue(productCategoryPage.isProductPriceSortAsc());
    }

    @Test
    @Feature("Sort with price: High to low")
    public void Sort_Display_04_Price_High_To_Low() {
        productCategoryPage.selectToDefaultDropdownByName("Product Order", "products-orderby", "Price: High to Low");
        verifyTrue(productCategoryPage.isProductPriceSortDesc());
    }


    @Test
    @Feature("Display with 3 products")
    public void Sort_Display_05_Pagination_3_Product() {
        productCategoryPage.selectToDefaultDropdownByName("Product per page", "products-pagesize", "3");
        verifyTrue(productCategoryPage.isQuantityProductDisplayedByQuantity(3));
        verifyFalse(productCategoryPage.isPaginationProductUndisplayed());
        verifyEquals(productCategoryPage.getCurrentActivePage(), 1);
        verifyTrue(productCategoryPage.isNextIconPaginationDisplayed());
        productCategoryPage.clickToDynamicPaginationByPageNumber(2);
        verifyEquals(productCategoryPage.getCurrentActivePage(), 2);
        verifyTrue(productCategoryPage.isPreviousIconPaginationDisplayed());
    }


    @Test
    @Feature("Display with 6 products")
    public void Sort_Display_06_Pagination_6_Product() {
        productCategoryPage.selectToDefaultDropdownByName("Product per page", "products-pagesize", "6");
        verifyTrue(productCategoryPage.isPaginationProductUndisplayed());
    }

    @Test
    @Feature("Display with 9 products")
    public void Sort_Display_07_Pagination_9_Product() {
        productCategoryPage.selectToDefaultDropdownByName("Product per page", "products-pagesize", "9");
        verifyTrue(productCategoryPage.isPaginationProductUndisplayed());
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String menu, subMenu;

    private UserHomePageObject homePage;
    private UserProductCategoryPageObject productCategoryPage;


}
