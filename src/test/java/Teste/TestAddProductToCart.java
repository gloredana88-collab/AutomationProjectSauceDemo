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

        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.Products();
        List<String> produseAdaugate = productsPage.addRandomProducts(6);
        productsPage.clickCart();
        productsPage.verifyProductsInCart();


    }
}
