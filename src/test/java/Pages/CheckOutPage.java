package Pages;

import DataBase.Queries.CheckOutTable;
import Helper.LogInHelpers;
import Logger.LoggerUtility;
import ObjectData.CheckOutObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.sql.SQLException;

public class CheckOutPage {
    WebDriver driver;
    LogInHelpers logInHelpers;
//    CheckOutTable checkOutTable;

    public CheckOutPage(WebDriver driver) {
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
//        this.checkOutTable = new CheckOutTable();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "first-name")
    WebElement firstNameField;

    @FindBy(id = "last-name")
    WebElement lastNameField;

    @FindBy(id = "postal-code")
    WebElement postalCodeField;

    @FindBy(id = "cancel")
    WebElement cancelButton;

    @FindBy(id = "continue")
    WebElement continueButton;

//    @FindBy(className = "error-button")
//    WebElement eroarelipsaDate;

    @FindBy(css = "h3[data-test='error']")
    WebElement checkoutErrorMessage;

    public void entryCheckOut(CheckOutObjects checkOutObjects){
        addFirstNameField(checkOutObjects.getEnterFirstName());
        addLastNameField(checkOutObjects.getEnterLastName());
        addZipCode(checkOutObjects.getEnterZipCode());

    }

    public void addFirstNameField (String firstName) {
//        System.out.println("Se introduc datele in rubrica First Name");
        logInHelpers.sendText(firstNameField, firstName);
        LoggerUtility.infoTestCase("Se introduc datele in rubrica First Name");
    }

    public void addLastNameField (String lastName) {
//        System.out.println("Se introduc datele in rubrica Last Name");
        logInHelpers.sendText(lastNameField, lastName);
        LoggerUtility.infoTestCase("Se introduc datele in rubrica Last Name");
    }

    public void addZipCode (String zipCode) {
//        System.out.println("Se introduc datele in rubrica ZipCode");
        logInHelpers.sendText(postalCodeField, zipCode);
        LoggerUtility.infoTestCase("Se introduc datele in rubrica ZipCode");
    }


    public void continueCheckout() {
        logInHelpers.clickOnElement(continueButton);
//        System.out.println("S-a selectat optiunea Continue");
        LoggerUtility.infoTestCase("S-a selectat optiunea Continue");
    }

    public void cancelCheckout() {
        logInHelpers.clickOnElement(cancelButton);
        System.out.println("S-a selectat optiunea Cancel");
        LoggerUtility.infoTestCase("S-a selectat optiunea Cancel");

    }

    public void assertCancelCheckout() {

        String currentUrl = driver.getCurrentUrl();

        System.out.println("Se verifica pagina dupa Cancel.");
        System.out.println("URL actual: " + currentUrl);

        Assert.assertTrue(
                currentUrl.contains("cart.html"),
                "Nu s-a revenit in Your Cart dupa Cancel."
        );

        System.out.println("Assert trecut: suntem in Your Cart.");
    }

//    public void assertDateInvalideCheckout() {
//        String confirmOrder =  eroarelipsaDate.getText();
//        System.out.println("MESAJ ACTUAL: [" + confirmOrder + "]");
//        assert confirmOrder.contains("Error:");
//    }
//public void assertDateInvalideCheckout(CheckOutObjects data) {
//
//    if (data.getEnterFirstName() == null || data.getEnterFirstName().isEmpty()) {
//
//        Assert.assertEquals(
//                checkoutErrorMessage.getText(),
//                "Error: First Name is required"
//        );
//
//    } else if (data.getEnterFirstName() == null || data.getEnterLastName().isEmpty()) {
//
//        Assert.assertEquals(
//                checkoutErrorMessage.getText(),
//                "Error: Last Name is required"
//        );
//
//    } else if (data.getEnterZipCode() == null || data.getEnterZipCode().isEmpty()) {
//
//        Assert.assertEquals(
//                checkoutErrorMessage.getText(),
//                "Error: Postal Code is required"
//        );
//    }
//}

    public void assertDateInvalideCheckout(CheckOutObjects data) {

        if (data.getEnterFirstName() == null || data.getEnterFirstName().isBlank()) {

            System.out.println("Se verifica eroarea pentru First Name.");
            System.out.println("Mesaj asteptat: Error: First Name is required");
            System.out.println("Mesaj primit: " + checkoutErrorMessage.getText());

            Assert.assertEquals(checkoutErrorMessage.getText(), "Error: First Name is required");
            System.out.println("Assert pentru First Name a trecut.");

        } else if (data.getEnterLastName() == null || data.getEnterLastName().isBlank()) {
            System.out.println("Se verifica eroarea pentru Last Name.");
            System.out.println("Mesaj asteptat: Error: Last Name is required");
            System.out.println("Mesaj primit: " + checkoutErrorMessage.getText());

            Assert.assertEquals(
                    checkoutErrorMessage.getText(),
                    "Error: Last Name is required"
            );
            System.out.println("Assert pentru Last Name a trecut.");

        } else if (data.getEnterZipCode() == null || data.getEnterZipCode().isBlank()) {

            System.out.println("Se verifica eroarea pentru Postal Code.");
            System.out.println("Mesaj asteptat: Error: Postal Code is required");
            System.out.println("Mesaj primit: " + checkoutErrorMessage.getText());

            Assert.assertEquals(
                    checkoutErrorMessage.getText(),
                    "Error: Postal Code is required"
            );

            System.out.println("Assert pentru Postal Code a trecut.");
        }
    }


//    public void dateinserate(){
//        WebElement rowAdded = rezultatInsert;
//        String rowaddedText = rowAdded.getText();
//        System.out.println(rowaddedText);
//
//    }
//
//    public void addEntryInTable(CheckOutObjects data) throws SQLException {
//        checkOutTable.insertTableRow(data);
//    }
}
