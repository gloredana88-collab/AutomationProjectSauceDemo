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
import java.util.List;

public class RemoveProductsFromCart {
    @Test
    public void metodaTestRemoveProductFromCart () {

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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("title")));

        List<WebElement> butoaneAddToCart = driver.findElements( By.xpath("//button[contains(@id,'add-to-cart')]"));
        System.out.println( "Numar produse gasite: " + butoaneAddToCart.size() );

        Assert.assertEquals( butoaneAddToCart.size(), 6, "Numarul produselor gasite nu este 6." );
        for (WebElement buton : butoaneAddToCart) {
            buton.click();
        }

        WebElement cartBadge = wait.until( ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_badge")));

        String numarProduseCos = cartBadge.getText();

        System.out.println( "Produse in cos: " + numarProduseCos );

        Assert.assertEquals( numarProduseCos, "6", "Cosul nu contine toate cele 6 produse." );

        WebElement cart = driver.findElement( By.className("shopping_cart_link") ); cart.click();
        wait.until( ExpectedConditions.visibilityOfElementLocated( By.className("title") ) );

        List<WebElement> produseDinCos = driver.findElements( By.className("cart_item") );
        System.out.println( "Produse initiale in cos: " + produseDinCos.size() );
        Assert.assertEquals( produseDinCos.size(), 6, "Cosul nu contine 6 produse." );

        List<WebElement> butoaneRemove = driver.findElements( By.xpath("//button[contains(@id,'remove')]") );
        System.out.println( "Butoane Remove gasite: " + butoaneRemove.size() );
        Assert.assertEquals( butoaneRemove.size(), 6, "Nu exista cate un buton Remove pentru fiecare produs." );

        butoaneRemove.get(0).click();
        butoaneRemove.get(1).click();

        List<WebElement> produseRamase = driver.findElements( By.className("cart_item") );
        System.out.println( "Produse ramase in cos: " + produseRamase.size() );
        Assert.assertEquals( produseRamase.size(), 4, "Dupa eliminarea a 2 produse ar trebui sa ramana 4." );

    }
}
