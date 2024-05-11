package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserLoginPageUI;
import utilities.dataModel.UserLogin;

public class UserLoginPageObject extends BasePage {
    public UserLoginPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click to login button")
    public void clickToLoginButton() {
        waitForElementClickable(UserLoginPageUI.LOGIN_BUTTON);
        clickToElement(UserLoginPageUI.LOGIN_BUTTON);
    }

    @Step("Verify the login unsuccessful message displayed")
    public boolean isLoginUnsuccessfulMessageDisplayed() {
        waitForElementVisibility(UserLoginPageUI.LOGIN_UNSUCCESSFUL_MESSAGE);
        return isElementDisplayed(UserLoginPageUI.LOGIN_UNSUCCESSFUL_MESSAGE);
    }

    @Step("Get the validation error message at login page ")
    public String getValidationSummaryErrorMessage() {
        waitForElementVisibility(UserLoginPageUI.LOGIN_VALIDATION_SUMMARY_ERROR);
        return getElementText(UserLoginPageUI.LOGIN_VALIDATION_SUMMARY_ERROR);
    }

    public void setToLoginForm(UserLogin userLoginInfo) {
        enterToDynamicTextboxById("Email", "Email", userLoginInfo.getEmail());
        enterToDynamicTextboxById("Password", "Password", userLoginInfo.getPassword());
    }
}
