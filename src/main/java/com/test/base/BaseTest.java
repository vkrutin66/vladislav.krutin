package com.test.base;

import com.test.util.Constants;
import com.test.util.reporter.Reporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITest;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest implements ITest {

    public static WebDriver driver;
    protected String currentAnnotatedMethodName = "";

    @AfterMethod
    public void setNullTestName() {
        currentAnnotatedMethodName = null;
    }

    @Override
    public String getTestName() {
        return currentAnnotatedMethodName;
    }

    @BeforeMethod
    public void setTestName(Method method) {
        currentAnnotatedMethodName = "";
        try {
            String testName = method.getAnnotation(Test.class).testName();
            if (!testName.isEmpty())
                currentAnnotatedMethodName = testName;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void startFirefox() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void setupFirefoxRemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startPhantomJs() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjs.exe";
            System.setProperty("phantomjs.binary.path", execPath);
        } else if (os.contains("mac")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjsmac";
            System.setProperty("phantomjs.binary.path", execPath);
        } else if (os.contains("lin")) {
            String execPath = System.getProperty("lib.dir") + File.separatorChar +
                    "phantomjs";
            System.setProperty("phantomjs.binary.path", execPath);
        }

        driver = new PhantomJSDriver(DesiredCapabilities.phantomjs());
        driver.manage().window().setSize(new Dimension(1440, 900));
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startChrome() {
        String platform = System.getProperty("os.name");

        String driversFolder = Constants.DEFAULT_LIB_DIR + File.separator;
        String pathToDriver = null;
        if (System.getProperty("path.to.driver") != null && !System.getProperty("path.to.driver").isEmpty()) {
            pathToDriver = System.getProperty("path.to.driver");
        } else {
            pathToDriver = (platform.contains("Wind")) ? driversFolder + "chromedriver.exe" : "/usr/local/bin/chromedriver";
        }

        System.setProperty("webdriver.chrome.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }


    protected void setupChromeRemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void closeBrowser() {
        if (driver != null)
            driver.quit();
    }


    @BeforeClass
    public void startBrowser() throws IOException {

        String message = "* Starting test " + this.getClass().toString();
        Reporter.log("\n" + message);
        System.out.println(message);

        String hubUrl = System.getProperty("hub");
        String browser = Constants.DEFAULT_BROWSER;
        String platform = System.getProperty("sun.desktop"); //or  os.name

        if (browser.equalsIgnoreCase("chrome")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupChromeRemoteDriver(hubUrl, platform);
            } else {
                this.startChrome();
            }

        } else if (browser.equalsIgnoreCase("firefox")) {
            if (hubUrl != null && !hubUrl.isEmpty()) {
                this.setupFirefoxRemoteDriver(hubUrl, platform);
            } else {
                this.startFirefox();
            }
        } else if (browser.equalsIgnoreCase("phantom")){
            startPhantomJs();
        }
        Reporter.log("Current window size is: " + driver.manage().window().getSize());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        Reporter.log("Stopping WebDriver");
        closeBrowser();
    }


}
