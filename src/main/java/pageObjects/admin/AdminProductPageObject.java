package pageObjects.admin;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.admin.AdminProductPageUI;

public class AdminProductPageObject extends BasePage {
    public AdminProductPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the `{productName}` is displayed at Table Result")
    public boolean isProductNameDisplayedAtTable(String productName) {
        waitForElementVisibility(AdminProductPageUI.PRODUCT_DATA_AT_TABLE, productName);
        return isElementDisplayed(AdminProductPageUI.PRODUCT_DATA_AT_TABLE, productName);
    }

    @Step("Click to `Search` button")
    public void clickToSearchButton() {
        waitForElementClickable(AdminProductPageUI.SEARCH_BUTTON);
        clickToElement(AdminProductPageUI.SEARCH_BUTTON);
    }

}
