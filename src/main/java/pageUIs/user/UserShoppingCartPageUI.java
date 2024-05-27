package pageUIs.user;

public class UserShoppingCartPageUI {
    public static final String PRODUCT_TITLE_AT_MINI_CART = "xpath=//div[contains(@class, 'mini-shopping-cart')]//div[contains(@class,'name')]//a[text()='%s']";
    public static final String PRODUCT_QUANTITY_AT_MINI_CART = "css=div.count>a";
    public static final String PRODUCT_INFO_AT_MINI_CART = "XPATH=//div[contains(@class, 'attributes') and contains(., '%s')]";
    public static final String PRODUCT_TOTAL_PRICE_AT_MINI_CART = "css=div.totals>strong";
    public static final String PRODUCT_INFO_BY_PRODUCT_TITLE_AT_CART_TABLE = "XPATH=//table[contains(@class, 'cart')]//a[contains(@class, 'product-name') and text()='%s']//following-sibling::div[contains(@class, 'attributes') and contains(.,'%s')]";
    public static final String EDIT_LINK_BY_PRODUCT_TITLE_AT_CART_TABLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/following-sibling::div[contains(@class, 'edit-item')]/a";
    public static final String REMOVE_BUTTON_BY_PRODUCT_TITLE_AT_CART_TABLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class,'remove-from-cart')]/button";
    public static final String EMPTY_SHOPPING_CART_MESSAGE = "xpath=//div[contains(@class, 'no-data') and contains(text(), 'Your Shopping Cart is empty!')]";
    public static final String QUANTITY_TEXTBOX_BY_PRODUCT_TITLE_AT_CART_TABLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class,'quantity')]//input";
    public static final String CHECKOUT_BUTTON = "id=checkout";
    public static final String QUANTITY_UP_BY_PRODUCT_TITLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class,'quantity')]//div[@class='quantity up']";

}
