package com.nopcommerce.user;


import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.UserHomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.UserRegisterPageObject;
import utilities.DataHelper;

@Feature("User Register")
public class User_01_Register extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        DataHelper data = DataHelper.getDataHelper();
        firstName = data.getFirstName();
        lastName = data.getLastName();
        email = data.getUserEmail();
        password = data.getPassword();
        invalidEmail = "automation@fc";
        invalidPassword = "12345";
        driver = getBrowserDriver(browserName, browserVersion, environment.endUserUrl());
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickToRegisterLink();

    }

    @Test
    @Description("Register fail with empty data")
    public void Register_01_Empty_Data() {
        registerPage.clickToRegisterButton();
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("First name", "FirstName-error"), "First name is required.");
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("Last name", "LastName-error"), "Last name is required.");
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("Email", "Email-error"), "Email is required.");
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("Confirm password", "ConfirmPassword-error"), "Password is required.");
    }

    @Test
    @Description("Register fail with the invalid email")
    public void Register_02_Invalid_Email() {
        registerPage.enterToDynamicTextboxById("Email", "Email", invalidEmail);
        registerPage.clickToRegisterButton();
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("Email", "Email-error"), "Wrong email");

    }

    @Test
    @Description("Register success with valid data")
    public void Register_03_Register_Success() {
        registerPage.enterToDynamicTextboxById("First name", "FirstName", firstName);
        registerPage.enterToDynamicTextboxById("Last name", "LastName", lastName);
        registerPage.enterToDynamicTextboxById("Email", "Email", email);
        registerPage.enterToDynamicTextboxById("Password", "Password", password);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", password);

        registerPage.clickToRegisterButton();
        verifyTrue(registerPage.isSuccessRegisterMessageDisplayed());
    }

    @Test
    @Description("Register fail with the existing email")
    public void Register_04_Existing_Email() {
        homePage = registerPage.clickToLogoutLink();
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToDynamicTextboxById("First name", "FirstName", firstName);
        registerPage.enterToDynamicTextboxById("Last name", "LastName", lastName);
        registerPage.enterToDynamicTextboxById("Email", "Email", email);
        registerPage.enterToDynamicTextboxById("Password", "Password", password);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", password);
        registerPage.clickToRegisterButton();
        verifyEquals(registerPage.getValidationSummaryErrorMessage(), "The specified email already exists");
    }

    @Test
    @Description("Register fail with invalid password")
    public void Register_05_Invalid_Password() {
        registerPage.enterToDynamicTextboxById("Password", "Password", invalidPassword);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", invalidPassword);
        registerPage.clickToRegisterButton();
        verifyTrue(registerPage.isInvalidPasswordCharacterMessageDisplayed());

    }

    @Test
    @Description("Register fail with unmatched password")
    public void Register_06_Unmatched_Password() {
        registerPage.enterToDynamicTextboxById("Password", "Password", password);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", invalidPassword);
        registerPage.clickToRegisterButton();
        verifyEquals(registerPage.getDynamicErrorMessageUnderTextboxById("Confirm password", "ConfirmPassword-error"), "The password and confirmation password do not match.");
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String firstName, lastName, invalidEmail, email, invalidPassword, password;

    private UserHomePageObject homePage;
    private UserRegisterPageObject registerPage;
    private UserLoginPageObject loginPage;

}
