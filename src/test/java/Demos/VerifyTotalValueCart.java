package Demos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class VerifyTotalValueCart {

    @Test
    public void metodaTestValue () {

        ChromeOptions options = new ChromeOptions();

        options.setExperimentalOption("prefs", java.util.Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "profile.password_manager_leak_detection", false
        ));

        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");


        String usernameCredential = ("standard_user");
        String passwordCredential = ("secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.click();
        username.sendKeys(usernameCredential);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passwordCredential);

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        // 3. Verificăm Products
        WebElement productsTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")
                )
        );

        Assert.assertEquals(
                productsTitle.getText(),
                "Products",
                "Nu suntem pe pagina Products."
        );

        // 4. Adăugăm 3 produse
        WebElement backpack = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        );
        backpack.click();

        WebElement bikeLight = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-bike-light")
                )
        );
        bikeLight.click();

        WebElement boltTShirt = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-bolt-t-shirt")
                )
        );
        boltTShirt.click();

        // 5. Verificăm că avem 3 produse în coș
        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        );

        Assert.assertEquals(
                cartBadge.getText(),
                "3",
                "Nu au fost adăugate 3 produse în coș."
        );

        // 6. Intrăm în Cart
        WebElement cart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.className("shopping_cart_link")
                )
        );
        cart.click();

        // 7. Verificăm că avem 3 produse în Cart
        List<WebElement> produseDinCos = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        By.className("cart_item")
                )
        );

        Assert.assertEquals(
                produseDinCos.size(),
                3,
                "În coș nu sunt 3 produse."
        );

        // 8. Calculăm totalul celor 3 produse
        List<WebElement> preturi = driver.findElements(
                By.className("inventory_item_price")
        );

        double totalCalculat = 0;

        for (WebElement pret : preturi) {

            double valoare = Double.parseDouble(
                    pret.getText().replace("$", "")
            );

            totalCalculat += valoare;
        }

        System.out.println("Total calculat pentru 3 produse: $" + totalCalculat);

        // 9. Verificăm subtotalul afișat în Cart
        WebElement subtotalElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(),'Item total:')]")
                )
        );

        String subtotalText = subtotalElement.getText();

        double subtotalActual = Double.parseDouble(
                subtotalText.replace("Item total: $", "")
        );

        System.out.println("Subtotal afișat: $" + subtotalActual);

        Assert.assertEquals(
                subtotalActual,
                totalCalculat,
                0.01,
                "Totalul pentru cele 3 produse nu este corect."
        );

        // 10. Eliminăm Backpack
        WebElement removeBackpack = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("remove-sauce-labs-backpack")
                )
        );
        removeBackpack.click();

        // 11. Verificăm că au rămas 2 produse
        List<WebElement> produseRamase = wait.until(
                ExpectedConditions.numberOfElementsToBeMoreThan(
                        By.className("cart_item"),
                        1
                )
        );

        Assert.assertEquals(
                produseRamase.size(),
                2,
                "După eliminare nu au rămas 2 produse."
        );

        // 12. Recalculăm totalul
        List<WebElement> preturiRamase = driver.findElements(
                By.className("inventory_item_price")
        );

        double totalCalculatDupaEliminare = 0;

        for (WebElement pret : preturiRamase) {

            double valoare = Double.parseDouble(
                    pret.getText().replace("$", "")
            );

            totalCalculatDupaEliminare += valoare;
        }

        System.out.println(
                "Total calculat după eliminare: $"
                        + totalCalculatDupaEliminare
        );

        // 13. Verificăm subtotalul după eliminare
        WebElement subtotalDupaEliminare = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("summary_subtotal_label")
                )
        );

        double subtotalActualDupaEliminare = Double.parseDouble(
                subtotalDupaEliminare.getText()
                        .replace("Item total: $", "")
        );

        System.out.println(
                "Subtotal afișat după eliminare: $"
                        + subtotalActualDupaEliminare
        );

        Assert.assertEquals(
                subtotalActualDupaEliminare,
                totalCalculatDupaEliminare,
                0.01,
                "Totalul după eliminarea produsului nu este corect."
        );

    }
}
