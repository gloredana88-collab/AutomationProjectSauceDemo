package Demos.Done;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SortFromProduct {


    @Test
    public void metodaTestSortFromProduct() {

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

        // 4. Găsim dropdown-ul de sortare
        WebElement sortDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("product_sort_container")
                )
        );

        // 5. Selectăm Price (low to high)
        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Price (low to high)");

        // 6. Luăm toate prețurile produselor
        List<WebElement> preturiProduse = driver.findElements(
                By.className("inventory_item_price")
        );

        // 7. Verificăm că avem produse
        Assert.assertTrue(
                preturiProduse.size() > 0,
                "Nu au fost găsite produse."
        );

        // 8. Verificăm că prețurile sunt în ordine crescătoare
        for (int i = 0; i < preturiProduse.size() - 1; i++) {

            double pretActual = Double.parseDouble(
                    preturiProduse.get(i).getText().replace("$", "")
            );

            double pretUrmator = Double.parseDouble(
                    preturiProduse.get(i + 1).getText().replace("$", "")
            );

            Assert.assertTrue(
                    pretActual <= pretUrmator,
                    "Produsele NU sunt sortate crescător după preț."
            );


        }
    }
}
