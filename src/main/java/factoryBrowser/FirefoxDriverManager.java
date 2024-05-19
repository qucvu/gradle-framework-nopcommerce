package factoryBrowser;

import commons.GlobalConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager implements BrowserFactory {
    private final String browserVersion;

    public FirefoxDriverManager(String browserVersion) {
        this.browserVersion = browserVersion;
    }

    @Override
    public WebDriver getBrowserDriver() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBrowserVersion(browserVersion);
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", GlobalConstants.DOWNLOAD_FILE_FOLDER);
        options.addPreference("browser.download.useDownloadDir", true);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip");
        options.addPreference("pdfjs.disabled", true);
        return new FirefoxDriver(options);
    }
}
