package Teste;

import ObjectData.CheckOutObjects;
import Pages.CheckOutPage;
import Pages.OverviewPage;
import Pages.ProductsPage;
import Pages.YourCartPage;
import SharedData.TestBasePage;
import XmlReader.XmlDataLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class TestCheckInInfoNull extends TestBasePage {
    private Map<String, CheckOutObjects> checkOutObjectsMap;

    @Test
    public void metodaTestCheckInInfoNull () {

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

        checkOutObjectsMap = XmlDataLoader.loadData("src/test/resources/CheckOutData.xml", CheckOutObjects.class);
        CheckOutObjects data = checkOutObjectsMap.get("dataSet_4");

        ProductsPage productsPage = new ProductsPage(getDriver());
        YourCartPage yourCartPage = new YourCartPage(getDriver());
        CheckOutPage checkOutPage = new CheckOutPage(getDriver());
        OverviewPage overviewPage = new OverviewPage(getDriver());

        productsPage.Products();
        List<String> produseAdaugate = productsPage.addRandomProducts(3);
        productsPage.clickCart();
        productsPage.verifyProductsInCart();
        yourCartPage.removeRandomProducts(produseAdaugate, 1);
        yourCartPage.verifyProductsRemoved();
        yourCartPage.verifyNumberOfProductsAfterRemove(3);
        yourCartPage.selectCheckOut();
        checkOutPage.entryCheckOut(data);
        checkOutPage.continueCheckout();
        checkOutPage.assertDateInvalideCheckout(data);

    }
}
