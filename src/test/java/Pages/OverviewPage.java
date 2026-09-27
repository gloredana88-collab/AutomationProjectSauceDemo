package Pages;

import Helper.LogInHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OverviewPage {

    WebDriver driver;
    LogInHelpers logInHelpers;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.logInHelpers = new LogInHelpers(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "cancel")
    WebElement cancelOverviewButton;

    @FindBy(id = "finish")
    WebElement finishButton;

    @FindBy(className = "complete-header")
    WebElement completeOrder;

    public void finishOrder(){
        logInHelpers.clickOnElement(finishButton);
        System.out.println("Comanda Finalizata. Navighez pe pagina Checkout: Complete!");
    }

    public void cancelFromOverview(){
        logInHelpers.clickOnElement(cancelOverviewButton);

    }

//    public void scroll(){
//        logInHelpers.scrollToElement(finishButton);
//    }

        public void assertCompleteOrder(){
        String confirmOrder =  completeOrder.getText();
            System.out.println("MESAJ ACTUAL: [" + confirmOrder + "]");
            assert confirmOrder.contains("Thank you for your order!");
    }
}
