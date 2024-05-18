package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserProductDetailsPageUI;

public class UserProductDetailsPageObject extends BasePage {
    public UserProductDetailsPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to {ratingLabel} rating checkbox")
    public void clickToRatingCheckboxByValue(String ratingLabel) {
        waitForElementClickable(UserProductDetailsPageUI.DYNAMIC_RATING_CHECKBOX_BY_LABEL, ratingLabel);
        checkToDefaultCheckboxRadio(UserProductDetailsPageUI.DYNAMIC_RATING_CHECKBOX_BY_LABEL, ratingLabel);
    }

    @Step("Get review title from name: {firstName}")
    public String getReviewTitleByFirstName(String firstName) {
        waitForElementVisibility(UserProductDetailsPageUI.REVIEW_TITLE_BY_FIRST_NAME, firstName);
        return getElementText(UserProductDetailsPageUI.REVIEW_TITLE_BY_FIRST_NAME, firstName);
    }

    @Step("Get review rating from name: {firstName}")
    public String getReviewRatingLabelByFirstName(String firstName) {
        waitForElementVisibility(UserProductDetailsPageUI.REVIEW_RATING_BY_FIRST_NAME, firstName);
        String value = getElementText(UserProductDetailsPageUI.REVIEW_RATING_BY_FIRST_NAME, firstName);
        switch (value) {
            case "1":
                return "Bad";
            case "2":
                return "Not good";
            case "3":
                return "Not bad but also not excellent";
            case "4":
                return "Good";
            case "5":
                return "Excellent";
            default:
                throw new RuntimeException("Invalid Rating value");
        }
    }

    @Step("Get review content from name: {firstName}")
    public String getReviewContentByFirstName(String firstName) {
        waitForElementVisibility(UserProductDetailsPageUI.REVIEW_CONTENT_BY_FIRST_NAME, firstName);
        return getElementText(UserProductDetailsPageUI.REVIEW_CONTENT_BY_FIRST_NAME, firstName);
    }

    @Step("Enter to review content textarea with value: {reviewContent}")
    public void enterToReviewContentTextArea(String reviewContent) {
        waitForElementVisibility(UserProductDetailsPageUI.REVIEW_CONTENT_TEXTAREA);
        sendKeyToElement(UserProductDetailsPageUI.REVIEW_CONTENT_TEXTAREA, reviewContent);
    }

    @Step("Click to `Add to WhistList button`")
    public void clickToAddToWhistListButtonAtProductOverview() {
        waitForElementClickable(UserProductDetailsPageUI.ADD_TO_WHIST_LIST_BUTTON_AT_PRODUCT_OVERVIEW);
        clickToElement(UserProductDetailsPageUI.ADD_TO_WHIST_LIST_BUTTON_AT_PRODUCT_OVERVIEW);
    }
}
