package Pages;

import Helper.LogInHelpers;
import Helper.ProductsHelpers;
import ObjectData.ProductsObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProductsPage {
    WebDriver driver;
    LogInHelpers logInHelpers;
    LogInPage logInPage;
    ProductsHelpers productsHelpers;

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
        this.logInPage = new LogInPage(driver);
        this.productsHelpers = new ProductsHelpers(driver);
        PageFactory.initElements(driver, this);
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "title")
    WebElement productsTitle;

    @FindBy(id = "react-burger-menu-btn")
    WebElement profileOptionButton;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutButton;

    @FindBy(className = "shopping_cart_badge")
    WebElement cartButtonBadge;

    @FindBy(className = "shopping_cart_link")
    WebElement cartButton;

    @FindBy(id = "dynamic_catalog_sidebar_link")
    WebElement dynamicCatalogLink;

//    @FindBy(id = "product_sort_container")
//    WebElement sortDropdown;

    @FindBy(css = ".product_sort_container")
    WebElement sortDropdown;

    @FindBy(xpath = "//*[normalize-space()='Lazy Load']")
    WebElement lazyLoadOption;

    @FindBy(xpath = "//*[normalize-space()='Spinner']")
    WebElement spinnerOption;

    @FindBy(xpath = "//*[normalize-space()='Slider']")
    WebElement sliderOption;


    public void logOut() {
        profileOptionButton.click();
        logInHelpers.waitVisibility(logoutButton);
        logoutButton.click();
        System.out.println("S-a selectat logout");
        logInPage.prezentaLogin();
    }

    public void Products() {
        logInPage.loginaction();
            System.out.println("Navighez pe pagina Products...");
//        assert productsTitle.isDisplayed();
        Assert.assertTrue(productsTitle.isDisplayed(), "Pagina Products nu este afisata.");
            System.out.println("Pagina Products este afisata cu succes.");
    }

    public void clickCart() {
        logInHelpers.clickOnElement(cartButton);
        System.out.println("S-a selectat optiunea Cart");
    }

    List<String> produseAlese;



//    public void addRandomProducts(int numberOfProducts) {
//
//        produseAlese = productsHelpers.getRandomProducts(ProductsObjects.PRODUCTS, numberOfProducts);
//        System.out.println("Produse selectate aleator:");
//        for (String produs : produseAlese) {
//            System.out.println("- " + ProductsObjects.PRODUCT_NAMES.get(produs));
//            productsHelpers.addProductToCart(produs);
//        }
//        System.out.println("Produsele au fost adaugate in cos.");
//    }

    public List<String> addRandomProducts(int numberOfProducts) {

        produseAlese = productsHelpers.getRandomProducts(ProductsObjects.PRODUCTS, numberOfProducts);
        System.out.println("Produse selectate aleator:");
        for (String produs : produseAlese) {
            System.out.println("- " + ProductsObjects.PRODUCT_NAMES.get(produs));
            productsHelpers.addProductToCart(produs);
        }
        System.out.println("Produsele au fost adaugate in cos.");
        return produseAlese;

    }


    public void verifyProductsInCart() {
            System.out.println("Verific produsele din cos...");

     List<WebElement> produseDinCos = driver.findElements(By.cssSelector(".cart_item .inventory_item_name"));
            System.out.println("Numar produse asteptate: " + produseAlese.size());
            System.out.println("Numar produse gasite in cos: " + produseDinCos.size());

      Assert.assertEquals(produseDinCos.size(), produseAlese.size(), "Numarul produselor din cos nu corespunde cu numarul produselor selectate.");
        for (String produs : produseAlese){
            String numeAsteptat = ProductsObjects.PRODUCT_NAMES.get(produs);
            if (numeAsteptat == null) {
                throw new IllegalArgumentException(
                        "Produs necunoscut: " + produs);
            }

            System.out.println("Verific produsul: " + numeAsteptat);

            boolean produsGasit = false;

            for (WebElement produsDinCos : produseDinCos){
                if (produsDinCos.getText().equals(numeAsteptat)){
                    produsGasit = true;
                    break;
                }
            }

         Assert.assertTrue(produsGasit, "Produsul '" + numeAsteptat + "' NU se afla in cos.");
            System.out.println("Produs verificat cu succes: " + numeAsteptat);
        }

            System.out.println("Verificarea cosului a fost finalizata cu succes.");
    }

//    public void removeRandomProducts(
//            List<String> produseAdaugate,
//            int numberOfProducts
//    ) {
//
//        System.out.println(
//                "Se elimina " + numberOfProducts
//                        + " produs(e) din pagina Products."
//        );
//
//        productsHelpers.removeRandomProductsFromProductsPage(
//                produseAdaugate,
//                numberOfProducts
//        );
//
//        System.out.println("Produsele au fost eliminate.");
//    }

    List<String> produseEliminate;

    public void removeRandomProducts(List<String> produseAdaugate, int numberOfProducts) {
        produseEliminate = productsHelpers.removeRandomProductsFromProductsPage(produseAdaugate, numberOfProducts);

        System.out.println("Produse selectate pentru eliminare:");

        for (String produs : produseEliminate) {
            System.out.println("- " + ProductsObjects.PRODUCT_NAMES.get(produs));
        }
    }

    public void assertProductsRemovedFromProductsPage() {
        System.out.println("Se verifica produsele eliminate din pagina Products.");
        for (String produs : produseEliminate) {
            String addButtonId = ProductsObjects.PRODUCT_BUTTON_IDS.get(produs);

            WebElement addButton = driver.findElement(By.id(addButtonId));

            System.out.println("Se verifica produsul: " + ProductsObjects.PRODUCT_NAMES.get(produs));

            Assert.assertTrue(addButton.isDisplayed(), "Produsul '" + ProductsObjects.PRODUCT_NAMES.get(produs) + "' nu are butonul Add to cart dupa eliminare.");

            System.out.println("Assert trecut: " + ProductsObjects.PRODUCT_NAMES.get(produs) + " are din nou butonul Add to cart.");
        }
    }

    public void testProductSorting() {

        Select sort = new Select(sortDropdown);

        // Name (A to Z)
        System.out.println("Se verifica sortarea: Name (A to Z)");
        sort.selectByValue("az");

        List<WebElement> productNames = driver.findElements(By.cssSelector(".inventory_item_name"));

        List<String> actualNames = new ArrayList<>();
        for (WebElement product : productNames) {
            actualNames.add(product.getText());
        }

        List<String> expectedNames = new ArrayList<>(actualNames);
        Collections.sort(expectedNames);

        System.out.println("Lista actuala: " + actualNames);
        System.out.println("Lista asteptata: " + expectedNames);

        Assert.assertEquals(actualNames, expectedNames, "Produsele nu sunt sortate corect Name (A to Z).");
        System.out.println("Assert trecut: Name (A to Z).");


        // Name (Z to A)
        System.out.println("Se verifica sortarea: Name (Z to A)");
        sort.selectByValue("za");

        productNames = driver.findElements(By.cssSelector(".inventory_item_name"));

        actualNames = new ArrayList<>();

        for (WebElement product : productNames) {
            actualNames.add(product.getText());
        }

        expectedNames = new ArrayList<>(actualNames);

        expectedNames.sort(Collections.reverseOrder());

        System.out.println("Lista actuala: " + actualNames);
        System.out.println("Lista asteptata: " + expectedNames);

        Assert.assertEquals(actualNames, expectedNames, "Produsele nu sunt sortate corect Name (Z to A).");

        System.out.println("Assert trecut: Name (Z to A).");


        // Price (low to high)
        System.out.println("Se verifica sortarea: Price (low to high)");

        sort.selectByValue("lohi");

        List<WebElement> productPrices = driver.findElements(By.cssSelector(".inventory_item_price"));

        List<Double> actualPrices = new ArrayList<>();

        for (WebElement product : productPrices) {
            actualPrices.add(Double.parseDouble(product.getText().replace("$", "")));
        }

        List<Double> expectedPrices = new ArrayList<>(actualPrices);

        Collections.sort(expectedPrices);

        System.out.println("Preturi actuale: " + actualPrices);
        System.out.println("Preturi asteptate: " + expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Produsele nu sunt sortate corect Price (low to high).");

        System.out.println("Assert trecut: Price (low to high).");


        // Price (high to low)
        System.out.println("Se verifica sortarea: Price (high to low)");

        sort.selectByValue("hilo");

        productPrices = driver.findElements(By.cssSelector(".inventory_item_price"));

        actualPrices = new ArrayList<>();

        for (WebElement product : productPrices) {actualPrices.add(Double.parseDouble(product.getText().replace("$", "")));
        }

        expectedPrices = new ArrayList<>(actualPrices);

        expectedPrices.sort(Collections.reverseOrder());

        System.out.println("Preturi actuale: " + actualPrices);
        System.out.println("Preturi asteptate: " + expectedPrices);

        Assert.assertEquals(actualPrices, expectedPrices, "Produsele nu sunt sortate corect Price (high to low).");

        System.out.println("Assert trecut: Price (high to low).");
    }

//    public void clickDynamicCatalog() {
//        System.out.println("Se apasa pe Dynamic Catalog.");
//
//        logInHelpers.clickOnElement(dynamicCatalogLink);
//
//        System.out.println("S-a accesat Dynamic Catalog.");
//    }
//
//    public void assertDynamicCatalog() {
//
//        String currentUrl = driver.getCurrentUrl();
//
//        System.out.println("Se verifica pagina Dynamic Catalog.");
//        System.out.println("URL actual: " + currentUrl);
//
//        Assert.assertTrue(currentUrl.contains("inventory.html"), "Nu s-a accesat pagina Dynamic Catalog.");
//
//        System.out.println("Assert trecut: Dynamic Catalog a fost accesat.");
//    }

    public void openBurgerMenu() {
        System.out.println("Se deschide meniul Burger.");
        logInHelpers.clickOnElement(profileOptionButton);
    }

    public void openDynamicCatalog() {
        System.out.println("Se selecteaza Dynamic Catalog.");
        logInHelpers.clickOnElement(dynamicCatalogLink);
    }

    public void assertLazyLoadOption() {
        System.out.println("Se verifica optiunea Lazy Load.");

        Assert.assertTrue(lazyLoadOption.isDisplayed(), "Optiunea Lazy Load nu este afisata.");

        System.out.println("Assert trecut: Lazy Load este afisat.");
    }

    public void selectLazyLoad() {System.out.println("Se selecteaza Lazy Load.");logInHelpers.clickOnElement(lazyLoadOption);}

    public void assertSpinnerOption() {
        System.out.println("Se verifica optiunea Spinner.");

        Assert.assertTrue(spinnerOption.isDisplayed(), "Optiunea Spinner nu este afisata.");

        System.out.println("Assert trecut: Spinner este afisat.");
    }

    public void selectSpinner() {
        System.out.println("Se selecteaza Spinner.");
        logInHelpers.clickOnElement(spinnerOption);
    }

    public void assertSliderOption() {
        System.out.println("Se verifica optiunea Slider.");

        Assert.assertTrue(sliderOption.isDisplayed(), "Optiunea Slider nu este afisata.");

        System.out.println("Assert trecut: Slider este afisat.");
    }

    public void selectSlider() {
        System.out.println("Se selecteaza Slider.");
        logInHelpers.clickOnElement(sliderOption);
    }

}


