package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.user.*;

public class PageGeneratorManager {
    @Step("Initialize the User Login Page")
    public static UserLoginPageObject getUserLoginPage(WebDriver driver) {
        return new UserLoginPageObject(driver);
    }

    @Step("Initialize the User Register Page")
    public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
        return new UserRegisterPageObject(driver);
    }

    @Step("Initialize the User Home Page")
    public static UserHomePageObject getUserHomePage(WebDriver driver) {
        return new UserHomePageObject(driver);
    }

    @Step("Initialize the Customer info Page")
    public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
        return new UserCustomerInfoPageObject(driver);
    }

    @Step("Initialize the User Address Page")
    public static UserAddressInformationPageObject getUserAddressPage(WebDriver driver) {
        return new UserAddressInformationPageObject(driver);
    }

    @Step("Initialize the User Product details Page")
    public static UserProductDetailsPageObject getUserProductDetailsPage(WebDriver driver) {
        return new UserProductDetailsPageObject(driver);
    }

    @Step("Initialize the User Search Page")
    public static UserSearchPageObject getUserSearchPage(WebDriver driver) {
        return new UserSearchPageObject(driver);
    }


    @Step("Initialize the User Product Category Page")
    public static UserProductCategoryPageObject getUserProductCategoryPage(WebDriver driver) {
        return new UserProductCategoryPageObject(driver);
    }

}
