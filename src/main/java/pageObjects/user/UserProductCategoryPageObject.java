package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.NopCommerceBasePageUI;
import pageUIs.user.UserProductCategoryPageUI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserProductCategoryPageObject extends BasePage {
    public UserProductCategoryPageObject(WebDriver driver) {
        super(driver);
    }


    @Step("Verify the product name is sorted from A to Z")
    public boolean isProductNameSortAsc() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForAllElementVisibility(NopCommerceBasePageUI.PRODUCT_TITLE_IN_PRODUCT_ITEM);
        return isDataStringSortAscLamDa(NopCommerceBasePageUI.PRODUCT_TITLE_IN_PRODUCT_ITEM);
    }

    @Step("Verify the product name is sorted from Z to A")
    public boolean isProductNameSortDesc() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForAllElementVisibility(NopCommerceBasePageUI.PRODUCT_TITLE_IN_PRODUCT_ITEM);
        return isDataStringSortDescLamDa(NopCommerceBasePageUI.PRODUCT_TITLE_IN_PRODUCT_ITEM);
    }

    @Step("Verify the product price is sorted from high to low")
    public boolean isProductPriceSortDesc() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForAllElementVisibility(NopCommerceBasePageUI.PRODUCT_PRICE_IN_PRODUCT_ITEM);
        List<WebElement> productPriceElements = getListElements(NopCommerceBasePageUI.PRODUCT_PRICE_IN_PRODUCT_ITEM);
        List<Float> productPriceList = productPriceElements.stream().map(productPrice -> Float.parseFloat(productPrice.getText().substring(1).replace(".", "").replace(",", "."))).toList();
        List<Float> sortedProductPriceList = new ArrayList<Float>(productPriceList);
        Collections.sort(sortedProductPriceList);
        Collections.reverse(sortedProductPriceList);
        return productPriceList.equals(sortedProductPriceList);
    }

    @Step("Verify the product price is sorted from low to high")
    public boolean isProductPriceSortAsc() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForAllElementVisibility(NopCommerceBasePageUI.PRODUCT_PRICE_IN_PRODUCT_ITEM);
        List<WebElement> productPriceElements = getListElements(NopCommerceBasePageUI.PRODUCT_PRICE_IN_PRODUCT_ITEM);
        List<Float> productPriceList = productPriceElements.stream().map(productPrice -> Float.parseFloat(productPrice.getText().substring(1).replace(".", "").replace(",", "."))).toList();
        List<Float> sortedProductPriceList = new ArrayList<Float>(productPriceList);
        Collections.sort(sortedProductPriceList);
        return productPriceList.equals(sortedProductPriceList);
    }

    @Step("Get current active page")
    public int getCurrentActivePage() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForElementVisibility(UserProductCategoryPageUI.CURRENT_ACTIVE_PAGE);
        return Integer.parseInt(getElementText(UserProductCategoryPageUI.CURRENT_ACTIVE_PAGE));
    }

    @Step("Verify the next icon Pagination is displayed")
    public boolean isNextIconPaginationDisplayed() {
        waitForElementVisibility(UserProductCategoryPageUI.NEXT_ICON_PAGINATION);
        return isElementDisplayed(UserProductCategoryPageUI.NEXT_ICON_PAGINATION);
    }

    @Step("Verify the previous icon Pagination is displayed")
    public boolean isPreviousIconPaginationDisplayed() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        waitForElementVisibility(UserProductCategoryPageUI.PREVIOUS_ICON_PAGINATION);
        return isElementDisplayed(UserProductCategoryPageUI.PREVIOUS_ICON_PAGINATION);
    }

    @Step("Navigate to Product page {pageNumber}")
    public void clickToDynamicPaginationByPageNumber(int pageNumber) {
        waitForElementClickable(UserProductCategoryPageUI.DYNAMIC_PAGINATION_BY_PAGE_NUMBER, String.valueOf(pageNumber));
        clickToElementByJS(UserProductCategoryPageUI.DYNAMIC_PAGINATION_BY_PAGE_NUMBER, String.valueOf(pageNumber));
    }

    @Step("Verify the pagination is not displayed")
    public boolean isPaginationProductUndisplayed() {
        waitForElementInvisibility(NopCommerceBasePageUI.AJAX_LOADING_PRODUCT_LIST);
        return isElementUndisplayed(UserProductCategoryPageUI.CURRENT_ACTIVE_PAGE);
    }
}
