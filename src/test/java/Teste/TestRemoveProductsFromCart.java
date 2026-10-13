package Teste;

import Pages.ProductsPage;
import Pages.YourCartPage;
import SharedData.TestBasePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;

@Listeners(ChainTestListener.class)
public class TestRemoveProductsFromCart extends TestBasePage {
    @Test
    public void metodaTestRemoveProductFromCart () {

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
