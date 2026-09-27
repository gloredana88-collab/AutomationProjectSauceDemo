package Teste;

import Pages.ProductsPage;
import SharedData.TestBasePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(ChainTestListener.class)
public class TestAddProductToCart extends TestBasePage {

    @Test
    public void metodaTestAddProductToCart () {

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


        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.Products();
        ChainTestListener.log("The user is on Product page");
//        productsPage.addRandomProducts(2);
        List<String> produseAdaugate = productsPage.addRandomProducts(6);
        productsPage.clickCart();
        productsPage.verifyProductsInCart();
        ChainTestListener.log("The Products in cart are verified");
        ChainTestListener.log("The test is finished");

    }
}
