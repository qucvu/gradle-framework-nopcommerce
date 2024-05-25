package com.nopcommerce.user;

import com.nopcommerce.common.Common_01_Login_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.*;

@Feature("User Page - Product WhistList, Compare/View Product")
public class User_06_WhistList_Compare extends BaseTest {


    public User_06_WhistList_Compare() {
    }

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        productTitle = "Apple MacBook Pro 13-inch";
        productCompare1 = "HTC One M8 Android L 5.0 Lollipop";
        productCompare2 = "Build your own computer";
        productViewFirst = "Asus N551JK-XO076H Laptop";
        productViewSecond = "HP Spectre XT Pro UltraBook";
        productViewThird = "Lenovo Thinkpad X1 Carbon Laptop";
        productViewFourth = "Samsung Series 9 NP900X4C Premium Ultrabook";
        productViewFifth = "Apple MacBook Pro 13-inch";
        fullName = String.format("%s %s", Common_01_Login_User.firstName, Common_01_Login_User.lastName);
        driver = getBrowserDriver(browserName, browserVersion, environment.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);
        homePage.setCookies(Common_01_Login_User.loggedCookies);
        homePage.refreshCurrentPage();
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());
        productDetailsPage = homePage.clickToDynamicProductByTitle(productTitle);
    }

    @Description("Verify user can add to whistList successfully ")
    @Test
    public void WhistList_Compare_01_Add_To_WhistList() {
        productDetailsPage.clickToAddToWhistListButtonAtProductOverview();
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your wishlist");
        productDetailsPage.closeHeaderBarNotification();
        productWhistListPage = productDetailsPage.clickToWhistListLink();
        verifyTrue(productWhistListPage.isProductTitleProductDisplayedAtWhistListTable(productTitle));
        productWhistListPage.clickToWhistListShareLink();
        verifyTrue(productWhistListPage.isFullNameDisplayedAtWhistListTitle(fullName));
        verifyTrue(productWhistListPage.isProductTitleProductDisplayedAtWhistListTable(productTitle));
    }

    @Description("Verify user can add to card from the whistList page successfully ")
    @Test
    public void WhistList_Compare_02_Add_To_Card() {
        shoppingCartPage = productWhistListPage.clickToShoppingCartLink();
        productWhistListPage = shoppingCartPage.clickToWhistListLink();
        productWhistListPage.checkToAddToCartCheckboxByProductTitle(productTitle);
        productWhistListPage.clickToButtonByText("Add to cart");
        shoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);
        shoppingCartPage.hoverToHeaderShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtMiniCart(productTitle));
        productWhistListPage = shoppingCartPage.clickToWhistListLink();
        verifyTrue(productWhistListPage.isProductTitleProductUndisplayed(productTitle));

    }

    @Description("Verify user can remove product in the whist list page ")
    @Test
    public void WhistList_Compare_03_Remove_WhistList_Product() {
        homePage = productWhistListPage.goToUserHomePage();
        productDetailsPage = homePage.clickToDynamicProductByTitle(productTitle);
        productDetailsPage.clickToAddToWhistListButtonAtProductOverview();
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your wishlist");
        productDetailsPage.closeHeaderBarNotification();
        productWhistListPage = productDetailsPage.clickToWhistListLink();
        productWhistListPage.removeAllProductWhistList();
        verifyTrue(productWhistListPage.isEmptyWhistListTable());
        verifyTrue(productWhistListPage.isProductTitleProductUndisplayed(productTitle));

    }


    @Description("Verify user can add product to compare ")
    @Test
    public void WhistList_Compare_04_Add_Product_To_Compare() {
        homePage = productWhistListPage.goToUserHomePage();
        homePage.clickToAddToCompareButtonByProductTitle(productCompare1);
        verifyEquals(homePage.getMessageAtBarNotification(), "The product has been added to your product comparison");
        homePage.closeHeaderBarNotification();
        productPriceCompareFirst = homePage.getProductPriceByProductTitleAtProductItem(productCompare1);
        homePage.clickToAddToCompareButtonByProductTitle(productCompare2);
        verifyEquals(homePage.getMessageAtBarNotification(), "The product has been added to your product comparison");
        homePage.closeHeaderBarNotification();
        productPriceCompareSecond = homePage.getProductPriceByProductTitleAtProductItem(productCompare2);
        homePage.clickToDynamicLinkAtFooterByText("Compare products list");
        productComparisonPage = PageGeneratorManager.getUserProductComparisonPage(driver);
        verifyTrue(productComparisonPage.isProductTitleDisplayedAtCompareProductTable(productCompare1));
        verifyTrue(productComparisonPage.isProductTitleDisplayedAtCompareProductTable(productCompare2));
        verifyTrue(productComparisonPage.isProductPriceDisplayedByProductTitleAtCompareProductTable(productCompare1, productPriceCompareFirst));
        verifyTrue(productComparisonPage.isProductPriceDisplayedByProductTitleAtCompareProductTable(productCompare2, productPriceCompareSecond));
        productComparisonPage.clickToDynamicLinkAtMainContentByText("Clear list");
        verifyTrue(productComparisonPage.isEmptyItemCompareMessageDisplayed());
        verifyTrue(productComparisonPage.isProductTitleUnDisplayedAtCompareProductTable(productCompare1));
        verifyTrue(productComparisonPage.isProductTitleUnDisplayedAtCompareProductTable(productCompare2));

    }


    @Description("Verify user can new recently products ")
    @Test
    public void WhistList_Compare_05_View_Recent_Product() {
        productComparisonPage.hoverToDynamicHeaderLinkByText("Computers");
        productComparisonPage.clickDynamicHeaderMenuLinkByText("Notebooks");
        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicProductByTitle(productViewFirst);
        productDetailsPage = PageGeneratorManager.getUserProductDetailsPage(driver);
        productDetailsPage.backToPage();

        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicProductByTitle(productViewSecond);
        productDetailsPage = PageGeneratorManager.getUserProductDetailsPage(driver);
        productDetailsPage.backToPage();

        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicProductByTitle(productViewThird);
        productDetailsPage = PageGeneratorManager.getUserProductDetailsPage(driver);
        productDetailsPage.backToPage();

        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicProductByTitle(productViewFourth);
        productDetailsPage = PageGeneratorManager.getUserProductDetailsPage(driver);
        productDetailsPage.backToPage();

        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicProductByTitle(productViewFifth);
        productDetailsPage = PageGeneratorManager.getUserProductDetailsPage(driver);
        productDetailsPage.backToPage();

        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        productCategoryPage.clickToDynamicLinkAtFooterByText("Recently viewed products");
        productViewRecentPage = PageGeneratorManager.getUserProductViewRecentPage(driver);
        verifyTrue(productViewRecentPage.isProductItemResultDisplayedByProductTitle(productViewThird));
        verifyTrue(productViewRecentPage.isProductItemResultDisplayedByProductTitle(productViewFourth));
        verifyTrue(productViewRecentPage.isProductItemResultDisplayedByProductTitle(productViewFifth));
        verifyTrue(productViewRecentPage.isProductItemUndisplayedByProductTitle(productViewFirst));
        verifyTrue(productViewRecentPage.isProductItemUndisplayedByProductTitle(productViewSecond));
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }


    private WebDriver driver;
    private String productTitle, fullName, productCompare1, productCompare2, productPriceCompareFirst, productPriceCompareSecond;

    private String productViewFirst, productViewSecond, productViewThird, productViewFourth, productViewFifth;
    private UserHomePageObject homePage;
    private UserProductDetailsPageObject productDetailsPage;
    private UserProductWhistListPageObject productWhistListPage;
    private UserShoppingCartPageObject shoppingCartPage;
    private UserProductComparisonPageObject productComparisonPage;
    private UserProductCategoryPageObject productCategoryPage;
    private UserProductViewRecentPageObject productViewRecentPage;
}
