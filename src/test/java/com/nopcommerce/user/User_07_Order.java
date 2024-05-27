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
        productOrderProcessorEdited = "2.2 GHz Intel Pentium Dual-Core E2200";
        productOrderRAMEdited = "4GB [+$20.00]";
        productOrderHDDEdited = "320 GB";
        productOrderOSEdited = "Vista Home [+$50.00]";
        productOrderQuantityEdited = "2";
        productOrderPriceEdited = "$1,320.00";
        productTotalPrice = "$2,640.00";
        productUpdateOrderTitle = "Lenovo IdeaCentre 600 All-in-One PC";
        productUpdateOrderTotalPrice = "$2,500.00";
        productUpdateOrderQuantity = "5";
        productCheckoutTitle = "Apple MacBook Pro 13-inch";
        cardNumberCheckout = dataHelper.getVisaCarNumber();
        cardCodeCheckout = dataHelper.getCardCodeNumber();
        cityCheckout = dataHelper.getCity();
        address1Checkout = dataHelper.getAddress();
        zipCodeCheckout = dataHelper.getPostalCode();
        phoneNumberCheckout = dataHelper.getPhoneNumber();
        giftWrapCheckout = "No";
        countryCheckout = "Viet Nam";
        stateCheckout = "Other";
        shippingMethodCheckout = "Next Day Air ($0.00)";
        paymentMethodCheckoutCheque = "Check / Money Order";
        productCheckoutQuantity = "2";
        productCheckoutTotalPrice = "$3,600.00";
        paymentMethodCheckoutCard = "Credit Card";
        cardholderCameCheckout = Common_01_Login_User.firstName + " " + Common_01_Login_User.lastName;
        expirationYearCheckout = "2024";
        expirationMonthCheckout = "09";
        productQuantityReOrder = "3";
        productCityReOrder = dataHelper.getCity();
        productAddress1ReOrder = dataHelper.getAddress();
        productZipCodeReOrder = dataHelper.getPostalCode();
        productPhoneNumberReOrder = dataHelper.getPhoneNumber();
        shippingMethodReOrder = "2nd Day Air ($0.00)";
        paymentMethodReOrder = "Check / Money Order";
        productTotalPriceReOrder = "$5,400.00";
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
        productOrderPrice = productDetailsPage.getProductPrice();
        productDetailsPage.clickToButtonByText("Add to cart");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
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
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtCartTable(productOrderTitle));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderProcessor));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderRAM));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderOS));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderHDD));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderSoftwareList));
        verifyEquals(shoppingCartPage.getProductPriceByProductTitleAtCartTable(productOrderTitle), productOrderPrice);
    }

    @Description("Verify the user can edit product in cart properly")
    @Test()
    public void Order_02_Edit_Product_In_Cart() {
        productDetailsPage = shoppingCartPage.clickToEditLinkByProductTitleAtCartTable(productOrderTitle);
        productDetailsPage.selectToDefaultDropdownByName("Product Processor", "product_attribute_1", productOrderProcessorEdited);
        productDetailsPage.selectToDefaultDropdownByName("Product RAM", "product_attribute_2", productOrderRAMEdited);
        productDetailsPage.checkToDefaultCheckboxRadioByLabel(productOrderHDDEdited);
        productDetailsPage.checkToDefaultCheckboxRadioByLabel(productOrderOSEdited);
        productDetailsPage.unCheckToSoftwareOptionsCheckBox(productOrderSoftwareList);
        productDetailsPage.checkToSoftwareOptionsCheckBox(productOrderSoftwareListEdited);
        productDetailsPage.enterToDynamicTextboxById("Quanity Product Order", "product_enteredQuantity_1", productOrderQuantityEdited);
        verifyEquals(productDetailsPage.getProductPrice(), productOrderPriceEdited);
        productDetailsPage.clickToButtonByText("Update");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
        shoppingCartPage = productDetailsPage.clickToShoppingCartLink();
        shoppingCartPage.hoverToHeaderShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtMiniCart(productOrderTitle));
        verifyEquals(shoppingCartPage.getProductQuantityAtMiniCart(), Integer.parseInt(productOrderQuantityEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderProcessorEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderRAMEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderOSEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedAtMiniCart(productOrderHDDEdited));
        verifyTrue(shoppingCartPage.isProductSoftwareOptionsDisplayedAtMiniCart(productOrderSoftwareListEdited));
        verifyEquals(shoppingCartPage.getPriceTotalAtMiniCart(), productTotalPrice);


        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtCartTable(productOrderTitle));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderProcessorEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderRAMEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderOSEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderHDDEdited));
        verifyTrue(shoppingCartPage.isProductInfoDisplayedByProductTitleAtCartTable(productOrderTitle, productOrderSoftwareListEdited));
        verifyEquals(shoppingCartPage.getProductPriceByProductTitleAtCartTable(productOrderTitle), productOrderPriceEdited);
        verifyEquals(shoppingCartPage.getProductTotalPriceByProductTitleAtCartTable(productOrderTitle), productTotalPrice);

    }

    @Description("Verify the user can remove product from cart")
    @Test()
    public void Order_03_Remove_Product_In_Cart() {
        shoppingCartPage.clickToRemoveButtonByProductTitleAtCartTable(productOrderTitle);
        verifyTrue(shoppingCartPage.isEmptyShoppingCartMessageDisplayed());
    }

    @Description("Verify the user can update product from cart")
    @Test()
    public void Order_04_Update_Product_In_Cart() {
        shoppingCartPage.enterToDynamicTextboxById("Search Store", "small-searchterms", productUpdateOrderTitle);
        shoppingCartPage.clickToButtonByText("Search");
        searchPage = PageGeneratorManager.getUserSearchPage(driver);
        productDetailsPage = searchPage.clickToDynamicProductByTitle(productUpdateOrderTitle);
        productDetailsPage.clickToButtonByText("Add to cart");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
        shoppingCartPage = productDetailsPage.clickToShoppingCartLink();
        shoppingCartPage.hoverToHeaderShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtMiniCart(productUpdateOrderTitle));
        shoppingCartPage.enterToQuantityTextboxByProductTitle(productUpdateOrderTitle, productUpdateOrderQuantity);
        verifyEquals(shoppingCartPage.getProductTotalPriceByProductTitleAtCartTable(productUpdateOrderTitle), productUpdateOrderTotalPrice);
        shoppingCartPage.clickToRemoveButtonByProductTitleAtCartTable(productUpdateOrderTitle);
    }

    @Description("Verify the user checkout with the payment method by Cheque")
    @Test()
    public void Order_05_Checkout_Order_Cheque_Payment() {
        shoppingCartPage = PageGeneratorManager.getUserShoppingCartPage(driver);
        shoppingCartPage.enterToDynamicTextboxById("Search Store", "small-searchterms", productCheckoutTitle);
        shoppingCartPage.clickToButtonByText("Search");
        searchPage = PageGeneratorManager.getUserSearchPage(driver);
        productDetailsPage = searchPage.clickToDynamicProductByTitle(productCheckoutTitle);
        productCheckoutPrice = productDetailsPage.getProductPrice();
        productDetailsPage.clickToButtonByText("Add to cart");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
        shoppingCartPage = productDetailsPage.clickToShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtCartTable(productCheckoutTitle));
        shoppingCartPage.selectToDefaultDropdownByName("Gift Wrapping options", "checkout_attribute_1", giftWrapCheckout);
        shoppingCartPage.checkToDefaultCheckboxRadioByLabel("I agree with the terms of service and I adhere to them unconditionally");
        shoppingCartPage.clickToButtonByText(" Checkout ");
        productCheckoutPage = PageGeneratorManager.getUserProductCheckoutPage(driver);
        productCheckoutPage.selectToDefaultDropdownByName("Country at Billing Address", "BillingNewAddress.CountryId", countryCheckout);
        productCheckoutPage.selectToDefaultDropdownByName("State at Billing Address", "BillingNewAddress.StateProvinceId", stateCheckout);
        productCheckoutPage.enterToDynamicTextboxById("City at Billing Address", "BillingNewAddress_City", cityCheckout);
        productCheckoutPage.enterToDynamicTextboxById("Address 1 at Billing Address", "BillingNewAddress_Address1", address1Checkout);
        productCheckoutPage.enterToDynamicTextboxById("Zip Code 1 at Billing Address", "BillingNewAddress_ZipPostalCode", zipCodeCheckout);
        productCheckoutPage.enterToDynamicTextboxById("Phone Number at Billing Address", "BillingNewAddress_PhoneNumber", phoneNumberCheckout);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Billing address");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(shippingMethodCheckout);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Shipping method");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(paymentMethodCheckoutCheque);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment method");
        verifyTrue(productCheckoutPage.isPaymentInfoMessageDisplayed());
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment information");
        verifyTrue(productCheckoutPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, paymentMethodCheckoutCheque));
        verifyTrue(productCheckoutPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, shippingMethodCheckout.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(productCheckoutPage.isProductTitleDisplayedAtCartTable(productCheckoutTitle));
        verifyEquals(productCheckoutPage.getProductPriceByProductTitleAtCartTable(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(productCheckoutPage.getProductQuantityByProductTitleAtCartTable(productCheckoutTitle), productCheckoutQuantity);
        verifyEquals(productCheckoutPage.getProductTotalPriceByProductTitleAtCartTable(productCheckoutTitle), productCheckoutTotalPrice);
        verifyEquals(productCheckoutPage.getGiftWrappingOption(), giftWrapCheckout);
        verifyEquals(productCheckoutPage.getSubTotalPriceAtTotalInfo(), productCheckoutTotalPrice);
        verifyEquals(productCheckoutPage.getFinalTotalPriceAtTotalInfo(), productCheckoutTotalPrice);
        productCheckoutPage.clickToButtonByText("Confirm");
        verifyTrue(productCheckoutPage.isSuccessOrderMessageDisplayed());
        orderNumber = productCheckoutPage.getOrderNumberUnderSuccessMessage();
        customerInfoPage = productCheckoutPage.clickToMyAccountLink();
        customerInfoPage.clickToDynamicLinkAtMainContentByText("Orders");
        orderPage = PageGeneratorManager.getUserOrderPage(driver);
        verifyTrue(orderPage.isOrderNumberDisplayed(orderNumber));
        orderPage.clickToDetailButtonByOrderNumber(orderNumber);
        verifyEquals(orderPage.getOrderNumberAtOrderOverview(), orderNumber);
        verifyEquals(orderPage.getOrderTotalPriceAtOrderOverview(), productCheckoutTotalPrice);
        verifyTrue(orderPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, paymentMethodCheckoutCheque));
        verifyTrue(orderPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, shippingMethodCheckout.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(orderPage.isProductTitleDisplayedAtOrderDetail(productCheckoutTitle));
        verifyEquals(orderPage.getUnitProductPriceByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(orderPage.getProductQuantityByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutQuantity);
        verifyEquals(orderPage.getProductTotalPriceByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutTotalPrice);
        verifyEquals(orderPage.getGiftWrappingOptionAtOrderDetail(), giftWrapCheckout);
        verifyEquals(orderPage.getSubTotalPriceAtOrderDetail(), productCheckoutTotalPrice);
        verifyEquals(orderPage.getFinalTotalPriceAtOrderDetail(), productCheckoutTotalPrice);

    }

    @Description("Verify the user checkout with the payment method by Card")
    @Test()
    public void Order_06_Checkout_Order_Card_Payment() {
        shoppingCartPage.enterToDynamicTextboxById("Search Store", "small-searchterms", productCheckoutTitle);
        shoppingCartPage.clickToButtonByText("Search");
        searchPage = PageGeneratorManager.getUserSearchPage(driver);
        productDetailsPage = searchPage.clickToDynamicProductByTitle(productCheckoutTitle);
        productCheckoutPrice = productDetailsPage.getProductPrice();
        productDetailsPage.clickToButtonByText("Add to cart");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "The product has been added to your shopping cart");
        productDetailsPage.closeHeaderBarNotification();
        shoppingCartPage = productDetailsPage.clickToShoppingCartLink();
        verifyTrue(shoppingCartPage.isProductTitleDisplayedAtCartTable(productCheckoutTitle));
        shoppingCartPage.selectToDefaultDropdownByName("Gift Wrapping options", "checkout_attribute_1", giftWrapCheckout);
        shoppingCartPage.checkToDefaultCheckboxRadioByLabel("I agree with the terms of service and I adhere to them unconditionally");
        shoppingCartPage.clickToButtonByText(" Checkout ");
        productCheckoutPage = PageGeneratorManager.getUserProductCheckoutPage(driver);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Billing address");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(shippingMethodCheckout);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Shipping method");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(paymentMethodCheckoutCard);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment method");
        productCheckoutPage.enterToDynamicTextboxById("Cardholder name", "CardholderName", cardholderCameCheckout);
        productCheckoutPage.enterToDynamicTextboxById("Card number", "CardNumber", cardNumberCheckout);
        productCheckoutPage.selectToDefaultDropdownByName("Expiration month", "ExpireMonth", expirationMonthCheckout);
        productCheckoutPage.selectToDefaultDropdownByName("Expiration year", "ExpireYear", expirationYearCheckout);
        productCheckoutPage.enterToDynamicTextboxById("Card code", "CardCode", cardCodeCheckout);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment information");
        verifyTrue(productCheckoutPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, paymentMethodCheckoutCard));
        verifyTrue(productCheckoutPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, shippingMethodCheckout.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(productCheckoutPage.isProductTitleDisplayedAtCartTable(productCheckoutTitle));
        verifyEquals(productCheckoutPage.getProductPriceByProductTitleAtCartTable(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(productCheckoutPage.getProductQuantityByProductTitleAtCartTable(productCheckoutTitle), productCheckoutQuantity);
        verifyEquals(productCheckoutPage.getProductTotalPriceByProductTitleAtCartTable(productCheckoutTitle), productCheckoutTotalPrice);
        verifyEquals(productCheckoutPage.getGiftWrappingOption(), giftWrapCheckout);
        verifyEquals(productCheckoutPage.getSubTotalPriceAtTotalInfo(), productCheckoutTotalPrice);
        verifyEquals(productCheckoutPage.getFinalTotalPriceAtTotalInfo(), productCheckoutTotalPrice);
        productCheckoutPage.clickToButtonByText("Confirm");
        verifyTrue(productCheckoutPage.isSuccessOrderMessageDisplayed());
        orderNumber = productCheckoutPage.getOrderNumberUnderSuccessMessage();
        customerInfoPage = productCheckoutPage.clickToMyAccountLink();
        customerInfoPage.clickToDynamicLinkAtMainContentByText("Orders");
        orderPage = PageGeneratorManager.getUserOrderPage(driver);
        verifyTrue(orderPage.isOrderNumberDisplayed(orderNumber));
        orderPage.clickToDetailButtonByOrderNumber(orderNumber);
        verifyEquals(orderPage.getOrderNumberAtOrderOverview(), orderNumber);
        verifyEquals(orderPage.getOrderTotalPriceAtOrderOverview(), productCheckoutTotalPrice);
        verifyTrue(orderPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, paymentMethodCheckoutCard));
        verifyTrue(orderPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, zipCodeCheckout, shippingMethodCheckout.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(orderPage.isProductTitleDisplayedAtOrderDetail(productCheckoutTitle));
        verifyEquals(orderPage.getUnitProductPriceByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(orderPage.getProductQuantityByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutQuantity);
        verifyEquals(orderPage.getProductTotalPriceByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutTotalPrice);
        verifyEquals(orderPage.getGiftWrappingOptionAtOrderDetail(), giftWrapCheckout);
        verifyEquals(orderPage.getSubTotalPriceAtOrderDetail(), productCheckoutTotalPrice);
        verifyEquals(orderPage.getFinalTotalPriceAtOrderDetail(), productCheckoutTotalPrice);
    }

    @Description("Verify the user can Reorder product properly")
    @Test()
    public void Order_07_Re_Order() {
        orderPage.clickToButtonByText("Re-order");
        shoppingCartPage.increaseQuantityProductByProductTitle(productCheckoutTitle);
        verifyEquals(shoppingCartPage.getProductTotalPriceByProductTitleAtCartTable(productCheckoutTitle), productTotalPriceReOrder);
        productCheckoutPrice = shoppingCartPage.getProductPriceByProductTitleAtCartTable(productCheckoutTitle);
        shoppingCartPage.selectToDefaultDropdownByName("Gift Wrapping options", "checkout_attribute_1", giftWrapCheckout);
        shoppingCartPage.checkToDefaultCheckboxRadioByLabel("I agree with the terms of service and I adhere to them unconditionally");
        shoppingCartPage.clickToCheckoutButton();
        shoppingCartPage.clickToButtonByText("Edit");
        productCheckoutPage.enterToDynamicTextboxById("City at Billing Address", "BillingNewAddress_City", productCityReOrder);
        productCheckoutPage.enterToDynamicTextboxById("Address 1 at Billing Address", "BillingNewAddress_Address1", productAddress1ReOrder);
        productCheckoutPage.enterToDynamicTextboxById("Zip Code 1 at Billing Address", "BillingNewAddress_ZipPostalCode", productZipCodeReOrder);
        productCheckoutPage.enterToDynamicTextboxById("Phone Number at Billing Address", "BillingNewAddress_PhoneNumber", productPhoneNumberReOrder);
        shoppingCartPage.clickToButtonByText("Save");
        productCheckoutPage.clickToContinueButtonBySectionTitle("Billing address");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(shippingMethodReOrder);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Shipping method");
        productCheckoutPage.checkToDefaultCheckboxRadioByLabel(paymentMethodReOrder);
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment method");
        verifyTrue(productCheckoutPage.isPaymentInfoMessageDisplayed());
        productCheckoutPage.clickToContinueButtonBySectionTitle("Payment information");
        verifyTrue(orderPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, productCityReOrder, productAddress1ReOrder, productPhoneNumberReOrder, productZipCodeReOrder, paymentMethodReOrder));
        verifyTrue(orderPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, productCityReOrder, productAddress1ReOrder, productPhoneNumberReOrder, productZipCodeReOrder, shippingMethodReOrder.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(productCheckoutPage.isProductTitleDisplayedAtCartTable(productCheckoutTitle));
        verifyEquals(productCheckoutPage.getProductPriceByProductTitleAtCartTable(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(productCheckoutPage.getProductQuantityByProductTitleAtCartTable(productCheckoutTitle), productQuantityReOrder);
        verifyEquals(productCheckoutPage.getProductTotalPriceByProductTitleAtCartTable(productCheckoutTitle), productTotalPriceReOrder);
        verifyEquals(productCheckoutPage.getGiftWrappingOption(), giftWrapCheckout);
        verifyEquals(productCheckoutPage.getSubTotalPriceAtTotalInfo(), productTotalPriceReOrder);
        verifyEquals(productCheckoutPage.getFinalTotalPriceAtTotalInfo(), productTotalPriceReOrder);
        productCheckoutPage.clickToButtonByText("Confirm");
        verifyTrue(productCheckoutPage.isSuccessOrderMessageDisplayed());
        orderNumber = productCheckoutPage.getOrderNumberUnderSuccessMessage();
        customerInfoPage = productCheckoutPage.clickToMyAccountLink();
        customerInfoPage.clickToDynamicLinkAtMainContentByText("Orders");
        orderPage = PageGeneratorManager.getUserOrderPage(driver);
        verifyTrue(orderPage.isOrderNumberDisplayed(orderNumber));
        orderPage.clickToDetailButtonByOrderNumber(orderNumber);
        verifyEquals(orderPage.getOrderNumberAtOrderOverview(), orderNumber);
        verifyEquals(orderPage.getOrderTotalPriceAtOrderOverview(), productTotalPriceReOrder);
        verifyTrue(orderPage.isBillingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, productCityReOrder, productAddress1ReOrder, productPhoneNumberReOrder, paymentMethodReOrder));
        verifyTrue(orderPage.isShippingAddressInfoDisplayed(Common_01_Login_User.firstName, Common_01_Login_User.lastName, Common_01_Login_User.email, countryCheckout, stateCheckout, productCityReOrder, productAddress1ReOrder, productPhoneNumberReOrder, shippingMethodReOrder.substring(0, shippingMethodCheckout.indexOf(" ("))));
        verifyTrue(orderPage.isProductTitleDisplayedAtOrderDetail(productCheckoutTitle));
        verifyEquals(orderPage.getUnitProductPriceByProductTitleAtOrderDetail(productCheckoutTitle), productCheckoutPrice);
        verifyEquals(orderPage.getProductQuantityByProductTitleAtOrderDetail(productCheckoutTitle), productQuantityReOrder);
        verifyEquals(orderPage.getProductTotalPriceByProductTitleAtOrderDetail(productCheckoutTitle), productTotalPriceReOrder);
        verifyEquals(orderPage.getGiftWrappingOptionAtOrderDetail(), giftWrapCheckout);
        verifyEquals(orderPage.getSubTotalPriceAtOrderDetail(), productTotalPriceReOrder);
        verifyEquals(orderPage.getFinalTotalPriceAtOrderDetail(), productTotalPriceReOrder);
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }


    private WebDriver driver;
    private String parentCategory, subCategory, productOrderTitle, productOrderProcessor, productOrderRAM, productOrderHDD, productOrderOS, productOrderPrice;
    private String productOrderProcessorEdited, productOrderRAMEdited, productOrderHDDEdited, productOrderOSEdited, productOrderPriceEdited, productTotalPrice, productOrderQuantityEdited;
    private String productUpdateOrderTitle, productUpdateOrderTotalPrice, productUpdateOrderQuantity;
    private String productCheckoutTitle, giftWrapCheckout, countryCheckout, stateCheckout, zipCodeCheckout, cityCheckout, address1Checkout, phoneNumberCheckout, shippingMethodCheckout, paymentMethodCheckoutCheque, paymentMethodCheckoutCard, orderNumber, productCheckoutPrice, productCheckoutQuantity, productCheckoutTotalPrice, cardNumberCheckout, cardholderCameCheckout, cardCodeCheckout, expirationYearCheckout, expirationMonthCheckout;

    private String productQuantityReOrder, productCityReOrder, productAddress1ReOrder, productZipCodeReOrder, productPhoneNumberReOrder, shippingMethodReOrder, paymentMethodReOrder, productTotalPriceReOrder;
    private String[] productOrderSoftwareList = {"Microsoft Office [+$50.00]", "Acrobat Reader [+$10.00]", "Total Commander [+$5.00]"};
    private String[] productOrderSoftwareListEdited = {"Microsoft Office [+$50.00]"};
    private DataHelper dataHelper;
    private UserHomePageObject homePage;
    private UserProductDetailsPageObject productDetailsPage;
    private UserProductCategoryPageObject productCategoryPage;
    private UserShoppingCartPageObject shoppingCartPage;
    private UserSearchPageObject searchPage;
    private UserProductCheckoutPageObject productCheckoutPage;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserOrderPageObject orderPage;

}
