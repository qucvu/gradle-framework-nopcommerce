package pageUIs.user;

public class UserProductCategoryPageUI {
    public final static String CURRENT_ACTIVE_PAGE = "css=li.current-page>span";
    public final static String PREVIOUS_ICON_PAGINATION = "xpath=//li[contains(@class, 'previous-page')]/a[text()='Previous']";
    public final static String NEXT_ICON_PAGINATION = "xpath=//li[contains(@class, 'next-page')]/a[text()='Next']";
    public final static String DYNAMIC_PAGINATION_BY_PAGE_NUMBER = "css=li:not(.next-page) a[data-page='%s']";
}
