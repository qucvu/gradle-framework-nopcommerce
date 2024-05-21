package factoryRunConfig;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {
    private final String browserName;
    private final String osName;
    private final String portNumber;

    public GridFactory(String browserName, String osName, String portNumber) {
        this.browserName = browserName;
        this.osName = osName;
        this.portNumber = portNumber;
    }

    public WebDriver createDriver() {
        AbstractDriverOptions options;
        Platform platform;
        WebDriver driver = null;

        if (osName.contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }
        platform = Platform.ANY;

        switch (browserName) {
            case "firefox" -> {
                options = new FirefoxOptions();
                options.setPlatformName(String.valueOf(platform));
            }
            case "chrome" -> {
                options = new ChromeOptions();
                options.setPlatformName(String.valueOf(platform));
            }
            case "edge" -> {
                options = new EdgeOptions();
                options.setPlatformName(String.valueOf(platform));
            }
            default -> throw new RuntimeException("Browser is not valid!");
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://localhost:%s", portNumber)), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;

    }
}
