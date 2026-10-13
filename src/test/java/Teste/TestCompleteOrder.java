package Teste;

import ObjectData.CheckOutObjects;
import Pages.CheckOutPage;
import Pages.OverviewPage;
import Pages.ProductsPage;
import Pages.YourCartPage;
import SharedData.TestBasePage;
import XmlReader.XmlDataLoader;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Listeners(ChainTestListener.class)
public class TestCompleteOrder extends TestBasePage {
    private Map<String, CheckOutObjects> checkOutObjectsMap;

    @Test
    public void metodaTestCompleteOrder () throws SQLException {


          checkOutObjectsMap = XmlDataLoader.loadData("src/test/resources/CheckOutData.xml", CheckOutObjects.class);
          CheckOutObjects data = checkOutObjectsMap.get("dataSet_1");


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
        overviewPage.finishOrder();
        overviewPage.assertCompleteOrder();


    }
}
