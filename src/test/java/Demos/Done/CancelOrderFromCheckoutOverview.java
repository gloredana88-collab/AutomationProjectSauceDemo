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

public class CancelOrderFromCheckoutOverview {

    @Test
    public void metodaTestCancelFromOverview () {

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
        String passwordCredential= ("secret_sauce");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.click();
        username.sendKeys(usernameCredential);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passwordCredential);

        WebElement submitButton = driver.findElement(By.id("login-button"));
        submitButton.click();

        // 2. Verificăm că suntem pe Products
        WebElement productsTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")
                )
        );

        Assert.assertEquals(
                productsTitle.getText(),
                "Products",
                "Nu am ajuns pe pagina Products."
        );


        // 3. Adăugăm un produs
        driver.findElement(
                By.id("add-to-cart-sauce-labs-backpack")
        ).click();


        // 4. Verificăm că avem 1 produs în coș
        WebElement cartBadge = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("shopping_cart_badge")
                )
        );

        Assert.assertEquals(
                cartBadge.getText(),
                "1",
                "În coș nu există un singur produs."
        );


        // 5. Intrăm în Cart
        driver.findElement(
                By.className("shopping_cart_link")
        ).click();


        // 6. Verificăm că avem 1 produs în Cart
        Assert.assertEquals(
                driver.findElements(By.className("cart_item")).size(),
                1,
                "Produsul nu există în Cart."
        );


        // 7. Click Checkout
        WebElement checkoutButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("checkout")
                )
        );

        checkoutButton.click();


        // 8. Verificăm pagina Checkout: Your Information
        WebElement checkoutTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")
                )
        );

        Assert.assertEquals(
                checkoutTitle.getText(),
                "Checkout: Your Information",
                "Nu am ajuns pe pagina Checkout: Your Information."
        );


        // 9. Completăm First Name
        driver.findElement(By.id("first-name"))
                .sendKeys("Ion");


        // 10. Completăm Last Name
        driver.findElement(By.id("last-name"))
                .sendKeys("Popescu");


        // 11. Completăm Postal Code
        driver.findElement(By.id("postal-code"))
                .sendKeys("010101");


        // 12. Click Continue
        driver.findElement(By.id("continue"))
                .click();


        // 13. Verificăm că am ajuns în Checkout: Overview
        WebElement overviewTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")
                )
        );

        Assert.assertEquals(
                overviewTitle.getText(),
                "Checkout: Overview",
                "Nu am ajuns pe pagina Checkout: Overview."
        );


// 14. Verificăm că produsul este prezent în Overview
        Assert.assertEquals(
                driver.findElements(By.className("inventory_item_name")).size(),
                1,
                "Produsul nu apare în Checkout: Overview."
        );


// 15. Click Cancel din Checkout: Overview
        WebElement cancelButton = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("cancel")
                )
        );

        cancelButton.click();


// 16. Așteptăm să revenim în Products
        wait.until(
                ExpectedConditions.urlContains("/inventory.html")
        );


// 17. Verificăm că suntem în Products
        Assert.assertTrue(
                driver.getCurrentUrl().contains("/inventory.html"),
                "Nu ne-am întors în pagina Products."
        );


// 18. Verificăm titlul paginii
        WebElement productsTitleAfterCancel = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("title")
                )
        );

        Assert.assertEquals(
                productsTitleAfterCancel.getText(),
                "Products",
                "Nu suntem pe pagina Products după Cancel."
        );


        System.out.println(
                "Testul Cancel din Checkout: Overview a trecut cu succes!"
        );

    }
}
