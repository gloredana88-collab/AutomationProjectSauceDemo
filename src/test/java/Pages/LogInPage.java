package Pages;

import Helper.LogInHelpers;
import Logger.LoggerUtility;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogInPage {

    WebDriver driver;
    LogInHelpers logInHelpers;
//    ProductsPage productsPage;

    public LogInPage(WebDriver driver) {
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
//        this.productsPage = new ProductsPage(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    WebElement username ;

    @FindBy(id = "password")
    WebElement password;

    @FindBy(id = "login-button")
    WebElement  loginButton;

    @FindBy(className = "login_logo")
    WebElement loginLogo;

    @FindBy(xpath = "//h3[@data-test='error']")
    WebElement mesajEroareLogin;

    String usernameCredential = ("standard_user");
    String passwordCredential= ("secret_sauce");



    public void clickUsername() {
        logInHelpers.clickOnElement(username);
    }

    public void fillUsername() {
        logInHelpers.sendText(username, usernameCredential );
        System.out.println("S-a introdus username");
        LoggerUtility.infoTestCase("Insert data username.");
        ChainTestListener.log("Insert data username.");
    }

    public void clickPassword() {
        logInHelpers.clickOnElement(password);
    }

    public void fillPassword() {
        logInHelpers.sendText(password, passwordCredential);
        System.out.println("S-a introdus parola");
        LoggerUtility.infoTestCase("Insert data parola.");
        ChainTestListener.log("Insert data parola.");
    }

    public void clickLogin() {
        logInHelpers.clickOnElement(loginButton);
        System.out.println("S-a selectat login");
        LoggerUtility.infoTestCase("Select login button.");
        ChainTestListener.log("Select login button.");
    }


    public void verificareLoginReusit() {
        Assert.assertFalse(logInHelpers.isElementDisplayed(loginLogo), "Login-ul nu a fost realizat cu succes. Logo-ul de Login este încă vizibil.");
        LoggerUtility.infoTestCase("Assert Login.");
        ChainTestListener.log("Assert Login.");
    }

    //login failed

    String usernameInvalid = ("invaliduser");
    String passwordInvalid = ("invalidpassword");

    public void fillUsernameInvalid(){
        logInHelpers.sendText(username, usernameInvalid );
    }

    public void fillPasswordInvalid() {
        logInHelpers.sendText(password, passwordInvalid);
    }


    public void assertMesajEroareLogin() {
        String mesajActual = mesajEroareLogin.getText();
            System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
            assert mesajActual.contains("Epic sadface: Username and password do not match any user in this service");
            LoggerUtility.infoTestCase("Assert Error Message Login.");
            ChainTestListener.log("Assert Error Message Login.");

    }

    public void clearinformation() {
        logInHelpers.clearElement(username);
        logInHelpers.clearElement(password);
        System.out.println("Datele au fost eliminate");
    }

    public void assertMesajEroareUserNull() {
        String mesajActual = mesajEroareLogin.getText();
            System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
            assert mesajActual.contains("Epic sadface: Username is required");

    }

    public void assertMesajEroarePasswordNull() {
        String mesajActual = mesajEroareLogin.getText();
            System.out.println("MESAJ ACTUAL: [" + mesajActual + "]");
            assert mesajActual.contains("Epic sadface: Password is required");

    }

    public void prezentaLogin() {
        assert loginButton.isDisplayed();
    }

    public void assertAnonimizareParola() {
        String valoareCampPassword = password.getAttribute("type");
            System.out.println("Tipul campului Password: " + valoareCampPassword);
            Assert.assertEquals( valoareCampPassword, "password", "Campul Password NU este anonimizat." );
    }

    public void loginaction() {
        clickUsername();
        fillUsername();
        clickPassword();
        fillPassword();
        clickLogin();
        System.out.println("Autentificare reusita");
        LoggerUtility.infoTestCase("Confim Login.");
        ChainTestListener.log("Confim Login.");
    }


}
