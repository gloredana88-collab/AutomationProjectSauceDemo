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
public class TestDynamicOptions extends TestBasePage {

    @Test
    public void testDynamicOptions(){

        ProductsPage productsPage = new ProductsPage(getDriver());

        productsPage.Products();
        productsPage.openBurgerMenu();
        productsPage.openDynamicCatalog();
        productsPage.assertLazyLoadOption();
        productsPage.selectLazyLoad();
        productsPage.openBurgerMenu();
        productsPage.openDynamicCatalog();
        productsPage.assertSpinnerOption();
        productsPage.selectSpinner();
        productsPage.openBurgerMenu();
        productsPage.openDynamicCatalog();
        productsPage.assertSliderOption();
        productsPage.selectSlider();




    }
}
