package commons;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.user.*;
import pageUIs.NopCommerceBasePageUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BasePage {
    private final int longTimeout = GlobalConstants.LONG_TIMEOUT;
    private final int shortTimeout = GlobalConstants.SHORT_TIMEOUT;
    WebDriver driver;


    public static BasePage getBasePage(WebDriver driver) {
        return new BasePage(driver);
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected void overrideImplicitTimeout(int shortTimeout) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(shortTimeout));
    }

    @Step("Open page url : {0}")
    public void openPageUrl(String pageUrl) {
        driver.get(pageUrl);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    @Step("Back to the previous page")
    public void backToPage() {
        driver.navigate().back();
    }

    @Step("Refresh the Page")
    public void refreshCurrentPage() {
        driver.navigate().refresh();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Alert waitForAlertPresence() {
        return new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.alertIsPresent());
    }

    protected boolean isAlertPresent() {
        boolean presentFlag = false;
        try {
            driver.switchTo().alert();
            presentFlag = true;
        } catch (NoAlertPresentException ex) {
            ex.printStackTrace();
        }

        return presentFlag;
    }

    @Step("Get all cookies from the current account")
    public Set<Cookie> getAllCookies() {
        return driver.manage().getCookies();
    }

    @Step("Set all cookies for the current account")
    public void setCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(1);
    }

    public void acceptAlert() {
        waitForAlertPresence().accept();
    }

    protected void cancelAlert() {
        waitForAlertPresence().dismiss();
    }

    protected String getAlertText() {
        return waitForAlertPresence().getText();
    }

    protected void sendKeyToAlert(String textValue) {
        waitForAlertPresence().sendKeys(textValue);
    }

    protected String getWindowHandle(WebDriver driver) {
        return driver.getWindowHandle();
    }

    protected void switchToWindowByParentId(String windowId) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            if (!id.equals(windowId)) {
                driver.switchTo().window(id);
            }
        }
    }

    protected void switchToWindowByTitle(String expectedTitlePage) {
        Set<String> allWindowIds = driver.getWindowHandles();
        for (String id : allWindowIds) {
            driver.switchTo().window(id);
            String actualTitle = driver.getTitle();
            if (actualTitle.equals(expectedTitlePage)) {
                break;
            }
        }
    }

    protected void closeOtherTabsWithoutParent(WebDriver driver, String parentId) {
        Set<String> allIds = driver.getWindowHandles();
        for (String id : allIds) {
            if (!id.equals(parentId)) {
                driver.switchTo().window(id).close();
            }
        }
        driver.switchTo().window(parentId);
    }

    public By getByLocator(String locatorType) {
        if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
            return By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
            return By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
            return By.xpath(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=") || locatorType.startsWith("NAME=")) {
            return By.cssSelector(locatorType.substring(5));
        } else {
            throw new RuntimeException("The locator is not supported");
        }
    }

    public static String getDynamicLocator(String locator, String... dynamicValues) {
        return String.format(locator, (Object[]) dynamicValues);
    }

    private WebElement getWebElement(String locatorType) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
        }
        return driver.findElement(getByLocator(locatorType));
    }

    private WebElement getWebElement(String locatorType, String... dynamicValues) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
        }
        return driver.findElement(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
    }

    protected List<WebElement> getListElements(String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected List<WebElement> getListElements(String locatorType, String... dynamicValues) {
        return driver.findElements(getByLocator(getDynamicLocator(locatorType, dynamicValues)));
    }

    protected void clickToElementByJS(String locatorType) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locatorType));
    }

    protected void clickToElementByJS(String locatorType, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(locatorType, dynamicValues));
    }


    protected void clickToElement(String locatorType) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
            clickToElementByJS(locatorType);
        } else {
            getWebElement(locatorType).click();
        }
    }

    protected void clickToElement(WebElement element) {
        element.click();

    }


    protected void clickToElement(String locatorType, String... dynamicValues) {
        if (driver.toString().contains("internet explorer")) {
            sleepInSecond(3);
            clickToElementByJS(locatorType, dynamicValues);
        } else {
            getWebElement(locatorType, dynamicValues).click();
        }
    }

    protected void clearElement(String locatorType) {
        WebElement element = getWebElement(locatorType);
        element.clear();
    }

    protected void clearElement(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        element.clear();
    }


    protected void sendKeyToElement(String locatorType, String textValue) {
        clearElement(locatorType);
        getWebElement(locatorType).sendKeys(textValue);
    }

    protected void sendKeyToElement(String locatorType, String textValue, String... dynamicValues) {
        clearElement(locatorType, dynamicValues);
        getWebElement(locatorType, dynamicValues).sendKeys(textValue);
    }

    protected String getElementText(String locatorType) {
        return getWebElement(locatorType).getText();
    }

    protected String getElementText(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).getText();
    }

    protected void selectItemInDefaultDropdown(String locatorType, String textItem) {
        new Select(getWebElement(locatorType)).selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropdown(String locatorType, String textItem, String... dynamicValues) {
        new Select(getWebElement(locatorType, dynamicValues)).selectByVisibleText(textItem);
    }

    protected String getSelectedItemDefaultDropdown(String locatorType) {
        return new Select(getWebElement(locatorType)).getFirstSelectedOption().getText();
    }

    protected String getSelectedItemDefaultDropdown(String locatorType, String... dynamicValues) {
        return new Select(getWebElement(locatorType, dynamicValues)).getFirstSelectedOption().getText();
    }

    protected boolean isDropdownMultiple(String locatorType) {
        return new Select(getWebElement(locatorType)).isMultiple();
    }

    protected void selectItemDropdown(String parentXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        getWebElement(parentXpath).click();
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                webElement.click();
                break;
            }
        }
    }

    protected void enterAndSelectItemDropdown(String textBoxXpath, String allItemXpath, String expectedTextItem) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(textBoxXpath);
        element.clear();
        element.sendKeys(expectedTextItem);
        sleepInSecond(1);
        List<WebElement> speedDropdownItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(allItemXpath)));
        for (WebElement webElement : speedDropdownItems) {
            if (webElement.getText().trim().equals(expectedTextItem)) {
                jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
                webElement.click();
                break;
            }
        }
    }

    protected String getElementAttribute(String attributeName, String locatorType) {
        return getWebElement(locatorType).getAttribute(attributeName);
    }

    protected String getElementAttribute(String attributeName, String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).getAttribute(attributeName);
    }

    protected String getElementCssValue(String locatorType, String propertyName) {
        return getWebElement(locatorType).getCssValue(propertyName);
    }

    protected String getHexColorFromRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex();
    }

    protected int getElementsSize(String locatorType) {
        return getListElements(locatorType).size();
    }

    protected int getElementsSize(String locatorType, String... dynamicValues) {
        return getListElements(locatorType, dynamicValues).size();
    }

    protected void checkToDefaultCheckboxRadio(String locatorType) {
        WebElement element = getWebElement(locatorType);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void checkToDefaultCheckboxRadio(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(String locatorType) {
        WebElement element = getWebElement(locatorType);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void unCheckToDefaultCheckbox(String locatorType, String... dynamicValues) {
        WebElement element = getWebElement(locatorType, dynamicValues);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(String locatorType) {
        try {
            return getWebElement(locatorType).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locatorType) {
        overrideImplicitTimeout(shortTimeout);
        List<WebElement> elements = getListElements(locatorType);
        overrideImplicitTimeout(longTimeout);
        return elements.size() == 0 || !elements.get(0).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locatorType, String... dynamicValues) {
        overrideImplicitTimeout(shortTimeout);
        List<WebElement> elements = getListElements(locatorType, dynamicValues);
        overrideImplicitTimeout(longTimeout);
        return elements.size() == 0 || !elements.get(0).isDisplayed();
    }

    protected boolean isElementEnabled(String locatorType) {
        return getWebElement(locatorType).isEnabled();
    }

    protected boolean isElementSelected(String locatorType) {
        return getWebElement(locatorType).isSelected();
    }

    protected boolean isElementSelected(String locatorType, String... dynamicValues) {
        return getWebElement(locatorType, dynamicValues).isSelected();
    }

    protected void switchToIframe(String locatorType) {
        new WebDriverWait(driver, Duration.ofSeconds(longTimeout)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getByLocator(locatorType)));
    }

    protected void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    protected void hoverMouseToElement(String locatorType) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locatorType)).build().perform();
    }

    protected void hoverMouseToElement(String locatorType, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.moveToElement(getWebElement(locatorType, dynamicValues)).build().perform();
    }

    protected void rightClickToElement(String locatorType) {
        new Actions(driver).contextClick().perform();
    }

    /**
     * only work for HTML4
     *
     * @param locatorType   Locator of first point
     * @param targetLocator Locator of target point
     */
    protected void dragAndDropElement(String locatorType, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(locatorType), getWebElement(targetLocator));
    }


    protected void pressKeyToElement(String locatorType, Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(locatorType), key).build().perform();
    }

    protected void pressKeyToElement(String locatorType, Keys key, String... dynamicValues) {
        Actions actions = new Actions(driver);
        actions.sendKeys(getWebElement(locatorType, dynamicValues), key).build().perform();
    }

    protected void scrollToBottomPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    protected void scrollToElement(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType));
    }

    protected void scrollToElement(String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(locatorType, dynamicValues));
    }

    protected void highlightElement(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(locatorType);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected void highlightElement(String locatorType, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement element = getWebElement(locatorType, dynamicValues);
        String originalStyle = element.getAttribute("style");
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
        sleepInSecond(1);
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
    }

    protected String getElementValueByJS(String xpathLocator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        xpathLocator = xpathLocator.substring(6);
        return (String) jsExecutor.executeScript("$(document.evaluate(" + xpathLocator + ", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;).val");
    }

    protected void removeAttributeInDOM(String locatorType, String attributeRemove) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(locatorType));
    }

    protected boolean areJQueryAndJSLoadedSuccess() {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
            }
        };

        return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
    }

    protected WebElement getShadowDom(String locatorType) {
        return (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", getWebElement(locatorType));
    }

    protected String getElementValidationMessage(String locatorType) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(locatorType));
    }

    protected boolean isImageLoaded(String locator) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(locator));
    }

    protected boolean isImageLoaded(String locator, String... dynamicValues) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
                getWebElement(locator, dynamicValues));
    }

    protected void waitForElementVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
    }

    protected void waitForElementVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementInvisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));

    }

    protected void waitForElementInvisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementUnDisplayed(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
        overrideImplicitTimeout(longTimeout);

    }

    protected void waitForElementUnDisplayed(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(shortTimeout));
        overrideImplicitTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
        overrideImplicitTimeout(longTimeout);
    }

    protected void waitForAllElementVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));

    }

    protected void waitForAllElementVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicLocator(locatorType, dynamicValues))));

    }

    protected void waitForAllElementInVisibility(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(locatorType)));
    }

    protected void waitForAllElementInVisibility(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListElements(locatorType, dynamicValues)));
    }

    protected void waitForElementClickable(String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
    }

    protected void waitForElementClickable(String locatorType, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locatorType, dynamicValues))));
    }

    protected void waitForElementStaleness(WebDriver driver, String locatorType) {
        WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
        explicitWait.until(ExpectedConditions.stalenessOf(getWebElement(locatorType)));
    }

    protected void uploadFilesByFileName(String locatorType, String... fileNames) {
        String filePath = GlobalConstants.UPLOAD_FILE_FOLDER;
        StringBuilder fullName = new StringBuilder();
        for (String fileName : fileNames) {
            fullName.append(filePath).append(fileName).append("\n");
        }
        fullName = new StringBuilder(fullName.toString().trim());
        getWebElement(locatorType).sendKeys(fullName.toString());
    }

    public boolean isDataStringSortAscLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataStringSortDescLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataFloatSortAscLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<String> dataList = elementList.stream().map(WebElement::getText).toList();
        List<String> sortList = new ArrayList<String>(dataList);
        Collections.sort(sortList);
        return sortList.equals(dataList);
    }

    public boolean isDataFloatSortDescLamDa(String locatorType) {
        List<WebElement> elementList = getListElements(locatorType);
        List<Float> dataList = elementList.stream().map(webElement -> Float.parseFloat(webElement.getText())).toList();
        List<Float> sortList = new ArrayList<Float
                >(dataList);
        Collections.sort(sortList);
        Collections.reverse(sortList);
        return sortList.equals(dataList);
    }


    /**
     * This method input the specific value to the textbox by id
     *
     * @param textID id of input locator
     * @param value  value send to textbox
     */
    @Step("Enter to {nameTextbox} Textbox with value: {value}")
    public void enterToDynamicTextboxById(String nameTextbox, String textID, String value) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textID);
        sendKeyToElement(NopCommerceBasePageUI.DYNAMIC_TEXTBOX_BY_ID, value, textID);
    }

    /**
     * This method click to the button by text
     *
     * @param textValue String text
     */
    @Step("Click to button {textValue}")
    public void clickToButtonByText(String textValue) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_BUTTON_BY_TEXT, textValue);
        clickToElement(NopCommerceBasePageUI.DYNAMIC_BUTTON_BY_TEXT, textValue);
    }

    protected void deleteFileByFilePath(String filePath) {
        try {
            Files.deleteIfExists(
                    Paths.get(filePath));
        } catch (NoSuchFileException e) {
            throw new RuntimeException(
                    "No such file/directory exists");
        } catch (DirectoryNotEmptyException e) {
            throw new RuntimeException("Directory is not empty.");
        } catch (IOException e) {
            throw new RuntimeException("Invalid permissions.");
        }

    }

    protected void writeDataIntoDataRecordByFileName(String data, String fileName) {
        File file = new File(GlobalConstants.DATA_RECORD + fileName + ".txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file, true);
            fr.write(data);
            fr.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Step("Click to Register Link at the header")
    public UserRegisterPageObject clickToRegisterLink() {
        waitForElementClickable(NopCommerceBasePageUI.REGISTER_LINK);
        clickToElement(NopCommerceBasePageUI.REGISTER_LINK);
        return PageGeneratorManager.getUserRegisterPage(driver);
    }

    @Step("Get the error message under '{textboxName}' textbox")
    public String getDynamicErrorMessageUnderTextboxById(String textboxName, String textId) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_MESSAGE_UNDER_TEXTBOOX_BY_ID, textId);
        return getElementText(NopCommerceBasePageUI.DYNAMIC_MESSAGE_UNDER_TEXTBOOX_BY_ID, textId);
    }

    @Step("Click to logout link")
    public UserHomePageObject clickToLogoutLink() {
        waitForElementClickable(NopCommerceBasePageUI.LOGOUT_LINK);
        clickToElementByJS(NopCommerceBasePageUI.LOGOUT_LINK);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    @Step("Click to login link")
    public UserLoginPageObject clickToLoginLink() {
        waitForElementClickable(NopCommerceBasePageUI.LOGIN_LINK);
        clickToElement(NopCommerceBasePageUI.LOGIN_LINK);
        return PageGeneratorManager.getUserLoginPage(driver);
    }

    @Step("Verify the 'My Account' link displays at the header")
    public boolean isMyAccountLinkDisplayedAtHeader() {
        waitForElementVisibility(NopCommerceBasePageUI.MY_ACCOUNT_LINK);
        return isElementDisplayed(NopCommerceBasePageUI.MY_ACCOUNT_LINK);
    }

    @Step("Click to My Account link")
    public UserCustomerInfoPageObject clickToMyAccountLink() {
        waitForElementClickable(NopCommerceBasePageUI.MY_ACCOUNT_LINK);
        clickToElement(NopCommerceBasePageUI.MY_ACCOUNT_LINK);
        return PageGeneratorManager.getUserCustomerInfoPage(driver);
    }

    @Step("Select to {dropdownName} dropdown with value: {item}")
    public void selectToDefaultDropdownByName(String dropdownName, String nameAttribute, String item) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, nameAttribute);
        selectItemInDefaultDropdown(NopCommerceBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, item, nameAttribute);
    }

    @Step("Get Message at bar notification")
    public String getMessageAtBarNotification() {
        waitForElementVisibility(NopCommerceBasePageUI.MESSAGE_AT_HEADER_BAR_NOTIFICATION);
        return getElementText(NopCommerceBasePageUI.MESSAGE_AT_HEADER_BAR_NOTIFICATION);
    }

    @Step("Get attribute value of {textboxName} textbox")
    public String getValueTextboxById(String textboxName, String id) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
        return getElementAttribute("value", NopCommerceBasePageUI.DYNAMIC_TEXTBOX_BY_ID, id);
    }

    @Step("Get item value at {dropdownName} dropdown")
    public String getItemValueDefaultDropdownByName(String dropdownName, String nameAttribute) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, nameAttribute);
        return getSelectedItemDefaultDropdown(NopCommerceBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, nameAttribute);
    }

    @Step("Click to {textValue} link ")
    public void clickToDynamicLinkAtMainContentByText(String textValue) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_LINK_BY_TEXT_USER_MAIN_CONTENT, textValue);
        clickToElement(NopCommerceBasePageUI.DYNAMIC_LINK_BY_TEXT_USER_MAIN_CONTENT, textValue);
    }

    @Step("Close header bar notification")
    public void closeHeaderBarNotification() {
        waitForElementClickable(NopCommerceBasePageUI.CLOSE_BAR_NOTIFICATION);
        clickToElement(NopCommerceBasePageUI.CLOSE_BAR_NOTIFICATION);
    }

    @Step("Go to product details page of product: {productTitle}")
    public UserProductDetailsPageObject clickToDynamicProductByTitle(String productTitle) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, productTitle);
        clickToElement(NopCommerceBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, productTitle);
        return PageGeneratorManager.getUserProductDetailsPage(driver);
    }

    @Step("Click to {textValue} link ")
    public UserSearchPageObject clickToDynamicLinkAtFooterByText(String textValue) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_FOOTER_LINK_BY_TEXT_USER, textValue);
        clickToElement(NopCommerceBasePageUI.DYNAMIC_FOOTER_LINK_BY_TEXT_USER, textValue);
        return PageGeneratorManager.getUserSearchPage(driver);
    }

    @Step("Verify the product item of product `{productTitle}` is displayed")
    public boolean isProductItemResultDisplayedByProductTitle(String productTitle) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, productTitle);
        return isElementDisplayed(NopCommerceBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, productTitle);
    }

    @Step("Verify the item of product `{productTitle}` is undisplayed")
    public boolean isProductItemUndisplayedByProductTitle(String productTitle) {
        return isElementUndisplayed(NopCommerceBasePageUI.DYNAMIC_PRODUCT_TITLE_BY_TITLE, productTitle);
    }


    @Step("Check to {label} checkbox/radio")
    public void checkToDefaultCheckboxRadioByLabel(String label) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_CHECKBOX_RADIO_BY_LABEL, label);
        checkToDefaultCheckboxRadio(NopCommerceBasePageUI.DYNAMIC_CHECKBOX_RADIO_BY_LABEL, label);
    }

    @Step("Uncheck to {label} checkbox")
    public void unCheckToDefaultCheckboxByLabel(String label) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_CHECKBOX_RADIO_BY_LABEL, label);
        unCheckToDefaultCheckbox(NopCommerceBasePageUI.DYNAMIC_CHECKBOX_RADIO_BY_LABEL, label);
    }

    @Step("Hover to {linkText} header link")
    public void hoverDynamicHeaderLinkByText(String linkText) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_HEADER_LINK_BY_TEXT_USER, linkText);
        hoverMouseToElement(NopCommerceBasePageUI.DYNAMIC_HEADER_LINK_BY_TEXT_USER, linkText);
    }

    @Step("Click to {linkText} header link")
    public void clickDynamicHeaderMenuLinkByText(String linkText) {
        waitForElementClickable(NopCommerceBasePageUI.DYNAMIC_HEADER_LINK_BY_TEXT_USER, linkText);
        clickToElement(NopCommerceBasePageUI.DYNAMIC_HEADER_LINK_BY_TEXT_USER, linkText);
    }

    @Step("Verify the {linkText} is active")
    public boolean isActiveLinkDisplayedByText(String linkText) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_ACTIVE_LINK_BY_TEXT, linkText);
        return isElementDisplayed(NopCommerceBasePageUI.DYNAMIC_ACTIVE_LINK_BY_TEXT, linkText);
    }

    @Step("Verify the quantity product item is less or equal {quantity}")
    public boolean isQuantityProductDisplayedByQuantity(int quantity) {
        waitForAllElementInVisibility(NopCommerceBasePageUI.PRODUCT_ITEM_QUANTITY);
        return getElementsSize(NopCommerceBasePageUI.PRODUCT_ITEM_QUANTITY) <= quantity;
    }

    @Step("Click to `WhistList` link at header bar")
    public UserProductWhistListPageObject clickToWhistListLink() {
        waitForElementClickable(NopCommerceBasePageUI.WHIST_LIST_LINK);
        clickToElementByJS(NopCommerceBasePageUI.WHIST_LIST_LINK);
        return PageGeneratorManager.getUserProductWhistListPage(driver);
    }

    @Step("Click to `Shopping Cart` link")
    public UserShoppingCartPageObject clickToShoppingCartLink() {
        waitForElementClickable(NopCommerceBasePageUI.SHOPPING_CART_LINK);
        clickToElementByJS(NopCommerceBasePageUI.SHOPPING_CART_LINK);
        return PageGeneratorManager.getUserShoppingCartPage(driver);
    }

    @Step("Hover to `Shopping Cart` link")
    public void hoverToHeaderShoppingCartLink() {
        waitForElementVisibility(NopCommerceBasePageUI.SHOPPING_CART_LINK);
        hoverMouseToElement(NopCommerceBasePageUI.SHOPPING_CART_LINK);
    }

    @Step("Go to user home page")
    public UserHomePageObject goToUserHomePage() {
        waitForElementClickable(NopCommerceBasePageUI.ICON_USER_HOME_PAGE);
        clickToElement(NopCommerceBasePageUI.ICON_USER_HOME_PAGE);
        return PageGeneratorManager.getUserHomePage(driver);
    }

    @Step("Click to `Add to Compare` button at {productTitle} item")
    public void clickToAddToCompareButtonByProductTitle(String productTitle) {
        scrollToElement(NopCommerceBasePageUI.ADD_TO_COMPARE_BUTTON_BY_PRODUCT_TITLE, productTitle);
        waitForElementClickable(NopCommerceBasePageUI.ADD_TO_COMPARE_BUTTON_BY_PRODUCT_TITLE, productTitle);
        clickToElement(NopCommerceBasePageUI.ADD_TO_COMPARE_BUTTON_BY_PRODUCT_TITLE, productTitle);
    }

    @Step("Get Price of product: `{productTitle}` at Product Item")
    public String getProductPriceByProductTitleAtProductItem(String productTitle) {
        waitForElementVisibility(NopCommerceBasePageUI.DYNAMIC_ACTUAL_PRODUCT_PRICE_BY_PRODUCT_TITLE, productTitle);
        return getElementText(NopCommerceBasePageUI.DYNAMIC_ACTUAL_PRODUCT_PRICE_BY_PRODUCT_TITLE, productTitle);
    }

}
