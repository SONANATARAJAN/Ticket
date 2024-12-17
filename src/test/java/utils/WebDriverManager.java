package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverManager {
    public WebDriver driver;
    public WebDriverWait wait;
    public WebDriverManager(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }
     public void clickMethod(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
     }
     public void visibleMethod(String locator){
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
     }
    public void sendkeys(String locator,String input){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).sendKeys(input);
    }
    //Ticket flow
    public void clickElement(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
    }
    public void submitTicket(){
        clickElement("//button[@id='Submit' and text()='Submit']");
    }
    public void resolveYes(){
        clickElement("//div[@id='resolutionStatus']//a[text()='Yes']");
    }
    public void resolved(){

    }
    //Request Fulfilment
    public void approvalRequest_Yes(){
        clickElement("//div[@id='approvalRequestCheckBox']//a[text()='Yes']");
    }


    }


