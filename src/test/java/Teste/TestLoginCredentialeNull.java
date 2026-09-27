package Teste;

import Pages.LogInPage;
import SharedData.TestBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TestLoginCredentialeNull extends TestBasePage {

    @Test
    public void metodaTestUsernameNull() {

//        ChromeOptions options = new ChromeOptions();
//
//        options.setExperimentalOption("prefs", java.util.Map.of(
//                "credentials_enable_service", false,
//                "profile.password_manager_enabled", false,
//                "profile.password_manager_leak_detection", false
//        ));
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.get("https://www.saucedemo.com/");


        LogInPage logInPage = new LogInPage(getDriver());
        logInPage.clickPassword();
        logInPage.fillPassword();
        logInPage.clickLogin();
        logInPage.assertMesajEroareUserNull();
        logInPage.clearinformation();


    }

    @Test
    public void metodaTestPasswordNull() {

//        ChromeOptions options = new ChromeOptions();
//
//        options.setExperimentalOption("prefs", java.util.Map.of(
//                "credentials_enable_service", false,
//                "profile.password_manager_enabled", false,
//                "profile.password_manager_leak_detection", false
//        ));
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().window().maximize();
//        driver.get("https://www.saucedemo.com/");

        LogInPage logInPage = new LogInPage(getDriver());
        logInPage.clickUsername();
        logInPage.fillUsername();
        logInPage.clickLogin();
        logInPage.assertMesajEroarePasswordNull();

    }
}
