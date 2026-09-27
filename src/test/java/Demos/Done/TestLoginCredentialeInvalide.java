package Demos.Done;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestLoginCredentialeInvalide {

    @Test
    public void metodaTestUserInvalid () {

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



        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.click();
        username.sendKeys(usernameInvalid);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passwordCredential);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement mesajEroareCredentiale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
//        assert mesajEroareCredentiale.getText().equals("Epic sadface: Username and password do not match any user in this service");
//        assert mesajEroareCredentiale.getText().contains("Epic sadface: Username and password do not match any user in this service");

        String mesajActual = mesajEroareCredentiale.getText();

        System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
        assert mesajActual.contains("Epic sadface: Username and password do not match any user in this service");




    }

    @Test
    public void metodaUsernameNull () {

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


        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passwordCredential);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement mesajEroareCredentiale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
//        assert mesajEroareCredentiale.getText().equals("Epic sadface: Username and password do not match any user in this service");
//        assert mesajEroareCredentiale.getText().contains("Epic sadface: Username is required");

        String mesajActual = mesajEroareCredentiale.getText();

        System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
        assert mesajActual.contains("Epic sadface: Username is required");

    }

    @Test
    public void metodaTestPasswordInvalid () {

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



        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.click();
        username.sendKeys(usernameCredential);

        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys(passwordInvalid);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement mesajEroareCredentiale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
//        assert mesajEroareCredentiale.getText().equals("Epic sadface: Username and password do not match any user in this service");
//        assert  mesajEroareCredentiale.getText().contains("Epic sadface: Username and password do not match any user in this service");
        String mesajActual = mesajEroareCredentiale.getText();

        System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
        assert mesajActual.contains("Epic sadface: Username and password do not match any user in this service");


    }

    @Test
    public void metodaTestPasswordNull () {

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



        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name")));
        username.click();
        username.sendKeys(passwordCredential);

//        WebElement password = driver.findElement(By.id("password"));
//        password.click();
//        password.sendKeys(usernameInvalid);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();

        WebElement mesajEroareCredentiale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@data-test='error']")));
//        WebElement mesajEroareCredentiale = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='error-message-container error']")));
//        assert  mesajEroareCredentiale.getText().contains("Epic sadface: Password is required");

        String mesajActual = mesajEroareCredentiale.getText();

        System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
        assert mesajActual.contains("Epic sadface: Password is required");

    }
}
