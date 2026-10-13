package Pages;

import Helper.LogInHelpers;
import Helper.ProductsHelpers;
import Logger.LoggerUtility;
import ObjectData.ProductsObjects;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class YourCartPage {
    WebDriver driver;
    LogInHelpers logInHelpers;
    LogInPage logInPage;
    ProductsHelpers productsHelpers;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
        this.productsHelpers = new ProductsHelpers(driver);
        this.logInPage = new LogInPage(driver);
        PageFactory.initElements(driver, this);
    }


    @FindBy(id = "checkout")
        WebElement checkout;

    @FindBy(id = "continue-shopping")
        WebElement continueShopping;


    List<String> produseEliminate;

    public void removeRandomProducts(List<String> produseAdaugate, int numberOfProducts) {
        if (numberOfProducts > produseAdaugate.size()) {
            throw new IllegalArgumentException("Nu poti elimina mai multe produse decat au fost adaugate in cos.");
        }

        List<String> produseDeEliminat = new ArrayList<>(produseAdaugate);
        Collections.shuffle(produseDeEliminat);

        produseEliminate = new ArrayList<>(produseDeEliminat.subList(0, numberOfProducts));

        System.out.println("Produse alese pentru eliminare:");

        for (String produs : produseEliminate) {
            String removeButtonId = ProductsObjects.PRODUCT_REMOVE_BUTTON_IDS.get(produs);
            WebElement removeButton = driver.findElement(By.id(removeButtonId));
            logInHelpers.clickOnElement(removeButton);

            System.out.println("Produs eliminat: " + ProductsObjects.PRODUCT_NAMES.get(produs));
            LoggerUtility.infoTestCase("Remove random products.");
            ChainTestListener.log("Remove random products.");
        }
    }

    public void verifyProductsRemoved() {
        List<WebElement> produseDinCos = driver.findElements(By.cssSelector(".cart_item .inventory_item_name"));
        for (String produs : produseEliminate) {
            String numeProdusEliminat = ProductsObjects.PRODUCT_NAMES.get(produs);
            boolean produsGasit = false;
            for (WebElement produsDinCos : produseDinCos) {
                if (produsDinCos.getText().equals(numeProdusEliminat)) {
                    produsGasit = true;
                    break;
                }
            }

            Assert.assertFalse(produsGasit, "Produsul '" + numeProdusEliminat + "' a fost eliminat, dar inca se afla in cos.");

            System.out.println("Verificare remove OK: " + numeProdusEliminat + " nu mai exista in cos.");
            LoggerUtility.infoTestCase("Assert remove product from cart.");
            ChainTestListener.log("Assert remove product from cart.");
        }
    }

    public void verifyNumberOfProductsAfterRemove(int numberOfProductsBeforeRemove) {
        List<WebElement> produseDinCos = driver.findElements(By.cssSelector(".cart_item .inventory_item_name"));
        int numberOfProductsRemoved = produseEliminate.size();
        int expectedNumberOfProducts = numberOfProductsBeforeRemove - numberOfProductsRemoved;

        Assert.assertEquals(produseDinCos.size(), expectedNumberOfProducts, "Numarul produselor ramase in cos nu este corect.");

        System.out.println("Numarul produselor ramase in cos este corect: " + produseDinCos.size());
        LoggerUtility.infoTestCase("Assert number of products after remove.");
        ChainTestListener.log("Assert number of products after remove.");
    }

    public void selectCheckOut() {
        checkout.click();
        System.out.println("S-a selectat optiunea checkOut");
        LoggerUtility.infoTestCase("Select Checkout option.");
        ChainTestListener.log("Select Checkout option.");
    }

    public void selectContinueShopping() {
        continueShopping.click();
        System.out.println("S-a selectat optiunea continueShopping");
        LoggerUtility.infoTestCase("Select Continue Shopping.");
        ChainTestListener.log("Select Continue Shopping.");
    }

    public void assertContinueShopping() {

        String currentUrl = driver.getCurrentUrl();

        System.out.println("Se verifica pagina dupa Continue Shopping.");
        System.out.println("URL actual: " + currentUrl);

        Assert.assertTrue(currentUrl.contains("inventory.html"), "Nu s-a revenit pe pagina Products dupa Continue Shopping.");

        System.out.println("Assert trecut: suntem pe pagina Products.");
        LoggerUtility.infoTestCase("Assert Continue Shopping.");
        ChainTestListener.log("Assert Continue Shopping.");
    }
}


