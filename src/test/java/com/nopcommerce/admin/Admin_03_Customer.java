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
        companyNameCustomer = dataHelper.getCompanyName();
        customerRoles = "Guests";
        adminComment = "This is the Testing Comment, pls ignore";
        driver = getBrowserDriver(browserName, browserVersion, environment.adminUrl());
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
//        adminManageCustomerPage.clickToAddNewButtonAdminPage();

    }


    @Description("Verify the Admin can search customer with email")
    @Test
    public void Customer_02_Search_Customer_With_Email() {
    }

    @Description("Verify the Admin can search customer with First Name and Last Name")
    @Test
    public void Customer_03_Search_Customer_With_Name() {
    }

    @Description("Verify the Admin can search customer with Company")
    @Test
    public void Customer_04_Search_Customer_With_Company() {
    }

    @Description("Verify the Admin can search customer with Full Data")
    @Test
    public void Customer_05_Search_Customer_With_Data() {
    }

    @Description("Verify the Admin can edit the customer")
    @Test
    public void Customer_06_Edit_Customer() {
    }

    @Description("Verify the Admin can add new the Customer Address")
    @Test
    public void Customer_07_Add_New_Customer() {
    }

    @Description("Verify the Admin can edit the existing Customer Address")
    @Test
    public void Customer_08_Edit_Customer() {
    }

    @Description("Verify the Admin can delete the existing Customer Address")
    @Test
    public void Customer_09_Delete_Customer() {
    }


    @AfterClass
    public void afterClass() {
        closeBrowserDriver();

    }

    private WebDriver driver;
    private String emailCustomer, passwordCustomer, firstNameCustomer, lastNameCustomer, genderCustomer, dobCustomer, companyNameCustomer, customerRoles, adminComment;
    private AdminLoginPageObject adminLoginPage;
    private AdminDashboardPageObject adminDashboardPage;
    private AdminManageCustomerPageObject adminManageCustomerPage;
    private DataHelper dataHelper;
}
