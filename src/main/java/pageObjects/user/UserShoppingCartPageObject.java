package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserShoppingCartPageUI;

public class UserShoppingCartPageObject extends BasePage {
    public UserShoppingCartPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify product: {productTitle} is displayed at Mini Shopping Cart")
    public boolean isProductTitleDisplayedAtMiniCart(String productTitle) {
        waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_TITLE_AT_MINI_CART, productTitle);
        return isElementDisplayed(UserShoppingCartPageUI.PRODUCT_TITLE_AT_MINI_CART, productTitle);
    }

    @Step("Get Quantity of Product in the Mini Shopping Cart")
    public int getProductQuantityAtMiniCart() {
        waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_QUANTITY_AT_MINI_CART);
        String quantity = getElementText(UserShoppingCartPageUI.PRODUCT_QUANTITY_AT_MINI_CART);
        return Integer.parseInt(quantity.substring(0, quantity.indexOf(" ")));
    }

    @Step("Verify the info `{productInfo}` is displayed at Mini Shopping Cart")
    public boolean isProductInfoDisplayedAtMiniCart(String productInfo) {
        waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_INFO_AT_MINI_CART, productInfo);
        return isElementDisplayed(UserShoppingCartPageUI.PRODUCT_INFO_AT_MINI_CART, productInfo);
    }

    @Step("Verify the info `{productOrderSoftwareList}` is displayed at Mini Shopping Cart")
    public boolean isProductSoftwareOptionsDisplayedAtMiniCart(String[] productOrderSoftwareList) {
        for (String productOrderSoftware : productOrderSoftwareList) {
            waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_INFO_AT_MINI_CART, productOrderSoftware);
            return isElementDisplayed(UserShoppingCartPageUI.PRODUCT_INFO_AT_MINI_CART, productOrderSoftware);
        }
        return false;
    }

    @Step("Get Product Total Price at Mini Shopping Cart")
    public String getPriceTotalAtMiniCart() {
        waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_TOTAL_PRICE_AT_MINI_CART);
        return getElementText(UserShoppingCartPageUI.PRODUCT_TOTAL_PRICE_AT_MINI_CART);
    }
}
