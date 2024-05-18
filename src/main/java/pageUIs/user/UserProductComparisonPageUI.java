package pageUIs.user;

public class UserProductComparisonPageUI {
    public final static String PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE = "XPATH=//table[contains(@class, 'compare-products-table')]//tr[contains(@class, 'product-name')]//a[text()='%s']";
    public final static String PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_COMPARE_PRODUCT_TABLE = "xpath=//a[text()='%s']/ancestor::tr/following-sibling::tr[contains(@class, 'product-price')]//td[text()='%s']";
    public final static String NO_ITEM_COMPARE_MESSAGE = "XPATH=//div[contains(@class, 'no-data') and text()='You have no items to compare.']";
}
