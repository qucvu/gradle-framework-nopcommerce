package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserOrderPageUI;

public class UserOrderPageObject extends BasePage {
    public UserOrderPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the Order Number `{orderNumber}` is displayed")
    public boolean isOrderNumberDisplayed(String orderNumber) {
        waitForElementVisibility(UserOrderPageUI.ORDER_NUMBER_AT_ORDER_HISTORY, orderNumber);
        return isElementDisplayed(UserOrderPageUI.ORDER_NUMBER_AT_ORDER_HISTORY, orderNumber);
    }

    @Step("Click to `Detail` button at Order `{orderNumber}`")
    public void clickToDetailButtonByOrderNumber(String orderNumber) {
        waitForElementClickable(UserOrderPageUI.DETAIL_BUTTON_BY_ORDER_NUMBER, orderNumber);
        clickToElement(UserOrderPageUI.DETAIL_BUTTON_BY_ORDER_NUMBER, orderNumber);
    }

    @Step("Get Order Number At Order Over View")
    public String getOrderNumberAtOrderOverview() {
        waitForElementVisibility(UserOrderPageUI.ORDER_NUMBER_AT_ORDER_OVERVIEW);
        String orderNumber = getElementText(UserOrderPageUI.ORDER_NUMBER_AT_ORDER_OVERVIEW);
        return orderNumber.substring(orderNumber.indexOf("#") + 1);
    }

    @Step("Get Order Total Price at Order Overview")
    public String getOrderTotalPriceAtOrderOverview() {
        waitForElementVisibility(UserOrderPageUI.ORDER_TOTAL_PRICE_AT_OVER_OVERVIEW);
        return getElementText(UserOrderPageUI.ORDER_TOTAL_PRICE_AT_OVER_OVERVIEW);
    }

    @Step("Verify the `{productTitle}` is displayed at Order Details")
    public boolean isProductTitleDisplayedAtOrderDetail(String productTitle) {
        waitForElementVisibility(UserOrderPageUI.PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
        return isElementDisplayed(UserOrderPageUI.PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
    }

    @Step("Get Unit Product Price of Product `{productTitle}` at Order Details")
    public String getUnitProductPriceByProductTitleAtOrderDetail(String productTitle) {
        waitForElementVisibility(UserOrderPageUI.UNIT_PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
        return getElementText(UserOrderPageUI.UNIT_PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
    }

    @Step("Get product quantity of Product `{productTitle}` at Order Details")
    public String getProductQuantityByProductTitleAtOrderDetail(String productTitle) {
        waitForElementVisibility(UserOrderPageUI.QUANTITY_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
        return getElementText(UserOrderPageUI.QUANTITY_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
    }

    @Step("Get Product Total Price of Product `{productTitle}` at Order Details")
    public String getProductTotalPriceByProductTitleAtOrderDetail(String productTitle) {
        waitForElementVisibility(UserOrderPageUI.PRODUCT_TOTAL_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
        return getElementText(UserOrderPageUI.PRODUCT_TOTAL_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL, productTitle);
    }


    @Step("Get Gift Wrapping Option of Product `{productTitle}` at Order Details")
    public String getGiftWrappingOptionAtOrderDetail() {
        waitForElementVisibility(UserOrderPageUI.GIFT_WRAPPING_OPTION_ORDER_DETAIL);
        String giftWrappingOption = getElementText(UserOrderPageUI.GIFT_WRAPPING_OPTION_ORDER_DETAIL);
        return giftWrappingOption.substring(giftWrappingOption.indexOf(":") + 1).trim();
    }

    @Step("Get Sub Total Price of Product `{productTitle}` at Order Details")
    public String getSubTotalPriceAtOrderDetail() {
        waitForElementVisibility(UserOrderPageUI.SUB_TOTAL_PRICE_AT_ORDER_DETAIL);
        return getElementText(UserOrderPageUI.SUB_TOTAL_PRICE_AT_ORDER_DETAIL);
    }

    @Step("Get Sub Total Price of Product `{productTitle}` at Order Details")
    public String getFinalTotalPriceAtOrderDetail() {
        waitForElementVisibility(UserOrderPageUI.FINAL_TOTAL_PRICE_AT_ORDER_DETAIL);
        return getElementText(UserOrderPageUI.FINAL_TOTAL_PRICE_AT_ORDER_DETAIL);

    }
}
