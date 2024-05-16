package factoryRunConfig;

import commons.GlobalConstants;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class BrowserStackFactory {
    private final String browserName;
    private final String osName;
    private final String osVersion;
    private final String browserVersion;

    public BrowserStackFactory(String browserName, String osName, String osVersion, String browserVersion) {
        this.browserName = browserName;
        this.osName = osName;
        this.osVersion = osVersion;
        this.browserVersion = browserVersion;
    }

    public WebDriver createDriver() {
        WebDriver driver = null;
        MutableCapabilities capabilities = new MutableCapabilities();
        HashMap<String, Object> bstackOptions = new HashMap<String, Object>();
        capabilities.setCapability("browserName", browserName);
        bstackOptions.put("os", osName);
        bstackOptions.put("osVersion", osVersion);
        bstackOptions.put("browserVersion", browserVersion);
        bstackOptions.put("consoleLogs", "info");
        bstackOptions.put("projectName", "Login Nopcommerce");
        bstackOptions.put("sessionName", String.format("Run on %s %s and browser %s %s ", osName, osVersion, browserName, browserVersion));
        capabilities.setCapability("bstack:options", bstackOptions);
        try {
            driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
