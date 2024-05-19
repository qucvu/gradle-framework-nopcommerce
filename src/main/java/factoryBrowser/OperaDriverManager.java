package factoryBrowser;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class OperaDriverManager implements BrowserFactory {
    private final String browserVersion;

    public OperaDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        WebDriverManager.chromiumdriver().driverVersion(browserVersion).setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-debugging-port=9222", "disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
        options.setBrowserVersion(browserVersion);
        options.setExperimentalOption("w3c", true);
        options.setBinary(GlobalConstants.OPERA_LAUNCHER_EXE_LOCATION);
        return new ChromeDriver(options);

    }
}
