package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserProductComparisonPageUI;

public class UserProductComparisonPageObject extends BasePage {
    public UserProductComparisonPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the {productTitle} is displayed at Compare Product Table")
    public boolean isProductTitleDisplayedAtCompareProductTable(String productTitle) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE, productTitle);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE, productTitle);
    }

    @Step("Verify the `{productTitle}` with price: `{productPrice}`is displayed at Compare Product Table")
    public boolean isProductPriceDisplayedByProductTitleAtCompareProductTable(String productTitle, String productPrice) {
        waitForElementVisibility(UserProductComparisonPageUI.PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE, productTitle, productPrice);
        return isElementDisplayed(UserProductComparisonPageUI.PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE, productTitle, productPrice);

    }

    @Step("Verify the `You have no items to compare.` is displayed at Compare products page")
    public boolean isEmptyItemCompareMessageDisplayed() {
        waitForElementVisibility(UserProductComparisonPageUI.NO_ITEM_COMPARE_MESSAGE);
        return isElementDisplayed(UserProductComparisonPageUI.NO_ITEM_COMPARE_MESSAGE);
    }

    @Step("Verify the product {productTitle} is undisplayed at `Compare Product` table")
    public boolean isProductTitleUnDisplayedAtCompareProductTable(String productTitle) {
        return isElementUndisplayed(UserProductComparisonPageUI.PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE, productTitle);
    }
}
