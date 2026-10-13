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
public class TestLoginCredentialeNull extends TestBasePage {

    @Test
    public void metodaTestUsernameNull() {

        LogInPage logInPage = new LogInPage(getDriver());

        logInPage.clickPassword();
        logInPage.fillPassword();
        logInPage.clickLogin();
        logInPage.assertMesajEroareUserNull();
        logInPage.clearinformation();

    }

    @Test
    public void metodaTestPasswordNull() {

        LogInPage logInPage = new LogInPage(getDriver());

        logInPage.clickUsername();
        logInPage.fillUsername();
        logInPage.clickLogin();
        logInPage.assertMesajEroarePasswordNull();

    }
}
