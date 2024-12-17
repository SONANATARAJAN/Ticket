package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;

public class EventTTFlow {
    private WebDriverWait wait;
    public WebDriver driver;

    public EventTTFlow(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //call
    public void ProblemResolved() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        //Problem Resolve
        webDriverManager.clickElement(XPathProvider.problemResolve1);
        webDriverManager.clickElement(XPathProvider.problemResolve2);
        webDriverManager.submitTicket();
        Thread.sleep(5000);
        webDriverManager.sendkeys("//div[@class='note-editable']","Closed Ticket");
        webDriverManager.submitTicket();
    }
    //1
public void resoluationVeriFyStatus_yes() throws InterruptedException {
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
    webDriverManager.submitTicket();
    webDriverManager.resolveYes();
    Thread.sleep(5000);
    ProblemResolved();

}
//call
public void resoluationVeriFyStatus_no_noc() throws InterruptedException {
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
    webDriverManager.submitTicket();
    Thread.sleep(5000);
    webDriverManager.submitTicket();
    //noc
    webDriverManager.clickElement("//div[@id='incidentBoundarycheck']//a[text()='NOC']");
    webDriverManager.submitTicket();
}
//2
public void resoluationVeriFyStatus_no_noc_yes() throws InterruptedException {
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    resoluationVeriFyStatus_no_noc();
    webDriverManager.resolveYes();
    ProblemResolved();
    Thread.sleep(4000);
}
//3
public void resoluationVeriFyStatus_no_noc_no_withoutV() throws InterruptedException{
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    resoluationVeriFyStatus_no_noc();
    webDriverManager.submitTicket();
    webDriverManager.clickElement("//input[@id='supplierInvolmentCheckBox']");
    webDriverManager.submitTicket();
    ProblemResolved();

}
//4
public void resoluationVeriFyStatus_no_noc_no_withV() throws InterruptedException{
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    resoluationVeriFyStatus_no_noc();
    webDriverManager.submitTicket();
    webDriverManager.clickElement("//button[@id='ReferVendor']");
    //bug
    }
//call
public void resoluationVeriFyStatus_no_others() throws InterruptedException{
    WebDriverManager webDriverManager=new WebDriverManager(driver);
    webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
    webDriverManager.submitTicket();
    Thread.sleep(3000);
    webDriverManager.submitTicket();
}
//5
    public void resoluationVeriFyStatus_no_others_without()throws InterruptedException{
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        resoluationVeriFyStatus_no_others();
        webDriverManager.clickElement("//input[@id='thirdPartyInvolmentCheckBox']");
        webDriverManager.submitTicket();
        ProblemResolved();
    }
    //6
    public void resoluationVeriFyStatus_no_others_with() throws InterruptedException{
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        resoluationVeriFyStatus_no_others();
        webDriverManager.clickElement("//button[@id='Submit' and text()='Refer To Third Party']");
       WebElement selectdrp= driver.findElement(By.xpath("//select[@id='station']"));
       Select drp=new Select(selectdrp);
       drp.selectByValue("LONDON");
       //refertoGroup
        WebElement selectdrop= driver.findElement(By.xpath("//select[@id='refertoGroup']"));
        Select drop=new Select(selectdrop);
        drp.selectByValue("LONDON");
        drop.selectByValue("Cable Station Staff");
        webDriverManager.submitTicket();
        //bug

    }
}
