package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserAddressInformationPageUI;

public class UserAddressInformationPageObject extends BasePage {
    public UserAddressInformationPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Verify the address information display {info} with value: {data}")
    public boolean isDataAddressInfoDisplayed(String infoName, String data) {
        waitForElementVisibility(UserAddressInformationPageUI.ADDRESS_INFO_BY_DATA, data);
        return isElementDisplayed(UserAddressInformationPageUI.ADDRESS_INFO_BY_DATA, data);
    }
}
