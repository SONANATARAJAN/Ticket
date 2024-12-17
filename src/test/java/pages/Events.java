package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;

public class Events {
    private WebDriverWait wait;
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;
    public Events(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout
        this.webDriverManager = new utils.WebDriverManager(driver); // Create an instance
    }

    public void modeOfRequest(String ModeofRequest) {
        webDriverManager.dropDownSelect(XPathProvider.EmodeOfRequest,ModeofRequest);
    }
    public void station(String Station){
        webDriverManager.dropDownSelect(XPathProvider.EstationXpath,Station);
    }

    public void classification(String Classification){
        webDriverManager.dropDownSelect(XPathProvider.Eclassification,Classification);
    }
    public void subClassification(String SubClassification){
        webDriverManager.dropDownSelect(XPathProvider.EsubClassificationXpath,SubClassification);
    }
public void severity(String Severity){
        webDriverManager.dropDownSelect(XPathProvider.Esevrity,Severity);
}
    public void periority(String Periority){
        webDriverManager.dropDownSelect(XPathProvider.EperiorityXpath,Periority);
    }
    public void urgency(String Urgency){
        webDriverManager.dropDownSelect(XPathProvider.Eurgencyxpath,Urgency);
    }
    public void eventType(String EventType){
        webDriverManager.dropDownSelect(XPathProvider.EeventTypexpath,EventType);
    }
    public void categoryInput(String Category){
        webDriverManager.dropDownSelect(XPathProvider.EcategoryXpath,Category);
    }
    public void notifyToIRU(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.notifyToIRU))).click();
    }
    public void NotifyToIRUwithAttachment(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.EnotifyIRUToIRUAttachment))).click();
    }
    public void ImpactedCircuitAttachment(String filePath){
        String filEPath = System.getProperty("user.dir")+"/" + filePath;
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
    }
    public void createEvent(  String modeOfRequest, String station,
                               String classification, String subClassification, String severity, String priority,
                               String urgency, String eventType, String category, String subjectInput,
                             String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        webDriverManager.reportedTime();
        classification(classification);
        subClassification(subClassification);
        severity(severity);
        periority(priority);
        urgency(urgency);
        eventType(eventType);
        categoryInput(category);
        webDriverManager.subjectInput(subjectInput);
        notifyToIRU();
        webDriverManager.linketTicket();
        NotifyToIRUwithAttachment();
        // Upload the files
        webDriverManager.Attachments(filePath);
        ImpactedCircuitAttachment(filePath);
        webDriverManager.Submit();
    }

}




