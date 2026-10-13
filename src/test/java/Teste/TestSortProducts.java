package Teste;

import Pages.ProductsPage;
import SharedData.TestBasePage;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ChainTestListener.class)
public class TestSortProducts extends TestBasePage {

    @Test
    public void testSort() {

        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.Products();
        productsPage.testProductSorting();
    }
}
