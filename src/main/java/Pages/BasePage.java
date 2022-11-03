package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    private static WebDriver driver;

    public static WebDriver drivers(){
        return BasePage.driver;
    }

    public BasePage(WebDriver driver){
        this.driver = driver;

    }

    public WebElement find(By locator){
        return driver.findElement(locator);
    }

    public void click(By locator){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));    //Element görünene kadar bekle
        find(locator).click();
    }

    public void send(By locator,String text){
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));  //elementi gördüğün zaman
        find(locator).sendKeys(text);   //bir metin girişi yapmak için
    }

    public WebElement getElementLocated(By by){
        WebDriverWait wait = new WebDriverWait(driver,10);
        return wait.until(ExpectedConditions.elementToBeClickable(by));

    }



    public boolean isDisplay(By by){       //elementin varlığını kontrol etmek için
        try{
            if (getElementLocated(by).isDisplayed())
                return true;
        }catch (TimeoutException e){
            return false;
        }
        return false;
    }

    public void waitForSecond(int second){
        try {
            TimeUnit.SECONDS.sleep(second);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void scrollPageElement(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();",element);

    }



}
