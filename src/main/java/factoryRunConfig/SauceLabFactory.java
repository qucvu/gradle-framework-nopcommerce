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
import java.util.Map;

public class SauceLabFactory {
    private final String browserName;
    private final String osName;
    private final String osVersion;
    private final String browserVersion;

    public SauceLabFactory(String browserName, String osName, String osVersion, String browserVersion) {
        this.browserName = browserName;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserVersion = browserVersion;
    }

    public WebDriver createDriver() {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        WebDriver driver = null;
        AbstractDriverOptions options;
        switch (browser) {
            case CHROME -> options = new ChromeOptions();
            case FIREFOX -> options = new FirefoxOptions();
            case EDGE -> options = new EdgeOptions();
            default -> throw new RuntimeException("The browser name is invalid");
        }
        options.setPlatformName(String.format("%s %s", osName, osVersion));
        options.setBrowserVersion(browserVersion);
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", "oauth-nguyenquocvu696-ae4f1");
        sauceOptions.put("accessKey", "c7dd4040-171c-46a8-820e-82a2ec2a40b7");
        sauceOptions.put("screenResolution", "1400x1050");
        sauceOptions.put("build", "selenium-build-VHYQS");
        sauceOptions.put("name", String.format("Run on %s - Nopcommerce project", browserName));
        options.setCapability("sauce:options", sauceOptions);
        try {
            driver = new RemoteWebDriver(new URL("https://ondemand.us-west-1.saucelabs.com:443/wd/hub"), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
