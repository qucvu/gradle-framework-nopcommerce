package pageUIs.user;

public class UserShoppingCartPageUI {
    public static final String PRODUCT_TITLE_AT_MINI_CART = "xpath=//div[contains(@class, 'mini-shopping-cart')]//div[contains(@class,'name')]//a[text()='%s']";
    public static final String PRODUCT_QUANTITY_AT_MINI_CART = "css=div.count>a";
    public static final String PRODUCT_INFO_AT_MINI_CART = "XPATH=//div[contains(@class, 'attributes') and contains(., '%s')]";
    public static final String PRODUCT_TOTAL_PRICE_AT_MINI_CART = "css=div.totals>strong";
}
