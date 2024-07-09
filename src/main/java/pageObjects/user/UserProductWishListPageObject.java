package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserProductWishListPageUI;

import java.util.List;

public class UserProductWishListPageObject extends BasePage {
    public UserProductWishListPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the {productTitle} is displayed at WishList table")
    public boolean isProductTitleProductDisplayedAtWishListTable(String productTitle) {
        waitForElementVisibility(UserProductWishListPageUI.PRODUCT_TITLE_AT_WISH_LIST_TABLE, productTitle);
        return isElementDisplayed(UserProductWishListPageUI.PRODUCT_TITLE_AT_WISH_LIST_TABLE, productTitle);
    }

    @Step("Click to 'WishList' share link")
    public void clickToWishListShareLink() {
        waitForElementClickable(UserProductWishListPageUI.WISH_LIST_SHARE_LINK);
        clickToElement(UserProductWishListPageUI.WISH_LIST_SHARE_LINK);
    }

    @Step("Verify the {fullName} is displayed at `WishList` table")
    public boolean isFullNameDisplayedAtWishListTitle(String fullName) {
        waitForElementVisibility(UserProductWishListPageUI.WISH_LIST_TITLE_BY_FULL_NAME, fullName);
        return isElementDisplayed(UserProductWishListPageUI.WISH_LIST_TITLE_BY_FULL_NAME, fullName);
    }

    @Step("Check to `Add to Cart` checkbox of product: {productTitle}")
    public void checkToAddToCartCheckboxByProductTitle(String productTitle) {
        waitForElementClickable(UserProductWishListPageUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_TITLE, productTitle);
        checkToDefaultCheckboxRadio(UserProductWishListPageUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_TITLE, productTitle);
    }


    @Step("Verify the product `{productTitle}` is not displayed at WishList Page")
    public boolean isProductTitleProductUndisplayed(String productTitle) {
        return isElementUndisplayed(UserProductWishListPageUI.PRODUCT_TITLE_AT_WISH_LIST_TABLE, productTitle);
    }

    @Step("Remove all product WishList item")
    public void removeAllProductWishList() {
        waitForAllElementVisibility(UserProductWishListPageUI.REMOVE_WISH_LIST_BUTTON_LIST);
        List<WebElement> removeWishListButtonList = getListElements(UserProductWishListPageUI.REMOVE_WISH_LIST_BUTTON_LIST);
        for (WebElement removeWishListButton : removeWishListButtonList) {
            clickToElement(removeWishListButton);
        }
    }

    @Step("Verify the product WishList is empty")
    public boolean isEmptyWishListTable() {
        waitForElementVisibility(UserProductWishListPageUI.EMPTY_WISH_LIST_MESSAGE);
        return isElementDisplayed(UserProductWishListPageUI.EMPTY_WISH_LIST_MESSAGE);
    }
}
