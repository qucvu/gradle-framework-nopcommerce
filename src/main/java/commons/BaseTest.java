package commons;

import factoryRunConfig.*;
import io.qameta.allure.Step;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Random;


public class BaseTest {
    private WebDriver driver;
    protected static Environment environment;
    private static String runConfig;
    private static String osName;
    private static String osVersion;
    private static String portNumber;


    public WebDriver getDriver() {
        return driver;
    }

    @Parameters({"runConfig", "osName", "osVersion", "hubPort"})
    @BeforeSuite
    public void beforeSuite(String runConfig, String osName, String osVersion, String hubPort) {
        String environmentName = System.getProperty("env");
        ConfigFactory.setProperty("env", environmentName);
        BaseTest.runConfig = runConfig;
        BaseTest.osName = osName;
        BaseTest.osVersion = osVersion;
        BaseTest.portNumber = hubPort;
        BaseTest.environment = ConfigFactory.create(Environment.class);
        deleteAllFileInFolder("allure-results");
    }


    protected WebDriver getBrowserDriver(String browserName, String browserVersion, String appUrl) {
        RunConfigList runConfig = RunConfigList.valueOf(BaseTest.runConfig.toUpperCase());
        switch (runConfig) {
            case LOCAL -> driver = new LocalFactory(browserName, browserVersion).createDriver();
            case GRID -> driver = new GridFactory(browserName, osName, portNumber).createDriver();
            case SAUCE_LABS ->
                    driver = new SauceLabFactory(browserName, osName, osVersion, browserVersion).createDriver();
            case BROWSER_STACK ->
                    driver = new BrowserStackFactory(browserName, osName, osVersion, browserVersion).createDriver();
            case LAMBDA_TEST ->
                    driver = new LambdaTestFactory(browserName, osName, osVersion, browserVersion).createDriver();
            default -> throw new RuntimeException("The run environment config is invalid");
        }
        driver.get(appUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstants.LONG_TIMEOUT));
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1920, 1080));

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

    protected void sleepInSecond(long second) {
        try {
            Thread.sleep(second * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
