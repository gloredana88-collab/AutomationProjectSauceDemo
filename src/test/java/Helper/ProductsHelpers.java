package Helper;

import ObjectData.ProductsObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsHelpers {
    WebDriver driver;
    LogInHelpers logInHelpers;

//    public void ProductsHelpers(){
//        this.driver = driver;
//        this.logInHelpers = new LogInHelpers(driver);
//        PageFactory.initElements(driver, this);
//    }


    public ProductsHelpers(WebDriver driver) {
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
    }

    public List<String> getRandomProducts(List<String> products, int numberOfProducts) {

        if (numberOfProducts > products.size()) {
            throw new IllegalArgumentException(
                    "Nu poti selecta mai multe produse decat exista in lista."
            );
        }

        List<String> produseDisponibile = new ArrayList<>(products);

        Collections.shuffle(produseDisponibile);

        return new ArrayList<>(
                produseDisponibile.subList(0, numberOfProducts)
        );
    }

//    public void addProductToCart(String product) {
//        WebElement addButton = driver.findElement(By.xpath("//button[contains(@id, 'add-to-cart-sauce-labs-" + product + "')]"));
//        addButton.click();
//    }

//    public void addProductToCart(String product) {
//
//        WebElement addButton = driver.findElement(
//                By.xpath(
//                        "//button[contains(@id, 'add-to-cart-sauce-labs-" + product + "')]"
//                )
//        );
//
//        logInHelpers.clickOnElement(addButton);
//    }

    public void addProductToCart(String product) {

        String buttonId = ProductsObjects.PRODUCT_BUTTON_IDS.get(product);

        if (buttonId == null) {
            throw new IllegalArgumentException(
                    "Produs necunoscut: " + product
            );
        }

        WebElement addButton = driver.findElement(By.id(buttonId));

        logInHelpers.clickOnElement(addButton);
    }

//    public void removeRandomProductsFromProductsPage(
//            List<String> produseAdaugate,
//            int numberOfProducts
//    ) {
//        if (numberOfProducts > produseAdaugate.size()) {
//            throw new IllegalArgumentException(
//                    "Nu poti elimina mai multe produse decat au fost adaugate in cos."
//            );
//        }
//
//        List<String> produseDeEliminat = new ArrayList<>(produseAdaugate);
//        Collections.shuffle(produseDeEliminat);
//
//        List<String> produseEliminate = new ArrayList<>(
//                produseDeEliminat.subList(0, numberOfProducts)
//        );
//
//        for (String produs : produseEliminate) {
//
//            String removeButtonId =
//                    ProductsObjects.PRODUCT_REMOVE_BUTTON_IDS.get(produs);
//
//            if (removeButtonId == null) {
//                throw new IllegalArgumentException(
//                        "Nu exista Remove button pentru produsul: " + produs
//                );
//            }
//
//            WebElement removeButton =
//                    driver.findElement(By.id(removeButtonId));
//
//            logInHelpers.clickOnElement(removeButton);
//
//            System.out.println(
//                    "Produs eliminat din pagina Products: "
//                            + ProductsObjects.PRODUCT_NAMES.get(produs)
//            );
//        }
//    }

    public List<String> removeRandomProductsFromProductsPage(
            List<String> produseAdaugate,
            int numberOfProducts
    ) {
        if (numberOfProducts > produseAdaugate.size()) {
            throw new IllegalArgumentException(
                    "Nu poti elimina mai multe produse decat au fost adaugate in cos."
            );
        }

        List<String> produseDeEliminat = new ArrayList<>(produseAdaugate);
        Collections.shuffle(produseDeEliminat);

        List<String> produseEliminate = new ArrayList<>(
                produseDeEliminat.subList(0, numberOfProducts)
        );

        for (String produs : produseEliminate) {

            String removeButtonId =
                    ProductsObjects.PRODUCT_REMOVE_BUTTON_IDS.get(produs);

            if (removeButtonId == null) {
                throw new IllegalArgumentException(
                        "Nu exista Remove button pentru produsul: " + produs
                );
            }

            WebElement removeButton =
                    driver.findElement(By.id(removeButtonId));

            logInHelpers.clickOnElement(removeButton);

            System.out.println(
                    "Produs eliminat din pagina Products: "
                            + ProductsObjects.PRODUCT_NAMES.get(produs)
            );
        }

        return produseEliminate;
    }

}
