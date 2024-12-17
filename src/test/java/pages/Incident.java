package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;
import java.util.List;



public class Incident {
    private WebDriverWait wait;
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;
    public Incident(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set timeout
        this.webDriverManager = new utils.WebDriverManager(driver);
    }

    public void modeOfRequest(String ModeofRequest) {
        webDriverManager.dropDownSelect(XPathProvider.ImodeOfRequest,ModeofRequest);
    }

    public void station(String Station) {
        webDriverManager.dropDownSelect(XPathProvider.IstationXpath,Station);
    }
    public void classification(String Classification) {
        webDriverManager.dropDownSelect(XPathProvider.Iclassification,Classification);
    }

    public void subClassification(String SubClassification) {
        webDriverManager.dropDownSelect(XPathProvider.IsubClassificationXpath,SubClassification);
    }

    public void partyName(String PartyName) {
        webDriverManager.dropDownSelect(XPathProvider.IpartyName,PartyName);
    }
    public void circuits(String Circuits){
        webDriverManager.dropDownSelect(XPathProvider.Icircuit,Circuits);
    }

  /*  public void ProtectionStatus() {
        try {
             wait.until(ExpectedConditions.elementToBeClickable(By.id("ProtectionStatus"))).click();
            System.out.println("ProtectionStatus element clicked successfully.");
        } catch (Exception e) {
             System.err.println("Error clicking on ProtectionStatus element: " + e.getMessage());
            e.printStackTrace();
        }} */

    public void priority(String Priority){
        webDriverManager.dropDownSelect(XPathProvider.IperiorityXpath,Priority);
    }
    public void severity(String Severity){
        webDriverManager.dropDownSelect(XPathProvider.Isevrity,Severity);
    }

    public void urgency(String Urgency){
        webDriverManager.dropDownSelect(XPathProvider.Iurgencyxpath,Urgency);
    }
    public void createIncident(  String modeOfRequest, String station,
                               String classification, String SubClassification,String PartyName,String  Circuits , String severity, String priority,
                               String urgency, String Subject,
                                String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        webDriverManager.reportedTime();
        classification(classification);
        subClassification(SubClassification);
        partyName(PartyName);
        circuits(Circuits);
        //ProtectionStatus();
        severity(severity);
        priority(priority);
        urgency(urgency);
        webDriverManager.subjectInput(Subject);
        webDriverManager.linketTicket();
        // Upload the files
        webDriverManager.Attachments(filePath);
        webDriverManager.Submit();
    }

}



