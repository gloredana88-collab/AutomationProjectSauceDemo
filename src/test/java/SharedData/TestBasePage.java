package SharedData;

import Logger.LoggerUtility;
import SharedData.Browser.BrowserFactory;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class TestBasePage {
    public WebDriver driver;
    public String testName;

    @BeforeMethod
    public void initialiseBrowser() {
        ChromeOptions options = new ChromeOptions();

                    options.setExperimentalOption("prefs", java.util.Map.of(
                    "credentials_enable_service", false,
                    "profile.password_manager_enabled", false,
                    "profile.password_manager_leak_detection", false
                    ));

//        this.driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
        testName = this.getClass().getSimpleName();
        LoggerUtility.startTestCase(testName);
        driver = new BrowserFactory().getBrowserFactory();
        LoggerUtility.infoTestCase("==== The browser started succesfully");
        driver.get("https://www.saucedemo.com/");



    }

    @AfterMethod
    public void clearBrowser(ITestResult result) {
        driver.quit();
//        LoggerUtility.infoTestCase("==== The browser closed succesfully");
//        LoggerUtility.endTestCase(testName);

            LoggerUtility.infoTestCase("==== The browser is closing ====");

            if (result.getStatus() == ITestResult.FAILURE) {
                LoggerUtility.errorLog(result.getThrowable().getMessage());
            }

            LoggerUtility.infoTestCase("==== The browser closed successfully ====");
            LoggerUtility.endTestCase(testName);
        }

//    @AfterMethod
//    public void clearBrowser() {
////        driver.quit();
////        LoggerUtility.infoTestCase("==== The browser closed succesfully");
////        LoggerUtility.endTestCase(testName);
//
//        LoggerUtility.infoTestCase("==== The browser is closing ====");
//
//        if (driver != null) {
//            driver.quit();
//        }
//
//        LoggerUtility.infoTestCase("==== The browser closed successfully ====");
//
//        LoggerUtility.endTestCase(testName);
//    }


//    @AfterSuite
//    public void finishLogFiles(){
//        LoggerUtility.mergeFiles();
//    }

    @AfterSuite
    public void finishLogFiles() {

        LogManager.shutdown();
        LoggerUtility.mergeFiles();
    }

    public WebDriver getDriver() {
        return driver;
    }


}
