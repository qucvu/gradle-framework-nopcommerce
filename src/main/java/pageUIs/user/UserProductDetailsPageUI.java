package pageUIs.user;

public class UserProductDetailsPageUI {
    public final static String DYNAMIC_RATING_CHECKBOX_BY_LABEL = "css=div.review-rating input[aria-label='Not good']";
    public final static String REVIEW_TITLE_BY_FIRST_NAME = "xpath=//span[text()='%s']/ancestor::div[@class='product-review-item']//div[@class='review-title']/strong";
    public final static String REVIEW_RATING_BY_FIRST_NAME = "xpath=//span[text()='%s']/ancestor::div[@class='product-review-item']//div[@class='product-review-box']/div/div";
    public final static String REVIEW_CONTENT_BY_FIRST_NAME = "XPATH=//span[text()='%s']/ancestor::div[@class='product-review-item']//div[@class='review-content']/div/div";
    public final static String REVIEW_CONTENT_TEXTAREA = "id=AddProductReview_ReviewText";
    public final static String ADD_TO_WISH_LIST_BUTTON_AT_PRODUCT_OVERVIEW = "CSS=div.add-to-wishlist button.add-to-wishlist-button ";
    public final static String PRODUCT_PRICE_AT_OVERVIEW = "css=div.product-price>span";
}
