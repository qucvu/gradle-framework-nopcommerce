package factoryRunConfig;

import commons.BrowserList;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class LambdaTestFactory {

    private final String browserName;
    private final String osName;
    private final String osVersion;
    private final String browserVersion;

    public LambdaTestFactory(String browserName, String osName, String osVersion, String browserVersion) {
        this.browserName = browserName;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserVersion = browserVersion;
    }

    public WebDriver createDriver() {
        WebDriver driver = null;
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        AbstractDriverOptions options;
        switch (browser) {
            case CHROME -> options = new ChromeOptions();
            case FIREFOX -> options = new FirefoxOptions();
            case EDGE -> options = new EdgeOptions();
            default -> throw new RuntimeException("The browser name is invalid");
        }
        options.setPlatformName(String.format("%s %s", osName, osVersion));
        options.setBrowserVersion(browserVersion);
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "nguyenquocvu696");
        ltOptions.put("accessKey", "r5X45Uagk4SwEW5XNMfznSci9GX5fmcWEIbAOSQuvn8J4XUBHr");
        ltOptions.put("name", "Run on " + browserName);
        ltOptions.put("resolution", "1280x800");
        ltOptions.put("project", "Gradle framework nopcommerce");
        ltOptions.put("w3c", true);
        options.setCapability("LT:Options", ltOptions);
        try {
            driver = new RemoteWebDriver(new URL("https://nguyenquocvu696:r5X45Uagk4SwEW5XNMfznSci9GX5fmcWEIbAOSQuvn8J4XUBHr@hub.lambdatest.com/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;

    }
}
