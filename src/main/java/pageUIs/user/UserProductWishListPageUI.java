package pageUIs.user;

public class UserProductWishListPageUI {
    public static final String PRODUCT_TITLE_AT_WISH_LIST_TABLE = "XPATH=//div[contains(@class, 'wishlist-content')]//table//a[contains(@class, 'product-name') and text()='%s']";
    public static final String WISH_LIST_SHARE_LINK = "css=a.share-link";
    public static final String WISH_LIST_TITLE_BY_FULL_NAME = "xpath=//div[contains(@class, 'page-title')]//h1[contains(text(), '%s')]";
    public static final String ADD_TO_CART_CHECKBOX_BY_PRODUCT_TITLE = "XPATH=//a[contains(@class, 'product-name') and text()='%s']//parent::td/preceding-sibling::td[contains(@class,'add-to-cart')]//input";
    public static final String REMOVE_WISH_LIST_BUTTON_BY_PRODUCT_TITLE = "xpath=//a[contains(@class, 'product-name') and contains(text(),'%s')]/parent::td/following-sibling::td[contains(@class, 'remove-from-cart')]/button";
    public static final String REMOVE_WISH_LIST_BUTTON_LIST = "css=td.remove-from-cart>button";
    public static final String EMPTY_WISH_LIST_MESSAGE = "xpath=//div[contains(@class, 'no-data') and contains(text(), 'The wishlist is empty!')]";

}





