package factoryRunConfig;

import factoryBrowser.*;
import org.openqa.selenium.WebDriver;

public class LocalFactory {
    private final String browserName;
    private final String browserVersion;

    public LocalFactory(String browserName, String browserVersion) {
        this.browserName = browserName;
        this.browserVersion = browserVersion;
    }

    public WebDriver createDriver() {
        WebDriver driver;
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX -> driver = new FirefoxDriverManager(browserVersion).getBrowserDriver();
            case CHROME -> driver = new ChromeDriverManager(browserVersion).getBrowserDriver();
            case EDGE -> driver = new EdgeDriverManager(browserVersion).getBrowserDriver();
            case OPERA -> driver = new OperaDriverManager(browserVersion).getBrowserDriver();
            case H_CHROME -> driver = new HeadlessChromeDriverManager(browserVersion).getBrowserDriver();
            case H_FIREFOX -> driver = new HeadlessFirefoxDriverManager(browserVersion).getBrowserDriver();
            case SAFARI -> driver = new SafariDriverManager(browserVersion).getBrowserDriver();
            default -> throw new BrowserNotSupportedException(browserName);
        }
        return driver;
    }


}
