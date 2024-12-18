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
    private final utils.WebDriverManager webDriverManager;

    public EventTTFlow(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.webDriverManager = new utils.WebDriverManager(driver);
    }
    //call
    public void ProblemResolved() throws InterruptedException {
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
        webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
        webDriverManager.submitTicket();
        webDriverManager.resolveYes();
        webDriverManager.submitTicket();
        Thread.sleep(5000);
        ProblemResolved();

}
//call
public void resoluationVeriFyStatus_no_noc() throws InterruptedException {
    webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
    webDriverManager.submitTicket();
    Thread.sleep(5000);
    webDriverManager.submitTicket();
    //noc
    webDriverManager.clickElement("//div[@id='incidentBoundarycheck']//a[text()='NOC']");
    webDriverManager.submitTicket();
    Thread.sleep(5000);
}
//2
public void resoluationVeriFyStatus_no_noc_yes() throws InterruptedException {
        resoluationVeriFyStatus_no_noc();
    webDriverManager.resolveYes();
    webDriverManager.submitTicket();
    ProblemResolved();
    Thread.sleep(4000);
}
//3
public void resoluationVeriFyStatus_no_noc_no_withoutV() throws InterruptedException{
    resoluationVeriFyStatus_no_noc();
    webDriverManager.submitTicket();
    webDriverManager.clickElement("//input[@id='supplierInvolmentCheckBox']");
    webDriverManager.submitTicket();
    ProblemResolved();

}
//4
public void resoluationVeriFyStatus_no_noc_no_withV() throws InterruptedException{
        resoluationVeriFyStatus_no_noc();
        Thread.sleep(3000);
        webDriverManager.submitTicket();
    webDriverManager.clickElement("//button[@id='ReferVendor']");
    //refer to third party
    webDriverManager.selectByVisibleText("//select[@id='refertoGroup']","Tyco");
    webDriverManager.sendkeys("//textarea[@id='description']","Description");
    webDriverManager.clickElement("//button[@id='Submit' and text()='Create Ticket']");
    Thread.sleep(5000);
    webDriverManager.clickElement("//input[@id='supplierInvolmentCheckBox']");
    webDriverManager.submitTicket();
    ProblemResolved();
    }
//call
public void resoluationVeriFyStatus_no_others() throws InterruptedException{
        webDriverManager.clickElement(XPathProvider.causeIdentificationXpath);
    webDriverManager.submitTicket();
    Thread.sleep(5000);
    webDriverManager.submitTicket();
    Thread.sleep(5000);


}
//5
    public void resoluationVeriFyStatus_no_others_without()throws InterruptedException{
        resoluationVeriFyStatus_no_others();
        webDriverManager.submitTicket();
        webDriverManager.clickElement("//input[@id='thirdPartyInvolmentCheckBox']");
        webDriverManager.submitTicket();
        ProblemResolved();
    }
    //6
    public void resoluationVeriFyStatus_no_others_with() throws InterruptedException{
        resoluationVeriFyStatus_no_others();
        webDriverManager.submitTicket();
        webDriverManager.clickElement("//button[@id='Submit' and text()='Refer To Third Party']");
        webDriverManager.selectByVisibleText("//select[@id='station']","LONDON");
        webDriverManager.sendkeys("//textarea[@id='description']","Description");
        //refertoGroup
        webDriverManager.selectByVisibleText("//select[@id='refertoGroup']","Cable Station Staff");
        webDriverManager.clickElement("//button[@id='Submit' and text()='Create Ticket']");
        webDriverManager.clickElement("//input[@id='thirdPartyInvolmentCheckBox']");
        webDriverManager.submitTicket();
        ProblemResolved();
    }
}
