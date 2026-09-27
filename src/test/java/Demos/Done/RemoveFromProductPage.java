package Demos.Done;

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

public class RemoveFromProductPage {


    @Test
    public void metodaTestRemoveFromProductPage () {

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
        // 3. Verificăm că suntem pe Products
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

        // 4. Adăugăm primul produs - Sauce Labs Backpack
        WebElement backpackButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        );
        backpackButton.click();

        // 5. Adăugăm al doilea produs - Sauce Labs Bike Light
        WebElement bikeLightButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("add-to-cart-sauce-labs-bike-light")
                )
        );
        bikeLightButton.click();

        // 6. Verificăm că avem 2 produse în coș
        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        );

        Assert.assertEquals(
                cartBadge.getText(),
                "2",
                "Nu au fost adăugate 2 produse în coș."
        );

        // 7. Eliminăm primul produs - Backpack
        WebElement removeBackpackButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("remove-sauce-labs-backpack")
                )
        );
        removeBackpackButton.click();

        // 8. Verificăm că a rămas un singur produs în coș
        WebElement cartBadgeAfterRemove = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        );

        Assert.assertEquals(
                cartBadgeAfterRemove.getText(),
                "1",
                "Produsul nu a fost eliminat corect."
        );

        // 9. Verificăm că butonul Backpack a revenit la "Add to cart"
        WebElement addBackpackButtonAgain = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("add-to-cart-sauce-labs-backpack")
                )
        );

        Assert.assertTrue(
                addBackpackButtonAgain.isDisplayed(),
                "Butonul Add to cart pentru Backpack nu a reapărut."
        );
    }
}
