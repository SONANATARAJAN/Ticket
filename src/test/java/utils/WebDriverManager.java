package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
//TT creation Process
    public void Attachments(String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
    }
    public void dropDownSelect(String clickXpath, String Value) {

        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(clickXpath);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = Value;
        boolean optionFound = false;
        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equalsIgnoreCase(criteria)) {
                option.click();
                optionFound = true;
                break;
            }}
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }
    }
    public void reportedTime(){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.visibleMethod((XPathProvider.reportedTime));
        Actions action=new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }
    public void subjectInput(String SubjectInput){
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(XPathProvider.subjectInput)));
        inputField.clear();
        inputField.sendKeys(SubjectInput);
        System.out.println("Value inputted: " + SubjectInput);
    }
    public void linketTicket(){
        WebElement ele = driver.findElement(By.xpath("//div[@id='s2id_LinkedTicket']"));
        ele.click();
        WebElement sk =  driver.findElement(By.xpath("//input[@id='s2id_autogen8_search']"));
        sk.sendKeys("EIG-TT");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li[1]/div")));
        option.click();
    }
    public void Submit(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.submit))).click();
    }

    }


