package factoryBrowser;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class SafariDriverManager implements BrowserFactory {
    private final String browserVersion;

    public SafariDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        if (!IS_OS_MAC) {
            throw new BrowserNotSupportedException("Safari is not supported on " + GlobalConstants.OS_NAME);
        }
        SafariOptions options = new SafariOptions();
        options.setBrowserVersion(browserVersion);
        options.setUseTechnologyPreview(true);
        return new SafariDriver(options);
    }
}
