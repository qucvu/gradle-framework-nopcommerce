package pageUIs.admin;

public class AdminManageCustomerPageUI {
    public final static String GENDER_CHECKBOX_BY_LABEL = "CSS=input[id=Gender_%s]";
    public final static String CUSTOMER_ROLES_QUANTITY = "CSS=select[name=SelectedCustomerRoleIds] + span ul.select2-selection__rendered>li.select2-selection__choice";
    public final static String SELECTED_CUSTOMER_ROLE_BY_TITLE = "CSS=select[name=SelectedCustomerRoleIds] + span li.select2-selection__choice[title='%s']";
    public final static String ALL_REMOVE_SELECTED_CUSTOMER_ROLES = "CSS=select[name=SelectedCustomerRoleIds] + span ul span.select2-selection__choice__remove";
    public final static String BACK_TO_CUSTOMER_LIST_LINK = "xpath=//a[text()='back to customer list']";
    public final static String ACTIVE_CHECKBOX = "id=Active";
    public final static String CUSTOMER_INFORMATION_AT_CUSTOMER_TABLE = "XPATH=//table[@id='customers-grid']//tbody/tr[contains(., 'Guests')]";
    public final static String ADD_NEW_ADDRESS_BUTTON = "css=#customer-address>div.card-body>button";
    public final static String BACK_TO_CUSTOMER_DETAIL_LINK = "css=a[href*='Admin/Customer/Edit']";
    public final static String ADDRESS_CUSTOMER_INFORMATION_BY_EMAIL_AT_ADDRESS_TABLE = "xpath=//table[@id='customer-addresses-grid']/tbody//td[text()='%s']/parent::tr[contains(., '%s')]";
    public final static String SAVE_ADDRESS_BUTTON = "css=div.float-right>button";
}
