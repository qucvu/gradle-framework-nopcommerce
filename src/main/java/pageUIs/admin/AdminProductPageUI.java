package pageUIs.admin;

public class AdminProductPageUI {
    public final static String PRODUCT_DATA_AT_TABLE = "xpath=//table[@id='products-grid']//tbody//td[text()='%s']";
    public final static String PRODUCT_DETAIL_TITLE_BY_PRODUCT_NAME = "xpath=//div[contains(@class, 'content-header')]//h1[contains(@class, 'float-left') and contains(text(), 'Edit product details - %s')]";

}
