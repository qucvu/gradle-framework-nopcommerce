package com.nopcommerce.user;


import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Allure;
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
import testData.dataModel.UserLogin;
import utilities.DataHelper;

@Feature("User Login")
public class User_02_Login extends BaseTest {

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
        dataHelper = DataHelper.getDataHelper();
        userLoginInfo = UserLogin.getUser();
        firstName = dataHelper.getFirstName();
        lastName = dataHelper.getLastName();
        email = dataHelper.getUserEmail();
        password = dataHelper.getPassword();
        unregisteredEmail = String.format("automation%s@gmail.com", generateRandomNumber());
        invalidEmail = "automation@fc";
        userLoginInfo.setEmail(email);
        userLoginInfo.setPassword(password);
        driver = getBrowserDriver(browserName, browserVersion, environment.endUserUrl());
        Allure.step("Pre-condition: REGISTER success the email: " + email);
        homePage = PageGeneratorManager.getUserHomePage(driver);
        registerPage = homePage.clickToRegisterLink();
        registerPage.enterToDynamicTextboxById("First name", "FirstName", firstName);
        registerPage.enterToDynamicTextboxById("Last name", "LastName", lastName);
        registerPage.enterToDynamicTextboxById("Email", "Email", email);
        registerPage.enterToDynamicTextboxById("Password", "Password", password);
        registerPage.enterToDynamicTextboxById("Confirm password", "ConfirmPassword", password);
        registerPage.clickToRegisterButton();
        verifyTrue(registerPage.isSuccessRegisterMessageDisplayed());
        homePage = registerPage.clickToLogoutLink();
        loginPage = homePage.clickToLoginLink();

    }


    @Test
    @Description("Login fail with empty data")
    public void Login_01_Empty_Data() {
        loginPage.clickToLoginButton();
        verifyEquals(loginPage.getDynamicErrorMessageUnderTextboxById("Email", "Email-error"), "Please enter your email");

    }

    @Test
    @Description("Login fail with invalid email")
    public void Login_02_Invalid_Email() {
        loginPage.enterToDynamicTextboxById("Email", "Email", invalidEmail);
        verifyEquals(loginPage.getDynamicErrorMessageUnderTextboxById("Email", "Email-error"), "Wrong email");
    }

    @Test
    @Description("Login fail with Unregistered Email")
    public void Login_03_Unregistered_Email() {
        loginPage.enterToDynamicTextboxById("Email", "Email", unregisteredEmail);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isLoginUnsuccessfulMessageDisplayed());
        verifyEquals(loginPage.getValidationSummaryErrorMessage(), "No customer account found");
    }

    @Test
    @Description("Login fail with valid email and empty password")
    public void Login_04_Empty_Password() {
        loginPage.enterToDynamicTextboxById("Email", "Email", email);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isLoginUnsuccessfulMessageDisplayed());
        verifyEquals(loginPage.getValidationSummaryErrorMessage(), "The credentials provided are incorrect");
    }

    @Test
    @Description("Login fail with wrong password")
    public void Login_05_Wrong_Password() {
        loginPage.enterToDynamicTextboxById("Email", "Email", email);
        loginPage.enterToDynamicTextboxById("Password", "Password", invalidEmail);
        loginPage.clickToLoginButton();
        verifyTrue(loginPage.isLoginUnsuccessfulMessageDisplayed());
        verifyEquals(loginPage.getValidationSummaryErrorMessage(), "The credentials provided are incorrect");
    }

    @Test
    @Description("Login success with valid data")
    public void Login_06_Login_Success() {
        loginPage.setToLoginForm(userLoginInfo);
        loginPage.clickToLoginButton();
        homePage = PageGeneratorManager.getUserHomePage(driver);
        verifyTrue(homePage.isMyAccountLinkDisplayedAtHeader());
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private String invalidEmail, unregisteredEmail, email, firstName, lastName, password;
    private UserHomePageObject homePage;
    private UserLoginPageObject loginPage;
    private UserRegisterPageObject registerPage;
    private UserLogin userLoginInfo;
    private DataHelper dataHelper;

}
