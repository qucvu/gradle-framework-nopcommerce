package pageUIs.user;

public class UserProductCheckoutPageUI {
    public static final String CONTINUE_BUTTON_BY_SECTION_TITLE = "XPATH=//h2[text()='%s']/ancestor::li//button[text()='Continue']";
    public static final String PAYMENT_MESSAGE_INFORMATION = "xpath=//div[contains(@class, 'section payment-info')]//table//p[contains(., 'NOP SOLUTIONS')]";
    public static final String SUCCESS_ORDER_MESSAGE = "xpath=//div[contains(@class, 'order-completed')]/div[contains(@class, 'title')]/strong[text()='Your order has been successfully processed!']";
    public static final String ORDER_NUMBER_UNDER_SUCCESS_MESSAGE = "css=div.order-completed div.order-number>strong";
    public static final String GIFT_WRAPPING_OPTION = "css=div.cart-options>div";
    public static final String SUB_TOTAL_PRICE_AT_TOTAL_INFO = "CSS=div.totals tr.order-subtotal>td.cart-total-right";
    public static final String FINAL_TOTAL_PRICE_AT_TOTAL_INFO = "CSS=div.totals tr.order-subtotal>td.cart-total-right";

}
