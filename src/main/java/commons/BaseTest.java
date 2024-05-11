package commons;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;


public class BaseTest {
    private WebDriver driver;
    protected static Environment environment;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeSuite
    public void beforeSuite() {
        String environmentName = System.getProperty("env");
        ConfigFactory.setProperty("env", environmentName);
        environment = ConfigFactory.create(Environment.class);
        deleteAllFileInFolder("allure-results");
    }


    protected WebDriver getBrowserDriver(String browserName) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX -> driver = new FirefoxDriver();

            case CHROME -> driver = new ChromeDriver();

            case EDGE -> driver = new EdgeDriver();

            case OPERA -> {
                WebDriverManager.chromiumdriver().driverVersion("123").setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222", "disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
                options.setExperimentalOption("w3c", true);
                options.setBinary(GlobalConstants.OPERA_LAUNCHER_EXE_LOCATION);
                driver = new ChromeDriver(options);
            }
            default -> throw new RuntimeException("Browser name is not valid");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));

        return driver;
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl) {
        BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
        switch (browser) {
            case FIREFOX -> driver = new FirefoxDriver();

            case CHROME -> driver = new ChromeDriver();

            case EDGE -> driver = new EdgeDriver();

            case OPERA -> {
                WebDriverManager.chromiumdriver().driverVersion("123").setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--remote-debugging-port=9222", "disable-infobars", "--no-sandbox", "--disable-dev-shm-usage");
                options.setExperimentalOption("w3c", true);
                options.setBinary(GlobalConstants.OPERA_LAUNCHER_EXE_LOCATION);
                driver = new ChromeDriver(options);
            }

            default -> throw new RuntimeException("Browser name is not valid");
        }
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));

        return driver;
    }

    protected void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected WebDriver getBrowserDriver(String browserName, String appUrl, String osName, String portNumber) {
        AbstractDriverOptions options;
        Platform platform;

        if (osName.contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }

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

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().maximize();
        driver.get(appUrl);


        return driver;
    }

    protected int generateRandomNumber() {
        Random random = new Random(System.currentTimeMillis());
        return random.nextInt();
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH + File.separator + folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            assert listOfFiles != null;
            if (listOfFiles.length != 0) {
                for (File listOfFile : listOfFiles) {
                    if (listOfFile.isFile() && !listOfFile.getName().equals("environment.properties")) {
                        new File(listOfFile.toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Step("Close browser driver")
    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstants.OS_NAME;
            String driverInstanceName = driver.toString().toLowerCase();
            String browserDriverName;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("internetexplorer")) {
                browserDriverName = "IEDriverServer";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "msedgedriver";
            } else if (driverInstanceName.contains("opera")) {
                browserDriverName = "operadriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                // cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
                cmd = "taskkill /f /im " + browserDriverName + ".exe /T";

            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
        } catch (Throwable e) {
            pass = false;

            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    @Step("Verify the expected data is: '{expected}'")
    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
        } catch (Throwable e) {
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    public String getDayFromDate(Date date) {
        return new SimpleDateFormat("d").format(date);
    }

    public String getMonthFromDate(Date date) {
        return new SimpleDateFormat("MMMM").format(date);
    }

    public String getYearFromDate(Date date) {
        return new SimpleDateFormat("yyyy").format(date);
    }

}
