package com.nopcommerce.user;


import commons.BaseTest;
import commons.PageGeneratorManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObjects.user.UserHomePageObject;

@Feature("User sort/display/paging data")
public class User_05_Sort_Display_Paging extends BaseTest {

    @Parameters({"browser"})
    @BeforeClass
    public void beforeClass(String browserName) {
        driver = getBrowserDriver(browserName, environment.endUserUrl());
        Allure.step("Pre-condition: Go to the sub menu of homepage");
        homePage = PageGeneratorManager.getUserHomePage(driver);
    }

    @Test
    @Feature("Search with empty data")
    public void Search_01_Empty_Date() {
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private UserHomePageObject homePage;


}
