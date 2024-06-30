package pageObjects.admin;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageUIs.NopCommerceBasePageUI;
import pageUIs.admin.AdminManageCustomerPageUI;

import java.util.List;

public class AdminManageCustomerPageObject extends BasePage {
    public AdminManageCustomerPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Check to Gender `{genderCustomer}` checkbox")
    public void checkToGenderCheckboxByLabel(String genderCustomer) {
        waitForElementClickable(AdminManageCustomerPageUI.GENDER_CHECKBOX_BY_LABEL, genderCustomer);
        checkToDefaultCheckboxRadio(AdminManageCustomerPageUI.GENDER_CHECKBOX_BY_LABEL, genderCustomer);
    }

    @Step("Select to Customer Roles")
    public void selectToCustomerRoles(String customerRoles) {
        removeAllCustomerRoles();
        String[] roles = customerRoles.split(";");
        for (String role : roles) {
            selectToDefaultDropdownByName("Selected Customer RoleIds", "SelectedCustomerRoleIds", role);
        }

    }

    @Step("Remove all Customer roles")
    private void removeAllCustomerRoles() {
        waitForAllElementVisibility(AdminManageCustomerPageUI.ALL_REMOVE_SELECTED_CUSTOMER_ROLES);
        List<WebElement> roleElements = getListElements(AdminManageCustomerPageUI.ALL_REMOVE_SELECTED_CUSTOMER_ROLES);
        for (WebElement element : roleElements) {
            clickToElement(element);
        }
    }

    @Step("Check to Active checkbox")
    public void checkToActiveCheckbox() {
        waitForElementClickable(AdminManageCustomerPageUI.ACTIVE_CHECKBOX);
        checkToDefaultCheckboxRadio(AdminManageCustomerPageUI.ACTIVE_CHECKBOX);
    }

    @Step("Verify the Gender checkbox `{genderCustomer}` is checked")
    public boolean isGenderCheckboxCheckedByLabel(String genderCustomer) {
        waitForElementVisibility(AdminManageCustomerPageUI.GENDER_CHECKBOX_BY_LABEL, genderCustomer);
        return isElementSelected(AdminManageCustomerPageUI.GENDER_CHECKBOX_BY_LABEL, genderCustomer);
    }


    @Step("Get quantity item roles")
    public int getQuantityCustomerRoles() {
        waitForElementVisibility(AdminManageCustomerPageUI.CUSTOMER_ROLES_QUANTITY);
        return getElementsSize(AdminManageCustomerPageUI.CUSTOMER_ROLES_QUANTITY);
    }

    @Step("Verify the Customer Roles `{customerRole}` is displayed")
    public boolean isCustomerRolesDisplayed(String customerRole) {
        waitForElementVisibility(AdminManageCustomerPageUI.SELECTED_CUSTOMER_ROLE_BY_TITLE, customerRole);
        return isElementDisplayed(AdminManageCustomerPageUI.SELECTED_CUSTOMER_ROLE_BY_TITLE, customerRole);
    }

    @Step("Click to `Back to Customer` list")
    public void clickToBackToCustomerList() {
        waitForElementClickable(AdminManageCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
        clickToElement(AdminManageCustomerPageUI.BACK_TO_CUSTOMER_LIST_LINK);
    }

    @Step("Verify the Customer information include: `{valueInfo}` is displayed")
    public boolean isCustomerInfoDisplayedAtCustomerTable(String... information) {
        for (String customerInfo : information) {
            waitForElementVisibility(AdminManageCustomerPageUI.CUSTOMER_INFORMATION_AT_CUSTOMER_TABLE, customerInfo);
            return isElementDisplayed(AdminManageCustomerPageUI.CUSTOMER_INFORMATION_AT_CUSTOMER_TABLE, customerInfo);
        }
        return false;
    }

    @Step("Open Address Card Section")
    public void openAddressCardAdminPage() {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_ELEMENT_BY_ID, "customer-address");
        String classAttribute = getElementAttribute("class", NopCommerceBasePageUI.DYNAMIC_ELEMENT_BY_ID, "customer-address");
        if (classAttribute.contains("collapsed-card")) {
            clickToElement(NopCommerceBasePageUI.DYNAMIC_ELEMENT_BY_ID, "customer-address");
        }
    }

    @Step("Click to 'Add New Address' Button")
    public void clickToAddNewAddressButton() {
        waitForElementClickable(AdminManageCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
        clickToElement(AdminManageCustomerPageUI.ADD_NEW_ADDRESS_BUTTON);
    }

    @Step("Click to 'Back To Customer Details' link")
    public void clickToBackToCustomerDetailsLink() {
        waitForElementClickable(AdminManageCustomerPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);
        clickToElement(AdminManageCustomerPageUI.BACK_TO_CUSTOMER_DETAIL_LINK);

    }

    @Step("Click to 'Save Address' button")
    public void clickToSaveAddressButton() {
        waitForElementClickable(AdminManageCustomerPageUI.SAVE_ADDRESS_BUTTON);
        clickToElement(AdminManageCustomerPageUI.SAVE_ADDRESS_BUTTON);

    }


    @Step("Verify the Data Address Customer are displayed At Address Table by Email Customer")
    public boolean isDataAddressCustomerDisplayedAtTableByEmail(String emailAddress, String... listAddressInfo) {
        for (String addressInfo : listAddressInfo) {
            waitForElementVisibility(AdminManageCustomerPageUI.ADDRESS_CUSTOMER_INFORMATION_BY_EMAIL_AT_ADDRESS_TABLE, emailAddress, addressInfo);
            return isElementDisplayed(AdminManageCustomerPageUI.ADDRESS_CUSTOMER_INFORMATION_BY_EMAIL_AT_ADDRESS_TABLE, emailAddress, addressInfo);
        }
        return false;
    }
}
