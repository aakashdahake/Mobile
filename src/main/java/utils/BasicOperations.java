package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasicOperations {

    public static WebDriver driver;
    static WebDriverWait wait;
 
    /**
     * Wait until element is displayed
     * @param driver
     * @param seconds
     * @param element
     */
    public void waitForWebElementPresent(WebDriver driver, int seconds, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver,seconds);
        wait.until( ExpectedConditions.visibilityOf(element));
    }


    public void scrollIntoViewElement(WebDriver driver , WebElement element) {

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    
    /**
     * Is element present
     * @param locator
     * @param timeoutInSeconds
     * @return
     */
    public boolean isElementPresent(By locator, int timeoutInSeconds) {
        try {
            wait = new WebDriverWait(driver, timeoutInSeconds);
            WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            if (elem != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }



    /**
     * find webelement
     * @param locator
     * @param timeoutSeconds
     * @return
     */
    public WebElement findElement(By locator, int timeoutSeconds) {
        wait = new WebDriverWait(driver, timeoutSeconds);
        WebElement elem = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (elem != null) {
            return elem;
        } else {
            return null;
        }
    }

    public boolean WaitTime(long time) {
        long startTime = System.currentTimeMillis();;
        long waitTime = Long.parseLong(ConfigManager.getProperty("WaitTime")) * 60000;
        long endTime = time + waitTime;
        if (startTime < endTime) {
            return true;
        } else {
            return false;
        }
    }
}