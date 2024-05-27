package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserProductCheckoutPageUI;

public class UserProductCheckoutPageObject extends BasePage {
    public UserProductCheckoutPageObject(WebDriver driver) {
        super(driver);
    }


    @Step("Click to `Continue` button at `{sectionTitle}` section")
    public void clickToContinueButtonBySectionTitle(String sectionTitle) {
        waitForElementClickable(UserProductCheckoutPageUI.CONTINUE_BUTTON_BY_SECTION_TITLE, sectionTitle);
        clickToElement(UserProductCheckoutPageUI.CONTINUE_BUTTON_BY_SECTION_TITLE, sectionTitle);
    }

    @Step("Verify the Payment information message is displayed")
    public boolean isPaymentInfoMessageDisplayed() {
        waitForElementVisibility(UserProductCheckoutPageUI.PAYMENT_MESSAGE_INFORMATION);
        return isElementDisplayed(UserProductCheckoutPageUI.PAYMENT_MESSAGE_INFORMATION);
    }


    @Step("Verify the Order Success Message is displayed")
    public boolean isSuccessOrderMessageDisplayed() {
        waitForElementVisibility(UserProductCheckoutPageUI.SUCCESS_ORDER_MESSAGE);
        return isElementDisplayed(UserProductCheckoutPageUI.SUCCESS_ORDER_MESSAGE);
    }

    @Step("Get Order Number Under Success Order Message")
    public String getOrderNumberUnderSuccessMessage() {
        waitForElementVisibility(UserProductCheckoutPageUI.ORDER_NUMBER_UNDER_SUCCESS_MESSAGE);
        String orderNumber = getElementText(UserProductCheckoutPageUI.ORDER_NUMBER_UNDER_SUCCESS_MESSAGE);
        return orderNumber.substring(orderNumber.indexOf(":") + 1).trim();
    }

    @Step("Get Sub total price at Total Cart Info")
    public String getSubTotalPriceAtTotalInfo() {
        waitForElementVisibility(UserProductCheckoutPageUI.SUB_TOTAL_PRICE_AT_TOTAL_INFO);
        return getElementText(UserProductCheckoutPageUI.SUB_TOTAL_PRICE_AT_TOTAL_INFO);
    }

    @Step("Get Final Total price at Total Cart Info")
    public String getFinalTotalPriceAtTotalInfo() {
        waitForElementVisibility(UserProductCheckoutPageUI.SUB_TOTAL_PRICE_AT_TOTAL_INFO);
        return getElementText(UserProductCheckoutPageUI.FINAL_TOTAL_PRICE_AT_TOTAL_INFO);

    }

    @Step("Get Wrapping Option")
    public String getGiftWrappingOption() {
        waitForElementVisibility(UserProductCheckoutPageUI.GIFT_WRAPPING_OPTION);
        String fullWrappingOption = getElementText(UserProductCheckoutPageUI.GIFT_WRAPPING_OPTION);
        return fullWrappingOption.substring(fullWrappingOption.indexOf(":") + 1).trim();
    }


}
