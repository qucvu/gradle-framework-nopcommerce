package pageUIs;

public class NopCommerceBasePageUI {
    public final static String DYNAMIC_TEXTBOX_BY_ID = "id=%s";
    public final static String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
    public final static String DYNAMIC_BUTTON_BY_NAME = "css=button[name='%s']";
    public final static String DYNAMIC_LINK_BY_TEXT_USER_MAIN_CONTENT = "xpath=//div[@id='main']//a[contains(text(), '%s')]";
    public final static String DYNAMIC_FOOTER_LINK_BY_TEXT_USER = "xpath=//div[contains(@class, 'footer')]//a[contains(text(), '%s')]";
    public final static String DYNAMIC_HEADER_LINK_BY_TEXT_USER = "xpath=//div[contains(@class, 'header')]//ul[contains(@class, 'notmobile')]//a[contains(text(), '%s')]";
    public final static String REGISTER_LINK = "css=a.ico-register";
    public final static String LOGOUT_LINK = "css=a.ico-logout";
    public final static String LOGIN_LINK = "css=a.ico-login";
    public final static String WHIST_LIST_LINK = "css=a.ico-wishlist";
    public final static String MY_ACCOUNT_LINK = "CSS=a.ico-account";
    public final static String SHOPPING_CART_LINK = "CSS=a.ico-cart";
    public final static String DYNAMIC_ELEMENT_BY_ID = "id=%s";
    public final static String DYNAMIC_DROPDOWN_BY_NAME = "css=select[name='%s']";
    public final static String MESSAGE_AT_HEADER_BAR_NOTIFICATION = "css=div.bar-notification>p";
    public final static String CLOSE_BAR_NOTIFICATION = "css=div.bar-notification>span.close";
    public final static String DYNAMIC_PRODUCT_TITLE_BY_TITLE = "xpath=//h2[contains(@class,'product-title')]//a[text()='%s']";
    public final static String DYNAMIC_CHECKBOX_RADIO_BY_LABEL = "xpath=//label[text()='%s']//preceding-sibling::input";
    public final static String DYNAMIC_ACTIVE_LINK_BY_TEXT = "XPATH=//li[contains(@class, 'active')]//a[contains(text(), '%s')]";
    public final static String PRODUCT_TITLE_IN_PRODUCT_ITEM = "css=div.product-item h2.product-title>a";
    public final static String PRODUCT_PRICE_IN_PRODUCT_ITEM = "css=div.product-item div.prices>span.price";
    public final static String PRODUCT_ITEM_QUANTITY = "css=div.item-grid div.product-item";
    public final static String ICON_USER_HOME_PAGE = "css=div.header-logo>a";
    public final static String ADD_TO_COMPARE_BUTTON_BY_PRODUCT_TITLE = "xpath=//a[text()='%s']/parent::h2[contains(@class, 'product-title')]/following-sibling::div[contains(@class, 'add-info')]//button[@title='Add to compare list']";
    public final static String DYNAMIC_ACTUAL_PRODUCT_PRICE_BY_PRODUCT_TITLE = "XPATH=//a[text()='%s']/parent::h2[contains(@class, 'product-title')]/following-sibling::div[contains(@class, 'add-info')]//span[contains(@class, 'actual-price')]";
    public final static String AJAX_LOADING_PRODUCT_LIST = "css=div.ajax-products-busy";
    public final static String BILLING_ADDRESS_LIST_BY_INFO = "xpath=//div[contains(@class, 'billing-info-wrap')]//ul[contains(@class, 'info-list') and contains(.,'%s')]";
    public final static String SHIPPING_ADDRESS_LIST_BY_INFO = "XPATH=//div[contains(@class, 'shipping-info-wrap')]//ul[contains(@class, 'info-list') and contains(.,'%s')]";

    public static final String QUANTITY_BY_PRODUCT_TITLE_AT_CART_TABLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class,'quantity')]//span";
    public static final String PRODUCT_TITLE_AT_CART_TABLE = "xpath=//table[contains(@class, 'cart')]//a[contains(@class, 'product-name') and text()='%s']";
    public static final String UNIT_PRODUCT_PRICE_BY_PRODUCT_TITLE_AT_CART_TABLE = "xpath=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class, 'unit-price')]/span";
    public static final String PRODUCT_TOTAL_PRICE_BY_PRODUCT_TITLE_AT_CART_TABLE = "XPATH=//a[contains(@class, 'product-name') and text()='%s']/parent::td/following-sibling::td[contains(@class, 'subtotal')]/span";

    public final static String CONTENT_HEADER_BY_HEADER_ADMIN_PAGE = "XPATH=//div[contains(@class, 'content-header')]/h1[contains(text(), '%s')]";
    public final static String DYNAMIC_NAV_LINK_TREE_VIEW_SECTION_ADMIN_PAGE = "xpath=//p[contains(text(), '%s')]/parent::a/parent::li[contains(@class, 'has-treeview')]";
    public final static String DYNAMIC_ACTIVE_NAV_LINK_TREE_VIEW_SECTION_ADMIN_PAGE = "xpath=//p[contains(text(), '%s')]/parent::a[contains(@class, 'active')]/parent::li[contains(@class, 'has-treeview')]";
    public final static String DYNAMIC_NAV_LINK_ITEM_BY_PARENT_SECTION = "XPATH=//p[contains(text(), '%s')]/parent::a/parent::li[contains(@class, 'has-treeview')]/ul[contains(@class, 'nav-treeview')]//p[contains(text(), '%s')]/parent::a";
    public final static String ITEM_QUANTITY_RESULT_ADMIN_PAGE = "css=table.dataTable>tbody>tr";
    public final static String EMPTY_TABLE_MESSAGE_ADMIN_PAGE = "xpath=//td[contains(@class, 'dataTables_empty') and text() = 'No data available in table']";
    public final static String EMPTY_TABLE_MESSAGE_BY_TABLE_ID_ADMIN_PAGE = "xpath=//table[@id='%s']//td[contains(@class, 'dataTables_empty') and text() = 'No data available in table']";

    public final static String DYNAMIC_CHECKBOX_RADIO_BY_LABEL_ADMIN_PAGE = "xpath=//label[text()='%s']/parent::div/parent::div/following-sibling::div[1]/input";

    public final static String SEARCH_BUTTON_ADMIN_PAGE = "css=button.btn-search";
    public final static String ADD_NEW_BUTTON_ADMIN_PAGE = "XPATH=//a[contains(., 'Add new') and contains(@class, 'btn')]";
    public final static String ALERT_MESSAGE_ADMIN_PAGE = "XPATH=//div[contains(@class, 'alert') and contains(., '%s')]";
    public final static String EDIT_LINK_AT_TABLE_ROW_BY_EMAIL_CUSTOMER = "xpath=//table[contains(@class, 'dataTable')]/tbody//td[text()='%s']/following-sibling::td/a[contains(@href,'Edit')]";
    public final static String DELETE_LINK_AT_TABLE_ROW_BY_EMAIL_CUSTOMER = "xpath=//table[contains(@class, 'dataTable')]/tbody//td[text()='%s']/following-sibling::td/a[contains(text(),'Delete')]";
}
