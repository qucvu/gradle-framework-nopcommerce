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
    public boolean isProductTitleDisplayedAtMiniShoppingCart(String productTitle) {
        waitForElementVisibility(UserShoppingCartPageUI.PRODUCT_TITLE_AT_MINI_SHOPPING_CART, productTitle);
        return isElementDisplayed(UserShoppingCartPageUI.PRODUCT_TITLE_AT_MINI_SHOPPING_CART, productTitle);
    }
}
