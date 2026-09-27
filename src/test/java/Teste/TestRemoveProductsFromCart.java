package Teste;

import Pages.ProductsPage;
import Pages.YourCartPage;
import SharedData.TestBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;

public class TestRemoveProductsFromCart extends TestBasePage {
    @Test
    public void metodaTestRemoveProductFromCart () {

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
        YourCartPage yourCartPage = new YourCartPage(getDriver());
        productsPage.Products();
        List<String> produseAdaugate = productsPage.addRandomProducts(2);
        productsPage.clickCart();
        productsPage.verifyProductsInCart();
        yourCartPage.removeRandomProducts(produseAdaugate, 2);
        yourCartPage.verifyProductsRemoved();
        yourCartPage.verifyNumberOfProductsAfterRemove(2);


    }
}
