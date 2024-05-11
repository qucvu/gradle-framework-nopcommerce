package com.nopcommerce.user;


import com.nopcommerce.common.Common_01_Login_User;
import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.user.*;
import utilities.DataHelper;

import java.util.Date;

@Feature("User My account Page")
public class User_03_My_Account extends BaseTest {

    @Parameters({"browser", "runConfig", "osName", "hubPort"})
    @BeforeClass
    public void beforeClass(String browserName, String runConfig, @Optional("windows") String osName, @Optional("4444") String port) {
        dataHelper = DataHelper.getDataHelper();
        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        email = dataHelper.getUserEmail();
        dob = dataHelper.getBirthday();
        companyName = dataHelper.getCompanyName();
        gender = dataHelper.getHumanGender();
        country = "United States";
        state = "Alabama";
        postalCode = dataHelper.getPostalCode();
        phoneNumber = dataHelper.getPhoneNumber();
        city = dataHelper.getCity();
        address1 = dataHelper.getAddress();
        newPassword = dataHelper.getPassword();
        oldPassword = Common_01_Login_User.password;
        productTitle = "Build your own computer";
        reviewTitle = "Good";
        reviewContent = "Nice product";
        ratingLabel = "Not goods";
        if (runConfig.equals("local")) {
            driver = getBrowserDriver(browserName, environment.endUserUrl());
        } else {
            driver = getBrowserDriver(browserName, environment.endUserUrl(), osName, port);
        }
        Allure.step("Pre-condition: Login User successfully");
        homePage = PageGeneratorManager.getUserHomePage(driver);
        homePage.setCookies(Common_01_Login_User.loggedCookies);
        homePage.refreshCurrentPage();
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());
    }

    @Test
    @Description("Update customer information")
    public void MyAccount_01_Update_Customer_Info() {
        customerInfoPage = homePage.clickToMyAccountLink();
        customerInfoPage.selectToGenderCheckbox(gender);
        customerInfoPage.enterToDynamicTextboxById("First name", "FirstName", firstName);
        customerInfoPage.enterToDynamicTextboxById("Last name", "LastName", lastName);
        customerInfoPage.selectToDefaultDropdownByName("Day", "DateOfBirthDay", getDayFromDate(dob));
        customerInfoPage.selectToDefaultDropdownByName("Day", "DateOfBirthMonth", getMonthFromDate(dob));
        customerInfoPage.selectToDefaultDropdownByName("Day", "DateOfBirthYear", getYearFromDate(dob));
        customerInfoPage.enterToDynamicTextboxById("Email", "Email", email);
        customerInfoPage.enterToDynamicTextboxById("Company name", "Company", companyName);
        customerInfoPage.clickToButtonByText("Save");
        verifyEquals(customerInfoPage.getMessageAtBarNotification(), "The customer info has been updated successfully.");
        verifyTrue(customerInfoPage.isGenderCheckedByLabel(gender));
        verifyEquals(customerInfoPage.getValueTextboxById("First name", "FirstName"), firstName);
        verifyEquals(customerInfoPage.getValueTextboxById("Last name", "LastName"), lastName);
        verifyEquals(customerInfoPage.getValueTextboxById("Email", "Email"), email);
        verifyEquals(customerInfoPage.getItemValueDefaultDropdownByName("Day", "DateOfBirthDay"), getDayFromDate(dob));
        verifyEquals(customerInfoPage.getItemValueDefaultDropdownByName("Month", "DateOfBirthMonth"), getDayFromDate(dob));
        verifyEquals(customerInfoPage.getItemValueDefaultDropdownByName("Year", "DateOfBirthYear"), getDayFromDate(dob));
        verifyEquals(customerInfoPage.getValueTextboxById("Company name", "Company"), companyName);

    }

    @Test
    @Description("Add address information")
    public void MyAccount_02_Add_Address_Information() {
        customerInfoPage.clickToDynamicLinkAtMainContentByText("Addresses");
        addressPage = PageGeneratorManager.getUserAddressPage(driver);
        addressPage.clickToButtonByText("Add new");
        addressPage.enterToDynamicTextboxById("First name", "Address_FirstName", firstName);
        addressPage.enterToDynamicTextboxById("Last name", "Address_LastName", lastName);
        addressPage.enterToDynamicTextboxById("Email", "Address_Email", email);
        addressPage.enterToDynamicTextboxById("Company name", "Address_Company", companyName);
        addressPage.selectToDefaultDropdownByName("Country", "Address.CountryId", country);
        addressPage.selectToDefaultDropdownByName("State", "Address.StateProvinceId", state);
        addressPage.enterToDynamicTextboxById("City", "Address_City", city);
        addressPage.enterToDynamicTextboxById("Address1", "Address_Address1", address1);
        addressPage.enterToDynamicTextboxById("Postal code", "Address_ZipPostalCode", postalCode);
        addressPage.enterToDynamicTextboxById("Phone number", "Address_PhoneNumber", phoneNumber);
        addressPage.clickToButtonByText("Save");
        verifyEquals(addressPage.getMessageAtBarNotification(), "The new address has been added successfully.");
        verifyTrue(addressPage.isDataAddressInfoDisplayed("First name", firstName));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Last name", lastName));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Email", email));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Phone number", phoneNumber));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Company name", companyName));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Country", country));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("City", city));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Address 1", address1));
        verifyTrue(addressPage.isDataAddressInfoDisplayed("Postal code", postalCode));

    }

    @Test
    @Description("Change successfully")
    public void MyAccount_03_Change_Password() {
        addressPage.clickToDynamicLinkAtMainContentByText("Change password");
        customerInfoPage = PageGeneratorManager.getUserCustomerInfoPage(driver);
        customerInfoPage.enterToDynamicTextboxById("Old password", "OldPassword", oldPassword);
        customerInfoPage.enterToDynamicTextboxById("New password", "NewPassword", newPassword);
        customerInfoPage.enterToDynamicTextboxById("Confirm password", "ConfirmNewPassword", newPassword);
        customerInfoPage.clickToButtonByText("Change password");
        verifyEquals(customerInfoPage.getMessageAtBarNotification(), "Password was changed");
        customerInfoPage.closeHeaderBarNotification();
        homePage = customerInfoPage.clickToLogoutLink();
        loginPage = homePage.clickToLoginLink();
        loginPage.enterToDynamicTextboxById("Email", "Email", email);
        loginPage.enterToDynamicTextboxById("Password", "Password", oldPassword);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isLoginUnsuccessfulMessageDisplayed());
        verifyEquals(loginPage.getValidationSummaryErrorMessage(), "The credentials provided are incorrect");

        loginPage.enterToDynamicTextboxById("Email", "Email", email);
        loginPage.enterToDynamicTextboxById("Password", "Password", newPassword);
        loginPage.clickToLoginButton();
        homePage = PageGeneratorManager.getUserHomePage(driver);
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());


    }


    @Test
    @Description("Review product successfully")
    public void MyAccount_04_Review_Product() {
        productDetailsPage = homePage.clickToDynamicProductByTitle(productTitle);
        productDetailsPage.clickToDynamicLinkAtMainContentByText("Add your review");
        productDetailsPage.enterToDynamicTextboxById("Review Title", "AddProductReview_Title", reviewTitle);
        productDetailsPage.enterToReviewContentTextArea(reviewContent);
        productDetailsPage.clickToRatingCheckboxByValue("Not Good");
        productDetailsPage.clickToButtonByText("Submit review");
        verifyEquals(productDetailsPage.getMessageAtBarNotification(), "Product review is successfully added.");
        verifyEquals(productDetailsPage.getReviewTitleByFirstName(firstName), reviewTitle);
        verifyEquals(productDetailsPage.getReviewRatingLabelByFirstName(firstName), ratingLabel);
        verifyEquals(productDetailsPage.getReviewContentByFirstName(firstName), reviewContent);

    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePageObject homePage;

    private String gender, firstName, lastName, email, companyName, country, state, city, address1, postalCode, phoneNumber, newPassword, oldPassword;
    private String productTitle, reviewTitle, reviewContent, ratingLabel;
    private Date dob;
    private DataHelper dataHelper;
    private UserCustomerInfoPageObject customerInfoPage;
    private UserAddressInformationPageObject addressPage;
    private UserLoginPageObject loginPage;
    private UserProductDetailsPageObject productDetailsPage;


}
