package com.nopcommerce.user;


import com.nopcommerce.common.Common_01_Login_User;
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
import pageObjects.user.UserSearchPageObject;
import utilities.DataHelper;

@Feature("Search product data")
public class User_04_Search_Advanced_Search extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        dataHelper = DataHelper.getDataHelper();
        nonExistingProduct = dataHelper.getProductName();
        relativeData = "Leno";
        absoluteProduct = "ThinkPad X1 Carbon";
        advancedSearchProduct = "Apple Macbook Pro";
        driver = getBrowserDriver(browserName, environment.endUserUrl());
        Allure.step("Pre-condition: Login User successfully");
        homePage = PageGeneratorManager.getUserHomePage(driver);
        homePage.setCookies(Common_01_Login_User.loggedCookies);
        homePage.refreshCurrentPage();
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());
        searchPage = homePage.clickToDynamicLinkAtFooterByText("Search");
    }

    @Test
    @Feature("Search with empty data")
    public void Search_01_Empty_Date() {
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getMessageSearchProductResult(), "Search term minimum length is 3 characters");
        verifyTrue(searchPage.isProductItemSearchUndisplayed());
    }

    @Test
    @Feature("Search with non existing data ")
    public void Search_02_NonExisting_Data() {
        searchPage.enterToDynamicTextboxById("Seach", "q", nonExistingProduct);
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getMessageSearchProductResult(), "No products were found that matched your criteria.");
        verifyTrue(searchPage.isProductItemSearchUndisplayed());
    }

    @Test
    @Feature("Search with Relative data ")
    public void Search_03_Relative_Data() {
        searchPage.enterToDynamicTextboxById("Search", "q", relativeData);
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getQuantityProductItemAtSearchPage(), 2);
        verifyTrue(searchPage.isProductItemResultDisplayedByProductTitle("Lenovo Thinkpad X1 Carbon Laptop"));
        verifyTrue(searchPage.isProductItemResultDisplayedByProductTitle("Lenovo IdeaCentre 600 All-in-One PC"));
    }


    @Test
    @Feature("Search with Absolute data ")
    public void Search_04_Absolute_Data() {
        searchPage.enterToDynamicTextboxById("Search", "q", absoluteProduct);
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getQuantityProductItemAtSearchPage(), 1);
        verifyTrue(searchPage.isProductItemResultDisplayedByProductTitle("Lenovo Thinkpad X1 Carbon Laptop"));
    }


    @Test
    @Feature("Search with Advanced Search and Parent Categories ")
    public void Search_05_Parent_Categories() {
        searchPage.enterToDynamicTextboxById("Search", "q", advancedSearchProduct);
        searchPage.checkToDefaultCheckboxRadioByLabel("Advanced search");
        searchPage.selectToDefaultDropdownByName("Category", "cid", "Computers");
        searchPage.unCheckToDefaultCheckboxByLabel("Automatically search sub categories");
        searchPage.clickToSearchButton();
        verifyTrue(searchPage.isProductItemSearchUndisplayed());
        verifyEquals(searchPage.getMessageSearchProductResult(), "No products were found that matched your criteria.");
    }


    @Test
    @Feature("Search with Advanced Search and Sub Categories ")
    public void Search_06_Sub_Categories() {
        searchPage.enterToDynamicTextboxById("Search", "q", advancedSearchProduct);
        searchPage.checkToDefaultCheckboxRadioByLabel("Advanced search");
        searchPage.selectToDefaultDropdownByName("Category", "cid", "Computers");
        searchPage.checkToDefaultCheckboxRadioByLabel("Automatically search sub categories");
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getQuantityProductItemAtSearchPage(), 1);
        verifyEquals(searchPage.getMessageSearchProductResult(), "Apple MacBook Pro 13-inch");
    }

    @Test
    @Feature("Search with Advanced Search and Incorrect Manufacturer ")
    public void Search_07_Incorrect_Manufacturer() {
        searchPage.enterToDynamicTextboxById("Search", "q", advancedSearchProduct);
        searchPage.checkToDefaultCheckboxRadioByLabel("Advanced search");
        searchPage.selectToDefaultDropdownByName("Category", "cid", "Computers");
        searchPage.checkToDefaultCheckboxRadioByLabel("Automatically search sub categories");
        searchPage.selectToDefaultDropdownByName("Manufacturer", "mid", "HP");
        searchPage.clickToSearchButton();
        verifyTrue(searchPage.isProductItemSearchUndisplayed());
        verifyEquals(searchPage.getMessageSearchProductResult(), "No products were found that matched your criteria.");
    }

    @Test
    @Feature("Search with Advanced Search and Correct Manufacturer ")
    public void Search_08_Correct_Manufacturer() {
        searchPage.enterToDynamicTextboxById("Search", "q", advancedSearchProduct);
        searchPage.checkToDefaultCheckboxRadioByLabel("Advanced search");
        searchPage.selectToDefaultDropdownByName("Category", "cid", "Computers");
        searchPage.checkToDefaultCheckboxRadioByLabel("Automatically search sub categories");
        searchPage.selectToDefaultDropdownByName("Manufacturer", "mid", "Apple");
        searchPage.clickToSearchButton();
        verifyEquals(searchPage.getQuantityProductItemAtSearchPage(), 1);
        verifyTrue(searchPage.isProductItemResultDisplayedByProductTitle("Apple MacBook Pro 13-inch"));
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String nonExistingProduct, relativeData, absoluteProduct, advancedSearchProduct;
    private UserHomePageObject homePage;
    private UserSearchPageObject searchPage;
    private DataHelper dataHelper;


}
