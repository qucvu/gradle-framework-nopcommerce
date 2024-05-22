package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverManager implements BrowserFactory {
    private final String browserVersion;

    public EdgeDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
        options.setBrowserVersion(browserVersion);
        return new EdgeDriver(options);
    }
}
