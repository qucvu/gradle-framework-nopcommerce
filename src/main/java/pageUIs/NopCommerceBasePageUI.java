package pageUIs;

public class NopCommerceBasePageUI {
    public final static String DYNAMIC_TEXTBOX_BY_ID = "css=input#%s";
    public final static String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public final static String DYNAMIC_LINK_BY_TEXT_USER_MAIN_CONTENT = "xpath=//div[@id='main']//a[contains(text(), '%s')]";
    public final static String DYNAMIC_LINK_BY_TEXT_USER_FOOTER = "xpath=//div[contains(@class,'footer')]//a[contains(text(), '%s')]";
    public final static String REGISTER_LINK = "css=a.ico-register";
    public final static String LOGOUT_LINK = "css=a.ico-logout";
    public final static String LOGIN_LINK = "css=a.ico-login";
    public final static String MY_ACCOUNT_LINK = "CSS=a.ico-account";
    public final static String DYNAMIC_MESSAGE_UNDER_TEXTBOOX_BY_ID = "id=%s";
    public final static String DYNAMIC_DROPDOWN_BY_NAME = "css=select[name='%s']";
    public final static String MESSAGE_AT_HEADER_BAR_NOTIFICATION = "css=div.bar-notification>p";
    public final static String CLOSE_BAR_NOTIFICATION = "css=div.bar-notification>span.close";
    public final static String DYNAMIC_PRODUCT_TITLE_BY_TITLE = "xpath=//h2[contains(@class,'product-title')]//a[text()='%s']";
    public final static String DYNAMIC_CHECKBOX_RADIO_BY_LABEL = "xpath=//label[text()='%s']//preceding-sibling::input";
}
