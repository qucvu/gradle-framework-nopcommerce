package pageObjects.user;

import commons.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageUIs.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {
    public UserCustomerInfoPageObject(WebDriver driver) {
        super(driver);
    }


    @Step("Check to gender checkbox with value {gender}")
    public void selectToGenderCheckbox(String gender) {
        waitForElementClickable(UserCustomerInfoPageUI.GENDER_CHECKBOX_BY_LABEL, gender);
        checkToDefaultCheckboxRadio(UserCustomerInfoPageUI.GENDER_CHECKBOX_BY_LABEL, gender);
    }

    @Step("Verify the {gender} radio is checked")
    public boolean isGenderCheckedByLabel(String gender) {
        waitForElementClickable(UserCustomerInfoPageUI.GENDER_CHECKBOX_BY_LABEL, gender);
        return isElementSelected(UserCustomerInfoPageUI.GENDER_CHECKBOX_BY_LABEL, gender);
    }
}
