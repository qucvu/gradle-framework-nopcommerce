package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageObjects.admin.AdminDashboardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.admin.AdminManageCustomerPageObject;
import pageObjects.admin.AdminProductPageObject;
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

    @Step("Initialize the User Product Whist List Page")
    public static UserProductWishListPageObject getUserProductWishListPage(WebDriver driver) {
        return new UserProductWishListPageObject(driver);
    }


    @Step("Initialize the User Shopping Cart Page")
    public static UserShoppingCartPageObject getUserShoppingCartPage(WebDriver driver) {
        return new UserShoppingCartPageObject(driver);
    }

    @Step("Initialize the User Product Comparison Page ")
    public static UserProductComparisonPageObject getUserProductComparisonPage(WebDriver driver) {
        return new UserProductComparisonPageObject(driver);
    }

    @Step("Initialize the User Recent Product View  Page ")
    public static UserProductViewRecentPageObject getUserProductViewRecentPage(WebDriver driver) {
        return new UserProductViewRecentPageObject(driver);
    }

    @Step("Initialize the User Product Checkout Page ")
    public static UserProductCheckoutPageObject getUserProductCheckoutPage(WebDriver driver) {
        return new UserProductCheckoutPageObject(driver);
    }

    @Step("Initialize the User Order Page")
    public static UserOrderPageObject getUserOrderPage(WebDriver driver) {
        return new UserOrderPageObject(driver);
    }

    @Step("Initialize the Admin Login Page")
    public static AdminLoginPageObject getAdminLoginPage(WebDriver driver) {
        return new AdminLoginPageObject(driver);
    }

    @Step("Initialize the Admin Dashboard Page")
    public static AdminDashboardPageObject getAdminDashboardPage(WebDriver driver) {
        return new AdminDashboardPageObject(driver);
    }


    @Step("Initialize the Admin Product Page")
    public static AdminProductPageObject getAdminProductPage(WebDriver driver) {
        return new AdminProductPageObject(driver);
    }

    @Step("Initialize the Admin Manage Customer Page")
    public static AdminManageCustomerPageObject getAdminManageCustomerPage(WebDriver driver) {
        return new AdminManageCustomerPageObject(driver);
    }


}
