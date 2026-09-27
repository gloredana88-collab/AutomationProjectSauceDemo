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

public class CheckInInfoNull {


    @Test
    public void metodaTestCheckInInfoNull () {

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

        WebElement productsTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        Assert.assertEquals(productsTitle.getText(), "Products");

        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();

        driver.findElement(By.className("shopping_cart_link")).click();

        driver.findElement(By.id("checkout")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Ion");

        driver.findElement(By.id("last-name")).sendKeys("Popescu");

        driver.findElement(By.id("continue")).click();

        String mesajActual = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3[data-test='error']"))).getText();

        System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");

        Assert.assertEquals(mesajActual, "Error: Postal Code is required", "Mesajul de eroare nu este cel așteptat.");
    }
}
