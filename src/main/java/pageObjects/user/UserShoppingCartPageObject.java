package pageObjects.user;

import commons.BasePage;
import commons.PageGeneratorManager;
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


    @Step("Verify the info `{productInfo}` is displayed at Cart table")
    public boolean isProductInfoDisplayedByProductTitleAtCartTable(String productTitle, String... productInfoList) {
        for (String productInfo : productInfoList) {
            waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_INFO_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle, productInfo);
            return isElementDisplayed(UserShoppingCartPageUI.PRODUCT_INFO_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle, productInfo);
        }
        return false;
    }


    @Step("Click to Edit link at Product `{productTitle}` link at Cart table")
    public UserProductDetailsPageObject clickToEditLinkByProductTitleAtCartTable(String productTitle) {
        waitForElementClickable(UserShoppingCartPageUI.EDIT_LINK_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle);
        clickToElement(UserShoppingCartPageUI.EDIT_LINK_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle);
        return PageGeneratorManager.getUserProductDetailsPage(driver);
    }

    @Step("Click to Remove button at `{productTitle}` row")
    public void clickToRemoveButtonByProductTitleAtCartTable(String productTitle) {
        waitForElementClickable(UserShoppingCartPageUI.REMOVE_BUTTON_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle);
        clickToElement(UserShoppingCartPageUI.REMOVE_BUTTON_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle);
    }

    @Step("Verify the Shopping Cart is empty")
    public boolean isEmptyShoppingCartMessageDisplayed() {
        waitForElementVisibility(UserShoppingCartPageUI.EMPTY_SHOPPING_CART_MESSAGE);
        return isElementDisplayed(UserShoppingCartPageUI.EMPTY_SHOPPING_CART_MESSAGE);
    }

    @Step("Enter to product quantity textbox with the value `{productQuantity}`")
    public void enterToQuantityTextboxByProductTitle(String productTitle, String productQuantity) {
        waitForElementVisibility(UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_TITLE_AT_CART_TABLE, productTitle);
        sendKeyToElementByJS(UserShoppingCartPageUI.QUANTITY_TEXTBOX_BY_PRODUCT_TITLE_AT_CART_TABLE, productQuantity, productTitle);
    }


    @Step("Click to `Checkout` button")
    public void clickToCheckoutButton() {
        waitForElementClickable(UserShoppingCartPageUI.CHECKOUT_BUTTON);
        clickToElement(UserShoppingCartPageUI.CHECKOUT_BUTTON);
    }

    @Step("Increase the quantity Product of Product `{productTitle}`")
    public void increaseQuantityProductByProductTitle(String productTitle) {
        waitForElementClickable(UserShoppingCartPageUI.QUANTITY_UP_BY_PRODUCT_TITLE, productTitle);
        clickToElementByJS(UserShoppingCartPageUI.QUANTITY_UP_BY_PRODUCT_TITLE, productTitle);
    }
}
