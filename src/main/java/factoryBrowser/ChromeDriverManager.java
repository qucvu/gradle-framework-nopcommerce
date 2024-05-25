package factoryBrowser;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class ChromeDriverManager implements BrowserFactory {
    private final String browserVersion;

    public ChromeDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBrowserVersion(browserVersion);
//        options.addArguments("--incognito");
//        options.addArguments("--disable-infobars");
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", GlobalConstants.DOWNLOAD_FILE_FOLDER);
        options.setExperimentalOption("prefs", chromePrefs);
        return new ChromeDriver(options);
    }

}
