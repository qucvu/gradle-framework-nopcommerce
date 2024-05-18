package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.user.UserProductWhistListPageUI;

import java.util.List;

public class UserProductWhistListPageObject extends BasePage {
    public UserProductWhistListPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the {productTitle} is displayed at WhistList table")
    public boolean isProductTitleProductDisplayedAtWhistListTable(String productTitle) {
        waitForElementVisibility(UserProductWhistListPageUI.PRODUCT_TITLE_AT_WHIST_LIST_TABLE, productTitle);
        return isElementDisplayed(UserProductWhistListPageUI.PRODUCT_TITLE_AT_WHIST_LIST_TABLE, productTitle);
    }

    @Step("Click to 'WhistList' share link")
    public void clickToWhistListShareLink() {
        waitForElementClickable(UserProductWhistListPageUI.WHIST_LIST_SHARE_LINK);
        clickToElement(UserProductWhistListPageUI.WHIST_LIST_SHARE_LINK);
    }

    @Step("Verify the {fullName} is displayed at `WhistList` table")
    public boolean isFullNameDisplayedAtWhistListTitle(String fullName) {
        waitForElementVisibility(UserProductWhistListPageUI.WHIST_LIST_TITLE_BY_FULL_NAME, fullName);
        return isElementDisplayed(UserProductWhistListPageUI.WHIST_LIST_TITLE_BY_FULL_NAME, fullName);
    }

    @Step("Check to `Add to Cart` checkbox of product: {productTitle}")
    public void checkToAddToCartCheckboxByProductTitle(String productTitle) {
        waitForElementClickable(UserProductWhistListPageUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_TITLE, productTitle);
        checkToDefaultCheckboxRadio(UserProductWhistListPageUI.ADD_TO_CART_CHECKBOX_BY_PRODUCT_TITLE, productTitle);
    }


    @Step("Verify the product `{productTitle}` is not displayed at whistList Page")
    public boolean isProductTitleProductUndisplayed(String productTitle) {
        return isElementUndisplayed(UserProductWhistListPageUI.PRODUCT_TITLE_AT_WHIST_LIST_TABLE, productTitle);
    }

    @Step("Remove all product Whist List item")
    public void removeAllProductWhistList() {
        waitForAllElementVisibility(UserProductWhistListPageUI.REMOVE_WHIST_LIST_BUTTON_LIST);
        List<WebElement> removeWhistListButtonList = getListElements(UserProductWhistListPageUI.REMOVE_WHIST_LIST_BUTTON_LIST);
        for (WebElement removeWhistListButton : removeWhistListButtonList) {
            clickToElement(removeWhistListButton);
        }
    }

    @Step("Verify the product Whist List is empty")
    public boolean isEmptyWhistListTable() {
        waitForElementVisibility(UserProductWhistListPageUI.EMPTY_WHIST_LIST_MESSAGE);
        return isElementDisplayed(UserProductWhistListPageUI.EMPTY_WHIST_LIST_MESSAGE);
    }
}
