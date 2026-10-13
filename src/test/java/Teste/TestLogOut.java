package Teste;

import Pages.LogInPage;
import Pages.ProductsPage;
import SharedData.TestBasePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class TestLogOut extends TestBasePage {
    @Test
    public void metodaTestLogOut() {

        LogInPage logInPage = new LogInPage(getDriver());
        ProductsPage productsPage = new ProductsPage(getDriver());

        logInPage.clickUsername();
        logInPage.fillUsername();
        logInPage.clickPassword();
        logInPage.fillPassword();
        logInPage.clickLogin();
        productsPage.logOut();


    }

}
