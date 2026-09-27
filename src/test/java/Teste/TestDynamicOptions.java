package Teste;

import Pages.ProductsPage;
import SharedData.TestBasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class TestDynamicOptions extends TestBasePage {

    @Test
    public void testDynamicOptions(){
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
