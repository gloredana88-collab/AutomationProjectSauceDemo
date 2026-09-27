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

public class TestVerificareAnonimizareParola {

    @Test
    public void metodaTestAnonimizareParola () {

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
        String usernameInvalid = ("user_standard");
        String passwordInvalid = ("invalidpassword");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.click();
        password.sendKeys(passwordCredential);

        String tipCampPassword = password.getAttribute("type");
        System.out.println("Tipul campului Password: " + tipCampPassword);
        Assert.assertEquals( tipCampPassword, "password", "Campul Password NU este anonimizat." );

        driver.quit();
    }

}


