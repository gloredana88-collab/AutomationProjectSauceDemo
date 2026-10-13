package Teste;

import Pages.LogInPage;
import SharedData.TestBasePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class TestLoginCredentialeInvalide extends TestBasePage {

    @Test
    public void metodaTestUserInvalid () {


        LogInPage logInPage = new LogInPage(getDriver());

        //incorect username
        logInPage.clickUsername();
        logInPage.fillUsernameInvalid();
        logInPage.clickPassword();
        logInPage.fillPassword();
        logInPage.clickLogin();
        logInPage.assertMesajEroareLogin();
        logInPage.clearinformation();

        //incorect password
        logInPage.clickUsername();
        logInPage.fillUsername();
        logInPage.clickPassword();
        logInPage.fillPasswordInvalid();
        logInPage.clickLogin();
        logInPage.assertMesajEroareLogin();

    }
}
