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
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserProductCategoryPageObject;
import pageObjects.user.UserProductDetailsPageObject;
import pageObjects.user.UserShoppingCartPageObject;
import utilities.DataHelper;

@Feature("User Page - Order feature")
public class User_07_Order extends BaseTest {

    public User_07_Order() {
    }

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        dataHelper = DataHelper.getDataHelper();
        parentCategory = "Computers";
        subCategory = "Desktops";
        productOrderTitle = "Build your own computer";
        productOrderProcessor = "2.5 GHz Intel Pentium Dual-Core E2200 [+$15.00]";
        productOrderRAM = "8GB [+$60.00]";
        productOrderHDD = "400 GB [+$100.00]";
        productOrderOS = "Vista Premium [+$60.00]";
        driver = getBrowserDriver(browserName, browserVersion, environment.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);
        homePage.setCookies(Common_01_Login_User.loggedCookies);
        homePage.refreshCurrentPage();
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());
        homePage.hoverToDynamicHeaderLinkByText(parentCategory);
        homePage.clickDynamicHeaderMenuLinkByText(subCategory);
        productCategoryPage = PageGeneratorManager.getUserProductCategoryPage(driver);
        verifyTrue(productCategoryPage.isActiveLinkDisplayedByText(parentCategory));
        verifyTrue(productCategoryPage.isActiveLinkDisplayedByText(subCategory));
        productDetailsPage = productCategoryPage.clickToDynamicProductByTitle(productOrderTitle);
    }

    @Description("Verify the user can add product to cart properly")
    @Test()
    public void Order_01_Add_To_Cart() {
        productDetailsPage.selectToDefaultDropdownByName("Product Processor", "product_attribute_1", productOrderProcessor);
        productDetailsPage.selectToDefaultDropdownByName("Product RAM", "product_attribute_2", productOrderRAM);
        productDetailsPage.checkToDefaultCheckboxRadioByLabel(productOrderHDD);
        productDetailsPage.checkToDefaultCheckboxRadioByLabel(productOrderOS);
        productDetailsPage.checkToSoftwareOptionsCheckBox(productOrderSoftwareList);
        productDetailsPage.clickToButtonByText("Add to cart");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
        productOrderPrice = productDetailsPage.getProductPrice();
        shoppingCartPage = productDetailsPage.clickToShoppingCartLink();
        shoppingCartPage.hoverToHeaderShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtMiniCart(productOrderTitle));
        verifyEquals(shoppingCartPage.getProductQuantityAtMiniCart(), 1);
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderProcessor));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderRAM));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderOS));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderHDD));
        verifyTrue(shoppingCartPage.isProductSoftwareOptionsDisplayedAtMiniCart(productOrderSoftwareList));
        verifyEquals(shoppingCartPage.getPriceTotalAtMiniCart(), productOrderPrice);
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }


    private WebDriver driver;
    private String parentCategory, subCategory, productOrderTitle, productOrderProcessor, productOrderRAM, productOrderHDD, productOrderOS, productOrderPrice;
    private String[] productOrderSoftwareList = {"Microsoft Office [+$50.00]", "Acrobat Reader [+$10.00]", "Total Commander [+$5.00]"};
    private DataHelper dataHelper;
    private UserHomePageObject homePage;
    private UserProductDetailsPageObject productDetailsPage;
    private UserProductCategoryPageObject productCategoryPage;
    private UserShoppingCartPageObject shoppingCartPage;

}
