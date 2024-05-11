package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserRegisterPageUI;

public class UserRegisterPageObject extends BasePage {
    public UserRegisterPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to Register button")
    public void clickToRegisterButton() {
        waitForElementClickable(UserRegisterPageUI.REGISTER_BUTTON);
        clickToElement(UserRegisterPageUI.REGISTER_BUTTON);
    }

    @Step("Verify the success register message is displayed")
    public boolean isSuccessRegisterMessageDisplayed() {
        waitForElementVisibility(UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
        return isElementDisplayed(UserRegisterPageUI.REGISTER_SUCCESS_MESSAGE);
    }

    @Step("Get the validation error message at the form")
    public String getValidationSummaryErrorMessage() {
        waitForElementVisibility(UserRegisterPageUI.REGISTER_VALIDATION_SUMMARY_ERROR);
        return getElementText(UserRegisterPageUI.REGISTER_VALIDATION_SUMMARY_ERROR);
    }

    @Step("Verify the invalid password message is displayed ")
    public boolean isInvalidPasswordCharacterMessageDisplayed() {
        waitForElementVisibility(UserRegisterPageUI.INVALID_PASSWORD_MESSAGE_UNDER_PASSWORD_TEXTBOX);
        return isElementDisplayed(UserRegisterPageUI.INVALID_PASSWORD_MESSAGE_UNDER_PASSWORD_TEXTBOX);
    }
}
