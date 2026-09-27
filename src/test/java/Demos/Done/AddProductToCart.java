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

public class AddProductToCart {

    @Test
    public void metodaTestAddProductToCart () {

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
        wait.until( ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        WebElement addBackpackButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack")));
        addBackpackButton.click();

        // 5. Verificam badge-ul cosului
         WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));
        Assert.assertEquals( cartBadge.getText(), "1", "Cosul ar trebui sa contina 1 produs." );

        WebElement cart = driver.findElement( By.className("shopping_cart_link") );
        cart.click();

        WebElement productInCart = wait.until(ExpectedConditions.visibilityOfElementLocated( By.cssSelector(".inventory_item_name")));
        Assert.assertEquals( productInCart.getText(), "Sauce Labs Backpack", "Produsul din cos nu este cel asteptat.");


    }
}
