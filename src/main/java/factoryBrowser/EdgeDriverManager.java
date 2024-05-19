package factoryBrowser;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;

public class EdgeDriverManager implements BrowserFactory {
    private final String browserVersion;

    public EdgeDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        if (!IS_OS_WINDOWS && !IS_OS_MAC) {
            throw new BrowserNotSupportedException("Edge is not supported on " + GlobalConstants.OS_NAME);
        }
        EdgeOptions options = new EdgeOptions();
        options.addArguments("disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
        options.setBrowserVersion(browserVersion);
        return new EdgeDriver(options);
    }
}
