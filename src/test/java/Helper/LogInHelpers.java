package Helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInHelpers {
    WebDriver driver;

    public LogInHelpers(WebDriver driver) {
        this.driver = driver;
    }

    public void waitVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));

    }

    private void waitToBeClickable(WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickOnElement(WebElement element){
        waitVisibility(element);
        waitToBeClickable(element);
        element.click();
    }

    public void clearElement (WebElement element){
        waitVisibility(element);
        waitToBeClickable(element);
        element.click();
        element.clear();
    }

    public void sendText(WebElement element, String text){
        waitVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isElementDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

//
//    public void scrollToElement(WebElement element){
//        waitVisibility(element);
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].scrollIntoView({block: 'center'});",
//                element
//        );
//    }

}
