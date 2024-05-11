package pageUIs.user;

public class UserRegisterPageUI {
    public final static String REGISTER_BUTTON = "ID=register-button";
    public final static String REGISTER_SUCCESS_MESSAGE = "xpath=//div[contains(@class,'result') and text()='Your registration completed']";
    public final static String REGISTER_VALIDATION_SUMMARY_ERROR = "CSS=div.validation-summary-errors li";
    public final static String INVALID_PASSWORD_MESSAGE_UNDER_PASSWORD_TEXTBOX = "xpath=//span[@data-valmsg-for='Password' and contains(text(), 'must have at least 6 characters and not greater than 64 characters')]";
}
