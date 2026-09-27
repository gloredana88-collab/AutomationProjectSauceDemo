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

public class DynamicOptions {

    @Test
    public void metodaTestDynamicOptions () {

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

        // 4. Deschidem meniul Burger
        WebElement burgerMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("react-burger-menu-btn")
                )
        );
        burgerMenu.click();

        // 5. Verificăm că meniul este deschis
        WebElement menuContainer = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("bm-menu")
                )
        );

        Assert.assertTrue(
                menuContainer.isDisplayed(),
                "Meniul Burger nu este afișat."
        );

        // 6. Selectăm Dynamic Catalog
        WebElement dynamicCatalog = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Dynamic Catalog']")
                )
        );

        dynamicCatalog.click();

        // 7. Verificăm Lazy Load
        WebElement lazyLoad = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[normalize-space()='Lazy Load']")
                )
        );

        Assert.assertTrue(
                lazyLoad.isDisplayed(),
                "Opțiunea Lazy Load nu este afișată."
        );

        lazyLoad.click();

        // 8. Deschidem din nou meniul Burger
        burgerMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("react-burger-menu-btn")
                )
        );
        burgerMenu.click();

        // 9. Selectăm din nou Dynamic Catalog
        dynamicCatalog = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Dynamic Catalog']")
                )
        );
        dynamicCatalog.click();

        // 10. Verificăm Spinner
        WebElement spinner = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[normalize-space()='Spinner']")
                )
        );

        Assert.assertTrue(
                spinner.isDisplayed(),
                "Opțiunea Spinner nu este afișată."
        );

        spinner.click();

        // 11. Deschidem din nou meniul Burger
        burgerMenu = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("react-burger-menu-btn")
                )
        );
        burgerMenu.click();

        // 12. Selectăm din nou Dynamic Catalog
        dynamicCatalog = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[normalize-space()='Dynamic Catalog']")
                )
        );
        dynamicCatalog.click();

        // 13. Verificăm Slider
        WebElement slider = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[normalize-space()='Slider']")
                )
        );

        Assert.assertTrue(
                slider.isDisplayed(),
                "Opțiunea Slider nu este afișată."
        );

        slider.click();
    }


}
