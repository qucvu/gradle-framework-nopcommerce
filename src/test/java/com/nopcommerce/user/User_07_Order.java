package com.nopcommerce.user;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

@Feature("User Page - Order feature")
public class User_07_Order extends BaseTest {

    public User_07_Order() {
    }

    @Parameters({"browserName", "browserVersion"})
    @BeforeClass
    public void beforeClass(String browserName, String browserVersion) {
    }

    @Description(" ")
    @Test
    public void WhistList_Compare_01_Add_To_WhistList() {
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }


    private WebDriver driver;
}
