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

public class CompleteOrder {

    @Test
    public void metodaTestCompleteOrder () {

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

        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        Assert.assertEquals(productsTitle.getText(), "Products");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

        driver.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();

        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        Assert.assertEquals(cartBadge.getText(), "3", "În coș nu sunt 3 produse.");

        driver.findElement(By.className("shopping_cart_link")).click();

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 3, "În coș nu sunt 3 produse.");

        WebElement produsDeEliminat = driver.findElement(By.xpath("//div[contains(@class,'cart_item')]" + "[.//div[@class='inventory_item_name' and " + "normalize-space()='Sauce Labs Bolt T-Shirt']]"));
        produsDeEliminat.findElement(By.tagName("button")).click();

        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "După eliminare nu au rămas 2 produse.");

        Assert.assertEquals(driver.findElement(By.className("shopping_cart_badge")).getText(), "2", "Badge-ul coșului nu este 2.");

        driver.findElement(By.id("checkout")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Ion");

        driver.findElement(By.id("last-name")).sendKeys("Popescu");

        driver.findElement(By.id("postal-code")).sendKeys("010101");

        driver.findElement(By.id("continue")).click();

        WebElement overviewTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));
        Assert.assertEquals(overviewTitle.getText(), "Checkout: Overview");
        Assert.assertEquals(driver.findElements(By.className("cart_item")).size(), 2, "La checkout nu au ajuns cele 2 produse.");

        driver.findElement(By.id("finish")).click();

        WebElement mesajFinal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        Assert.assertEquals(mesajFinal.getText(), "Thank you for your order!", "Comanda nu a fost finalizată corect.");
        System.out.println("Comanda a fost finalizată cu succes!");

//        WebElement backHomeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("back-to-products")));
//        backHomeButton.click(); // return to main page

        WebElement profileOptionsButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-burger-menu-btn")));
        profileOptionsButton.click();

        WebElement logOutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        logOutButton.click();


    }
}
