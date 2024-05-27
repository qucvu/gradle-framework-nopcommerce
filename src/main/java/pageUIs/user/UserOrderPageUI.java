package pageUIs.user;

public class UserOrderPageUI {
    public static final String ORDER_NUMBER_AT_ORDER_HISTORY = "XPATH=//div[contains(@class, 'order-item')]//strong[text()='Order Number: %s']";
    public static final String DETAIL_BUTTON_BY_ORDER_NUMBER = "XPATH=//strong[text()='Order Number: %s']/parent::div/following-sibling::div/button";
    public static final String ORDER_NUMBER_AT_ORDER_OVERVIEW = "CSS=div.order-overview>div.order-number";
    public static final String ORDER_TOTAL_PRICE_AT_OVER_OVERVIEW = "css=div.order-overview li.order-total>strong";
    public static final String QUANTITY_BY_PRODUCT_TITLE_AT_ORDER_DETAIL = "xpath=//a[text()='%s']/parent::em/parent::td/following-sibling::td[contains(@class,'quantity')]//span";
    public static final String PRODUCT_TITLE_AT_ORDER_DETAIL = "xpath=//table[contains(@class, 'data-table')]//a[text()='%s']";
    public static final String UNIT_PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL = "xpath=//a[text()='%s']/parent::em/parent::td/following-sibling::td[contains(@class, 'unit-price')]/span";
    public static final String PRODUCT_TOTAL_PRICE_BY_PRODUCT_TITLE_AT_ORDER_DETAIL = "xpath=//a[text()='%s']/parent::em/parent::td/following-sibling::td[contains(@class, 'total')]/span";
    public static final String GIFT_WRAPPING_OPTION_ORDER_DETAIL = "css=div.selected-checkout-attributes";
    public static final String SUB_TOTAL_PRICE_AT_ORDER_DETAIL = "CSS=table.cart-total tr:nth-child(1)>td.cart-total-right";
    public static final String FINAL_TOTAL_PRICE_AT_ORDER_DETAIL = "css=table.cart-total tr:nth-child(4)>td.cart-total-right";

}
