package factoryRunConfig;

import commons.BrowserList;
import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

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
            case FIREFOX -> {
                FirefoxOptions options = new FirefoxOptions();
                options.setBrowserVersion(browserVersion);
                driver = new FirefoxDriver(options);
            }

            case CHROME -> {
                ChromeOptions options = new ChromeOptions();
                options.setBrowserVersion(browserVersion);
                System.out.println(options.toString());
                driver = new ChromeDriver(options);
            }

            case EDGE -> {
                EdgeOptions options = new EdgeOptions();
                options.setBrowserVersion(browserVersion);
                driver = new EdgeDriver(options);
            }

            case OPERA -> {
                WebDriverManager.chromiumdriver().driverVersion("123").setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222", "disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
                options.setBrowserVersion(browserVersion);
                options.setExperimentalOption("w3c", true);
                options.setBinary(GlobalConstants.OPERA_LAUNCHER_EXE_LOCATION);
                driver = new ChromeDriver(options);
            }

            default -> throw new RuntimeException("Browser name is not valid");
        }
        return driver;
    }


}
