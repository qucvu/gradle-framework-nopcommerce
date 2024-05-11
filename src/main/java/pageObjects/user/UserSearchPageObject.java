package pageObjects.user;

import commons.BasePage;
import commons.GlobalConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserSearchPageUI;

public class UserSearchPageObject extends BasePage {
    public UserSearchPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to search button")
    public void clickToSearchButton() {
        waitForElementClickable(UserSearchPageUI.SEARCH_PRODUCT_BUTTON);
        clickToElement(UserSearchPageUI.SEARCH_PRODUCT_BUTTON);
    }

    @Step("Get Message search result")
    public String getMessageSearchProductResult() {
        waitForElementVisibility(UserSearchPageUI.SEARCH_RESULT_MESSAGE);
        return getElementText(UserSearchPageUI.SEARCH_RESULT_MESSAGE);
    }

    @Step("Get quantity product item")
    public int getQuantityProductItemAtSearchPage() {
        waitForAllElementVisibility(UserSearchPageUI.PRODUCT_ITEM_SEARCH_RESULT);
        overrideImplicitTimeout(GlobalConstants.SHORT_TIMEOUT);
        return getElementsSize(UserSearchPageUI.PRODUCT_ITEM_SEARCH_RESULT);
    }

    @Step("Verify the product item list is undisplayed")
    public boolean isProductItemSearchUndisplayed() {
        return isElementUndisplayed(UserSearchPageUI.PRODUCT_ITEM_SEARCH_RESULT);
    }


}
