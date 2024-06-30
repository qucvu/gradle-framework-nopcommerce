package com.nopcommerce.admin;

import com.nopcommerce.common.Common_02_Login_Admin;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminManageCustomerPageObject;
import testData.AddressCustomerData;
import utilities.DataHelper;

@Feature("Admin Customer")
public class Admin_03_Customer extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        dataHelper = DataHelper.getDataHelper();
        emailCustomer = dataHelper.getUserEmail();
        passwordCustomer = dataHelper.getPassword();
        firstNameCustomer = dataHelper.getFirstName();
        lastNameCustomer = dataHelper.getLastName();
        genderCustomer = dataHelper.getHumanGender();
        dobCustomer = dataHelper.getBirthdayWithFormat("MM/dd/yyyy");
        dayOfDob = String.valueOf(Integer.parseInt(dobCustomer.split("/")[1]));
        monthOfDob = String.valueOf(Integer.parseInt(dobCustomer.split("/")[0]));
        companyNameCustomer = dataHelper.getCompanyName();
        customerRoles = "Guests";
        adminComment = "This is the Testing Comment, pls ignore";
        editedEmailCustomer = dataHelper.getUserEmail();
        editedFirstNameCustomer = dataHelper.getFirstName();
        editedLastNameCustomer = dataHelper.getLastName();
        editedDobCustomer = dataHelper.getBirthdayWithFormat("MM/dd/yyyy");
        editedAdminComment = "Edited - This is the Testing Comment, pls ignore";
        editedCompanyNameCustomer = dataHelper.getCompanyName();
        addressCustomerData = AddressCustomerData.getAddressCustomerData();
        editedAddressCustomerData = AddressCustomerData.getAddressCustomerData();
        driver = getBrowserDriver(browserName, browserVersion, environment.adminUrl());
        adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
        adminLoginPage.setCookies(Common_02_Login_Admin.loggedCookies);
        adminLoginPage.refreshCurrentPage();
        adminLoginPage.clickToButtonByText("Log in");
        adminDashboardPage = PageGeneratorManager.getAdminDashboardPage(driver);
        verifyTrue(adminDashboardPage.isContentHeaderDisplayedByHeaderAdminPage("Dashboard"));
        adminDashboardPage.clickToDynamicNavTreeViewLinkAdminPage("Customers");
        adminDashboardPage.clickToDynamicNavLinkByParentSectionAdminPage("Customers", "Customers");
        adminManageCustomerPage = PageGeneratorManager.getAdminManageCustomerPage(driver);
        verifyTrue(adminManageCustomerPage.isContentHeaderDisplayedByHeaderAdminPage("Customers"));
    }

    @Description("Verify the Admin can create a customer")
    @Test
    public void Customer_01_Create_Customer() {
        adminManageCustomerPage.clickToAddNewButtonAdminPage();
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "Email", emailCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Password", "Password", passwordCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "FirstName", firstNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "LastName", lastNameCustomer);
        adminManageCustomerPage.checkToGenderCheckboxByLabel(genderCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("DateOfBirth", "DateOfBirth", dobCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Company Name", "Company", companyNameCustomer);
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.checkToActiveCheckbox();
        adminManageCustomerPage.enterToDynamicTextboxById("Admin Comment", "AdminComment", adminComment);
        adminManageCustomerPage.clickToButtonByName("save-continue");
        verifyTrue(adminManageCustomerPage.isAlertMessagePageDisplayed("The new customer has been added successfully."));
        verifyEquals(adminManageCustomerPage.getValueTextboxById("Email", "Email"), emailCustomer);
        verifyEquals(adminManageCustomerPage.getValueTextboxById("First Name", "FirstName"), firstNameCustomer);
        verifyEquals(adminManageCustomerPage.getValueTextboxById("Last Name", "LastName"), lastNameCustomer);
        verifyTrue(adminManageCustomerPage.isGenderCheckboxCheckedByLabel(genderCustomer));
        verifyEquals(adminManageCustomerPage.getValueTextboxById("Company", "Company"), companyNameCustomer);
        verifyEquals(adminManageCustomerPage.getQuantityCustomerRoles(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerRolesDisplayed(customerRoles));
        verifyEquals(adminManageCustomerPage.getDynamicElementTextById("Admin Comment", "AdminComment"), adminComment);
        adminManageCustomerPage.clickToBackToCustomerList();
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", firstNameCustomer, lastNameCustomer), companyNameCustomer));

    }


    @Description("Verify the Admin can search customer with email")
    @Test
    public void Customer_02_Search_Customer_With_Email() {
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "SearchEmail", emailCustomer);
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyEquals(adminManageCustomerPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", firstNameCustomer, lastNameCustomer), companyNameCustomer));
    }

    @Description("Verify the Admin can search customer with First Name and Last Name")
    @Test
    public void Customer_03_Search_Customer_With_Name() {
        adminManageCustomerPage.clearDynamicTextBoxById("Email", "SearchEmail");
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "SearchFirstName", firstNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "SearchLastName", lastNameCustomer);
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyEquals(adminManageCustomerPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", firstNameCustomer, lastNameCustomer), companyNameCustomer));

    }

    @Description("Verify the Admin can search customer with Company")
    @Test
    public void Customer_04_Search_Customer_With_Company() {
        adminManageCustomerPage.clearDynamicTextBoxById("First Name", "SearchFirstName");
        adminManageCustomerPage.clearDynamicTextBoxById("Last Name", "SearchLastName");
        adminManageCustomerPage.enterToDynamicTextboxById("Search Company", "SearchCompany", companyNameCustomer);
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyEquals(adminManageCustomerPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", firstNameCustomer, lastNameCustomer), companyNameCustomer));

    }

    @Description("Verify the Admin can search customer with Full Data")
    @Test
    public void Customer_05_Search_Customer_With_Data() {
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "SearchFirstName", firstNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "SearchLastName", lastNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "SearchEmail", emailCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Search Company", "SearchCompany", companyNameCustomer);
        adminManageCustomerPage.selectToDefaultDropdownByName("Search Month of Birth", "SearchMonthOfBirth", monthOfDob);
        adminManageCustomerPage.selectToDefaultDropdownByName("Search Day of Birth", "SearchDayOfBirth", dayOfDob);
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyEquals(adminManageCustomerPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", firstNameCustomer, lastNameCustomer), companyNameCustomer));
    }

    @Description("Verify the Admin can edit the customer")
    @Test
    public void Customer_06_Edit_Customer() {
        adminManageCustomerPage.clickToEditLinkByEmailAtAdminTable(customerRoles);
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "Email", editedEmailCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "FirstName", editedFirstNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "LastName", editedLastNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("DateOfBirth", "DateOfBirth", editedDobCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Company Name", "Company", editedCompanyNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Admin Comment", "AdminComment", editedAdminComment);
        adminManageCustomerPage.clickToButtonByName("save");
        verifyTrue(adminManageCustomerPage.isAlertMessagePageDisplayed("The customer has been updated successfully."));
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "SearchFirstName", editedFirstNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "SearchLastName", editedLastNameCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "SearchEmail", editedEmailCustomer);
        adminManageCustomerPage.enterToDynamicTextboxById("Search Company", "SearchCompany", editedCompanyNameCustomer);
        adminManageCustomerPage.selectToDefaultDropdownByName("Search Month of Birth", "SearchMonthOfBirth", String.valueOf(Integer.parseInt(editedDobCustomer.split("/")[0])));
        adminManageCustomerPage.selectToDefaultDropdownByName("Search Day of Birth", "SearchDayOfBirth", String.valueOf(Integer.parseInt(editedDobCustomer.split("/")[1])));
        adminManageCustomerPage.selectToCustomerRoles(customerRoles);
        adminManageCustomerPage.clickToSearchButtonAdminPage();
        verifyEquals(adminManageCustomerPage.getItemResultQuantityAdminPage(), 1);
        verifyTrue(adminManageCustomerPage.isCustomerInfoDisplayedAtCustomerTable(customerRoles.substring(0, customerRoles.length() - 1), String.format("%s %s", editedFirstNameCustomer, editedLastNameCustomer), editedCompanyNameCustomer));

    }

    @Description("Verify the Admin can add new the Customer Address")
    @Test
    public void Customer_07_Add_New_Address_Customer() {
        adminManageCustomerPage.clickToEditLinkByEmailAtAdminTable(customerRoles);
        adminManageCustomerPage.openAddressCardAdminPage();
        adminManageCustomerPage.clickToAddNewAddressButton();
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "Address_FirstName", addressCustomerData.firstName);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "Address_LastName", addressCustomerData.lastName);
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "Address_Email", addressCustomerData.email);
        adminManageCustomerPage.enterToDynamicTextboxById("Company Name", "Address_Company", addressCustomerData.companyName);
        adminManageCustomerPage.selectToDefaultDropdownByName("Country Address", "Address.CountryId", addressCustomerData.country);
        adminManageCustomerPage.selectToDefaultDropdownByName("State Address", "Address.StateProvinceId", addressCustomerData.state);
        adminManageCustomerPage.enterToDynamicTextboxById("City", "Address_City", addressCustomerData.city);
        adminManageCustomerPage.enterToDynamicTextboxById("Address1", "Address_Address1", addressCustomerData.address1);
        adminManageCustomerPage.enterToDynamicTextboxById("Zip code", "Address_ZipPostalCode", addressCustomerData.zipCode);
        adminManageCustomerPage.enterToDynamicTextboxById("Phone number", "Address_PhoneNumber", addressCustomerData.phoneNumber);
        adminManageCustomerPage.clickToSaveAddressButton();
        verifyTrue(adminManageCustomerPage.isAlertMessagePageDisplayed("The new address has been added successfully."));
        adminManageCustomerPage.clickToBackToCustomerDetailsLink();
        verifyTrue(adminManageCustomerPage.isDataAddressCustomerDisplayedAtTableByEmail(addressCustomerData.email, addressCustomerData.firstName, addressCustomerData.lastName, addressCustomerData.phoneNumber, addressCustomerData.country, addressCustomerData.state, addressCustomerData.address1, addressCustomerData.city, addressCustomerData.zipCode));

    }

    @Description("Verify the Admin can edit the existing Customer Address")
    @Test
    public void Customer_08_Edit_Address_Customer() {
        adminManageCustomerPage.clickToEditLinkByEmailAtAdminTable(addressCustomerData.email);
        adminManageCustomerPage.enterToDynamicTextboxById("First Name", "Address_FirstName", editedAddressCustomerData.firstName);
        adminManageCustomerPage.enterToDynamicTextboxById("Last Name", "Address_LastName", editedAddressCustomerData.lastName);
        adminManageCustomerPage.enterToDynamicTextboxById("Email", "Address_Email", editedAddressCustomerData.email);
        adminManageCustomerPage.enterToDynamicTextboxById("Company Name", "Address_Company", editedAddressCustomerData.companyName);
        adminManageCustomerPage.selectToDefaultDropdownByName("Country Address", "Address.CountryId", editedAddressCustomerData.country);
        adminManageCustomerPage.selectToDefaultDropdownByName("State Address", "Address.StateProvinceId", editedAddressCustomerData.state);
        adminManageCustomerPage.enterToDynamicTextboxById("City", "Address_City", editedAddressCustomerData.city);
        adminManageCustomerPage.enterToDynamicTextboxById("Address1", "Address_Address1", editedAddressCustomerData.address1);
        adminManageCustomerPage.enterToDynamicTextboxById("Zip code", "Address_ZipPostalCode", editedAddressCustomerData.zipCode);
        adminManageCustomerPage.enterToDynamicTextboxById("Phone number", "Address_PhoneNumber", editedAddressCustomerData.phoneNumber);
        adminManageCustomerPage.clickToSaveAddressButton();
        verifyTrue(adminManageCustomerPage.isAlertMessagePageDisplayed("The address has been updated successfully."));
        adminManageCustomerPage.clickToBackToCustomerDetailsLink();
        verifyTrue(adminManageCustomerPage.isDataAddressCustomerDisplayedAtTableByEmail(editedAddressCustomerData.email, editedAddressCustomerData.firstName, editedAddressCustomerData.lastName, editedAddressCustomerData.phoneNumber, editedAddressCustomerData.country, editedAddressCustomerData.state, editedAddressCustomerData.address1, editedAddressCustomerData.city, editedAddressCustomerData.zipCode));
    }

    @Description("Verify the Admin can delete the existing Customer Address")
    @Test
    public void Customer_09_Delete_Address_Customer() {
        adminManageCustomerPage.clickToDeleteLinkByEmailAtAdminTable(editedAddressCustomerData.email);
        adminManageCustomerPage.acceptAlert();
        verifyTrue(adminManageCustomerPage.isEmptyDataTableMessageDisplayedByTableIdAdminPage("customer-addresses-grid"));
    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();

    }

    private WebDriver driver;
    private String emailCustomer, passwordCustomer, firstNameCustomer, lastNameCustomer, genderCustomer, dobCustomer, companyNameCustomer, customerRoles, adminComment, dayOfDob, monthOfDob;
    private String editedEmailCustomer, editedFirstNameCustomer, editedLastNameCustomer, editedDobCustomer, editedCompanyNameCustomer, editedAdminComment;
    private AddressCustomerData addressCustomerData, editedAddressCustomerData;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private AdminManageCustomerPageObject adminManageCustomerPage;
    private DataHelper dataHelper;
}
