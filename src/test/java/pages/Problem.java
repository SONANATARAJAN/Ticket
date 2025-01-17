package pages;

import org.apache.xmlbeans.impl.xb.xsdschema.FieldDocument;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;
import java.util.List;

import static pages.Change.ticketNumber;

public class Problem {
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;
    public WebDriverWait wait;

    public Problem(WebDriver driver) {
        this.driver=driver;
        this.webDriverManager = new utils.WebDriverManager(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));

    }


    public void modeOfRequest(String ModeofRequest) {
        webDriverManager.dropDownSelect(XPathProvider.PmodeOfRequest,ModeofRequest);
    }
    public void station(String Station) {
         webDriverManager.dropDownSelect(XPathProvider.PstationXpath,Station);
    }
    public void classification(String Classification) {
        webDriverManager.dropDownSelect(XPathProvider.Pclassification,Classification);
    }
    public void subClassification(String SubClassification) {
        webDriverManager.dropDownSelect(XPathProvider.PsubClassificationXpath,SubClassification);
    }
    public void severity(String Severity){
        webDriverManager.dropDownSelect(XPathProvider.Psevrity,Severity);
    }
    public void urgency(String Urgency){
        webDriverManager.dropDownSelect(XPathProvider.Purgencyxpath,Urgency);
    }
    public static String ProblemticketNumber; // Shared variable

    public void PcaptureTicketId() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']//div[@class='message']")));
        String ticketId = alert.getText();
        ProblemticketNumber = ticketId.split(":")[1].trim(); // Assign to static variable
        System.out.println("Full String: " + ticketId);
        System.out.println("Split String into Ticket ID: " + ProblemticketNumber);
    }

    public void priority(String Priority) {
        webDriverManager.dropDownSelect(XPathProvider.PperiorityXpath,Priority);

    }

    public void createProblem(  String modeOfRequest, String station,
                               String classification, String SubClassification  , String severity, String priority,
                               String urgency, String Subject,
                                String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        webDriverManager.reportedTime();
        classification(classification);
        subClassification(SubClassification);
        severity(severity);
        priority(priority);
        urgency(urgency);
        webDriverManager.subjectInput(Subject);
        //webDriverManager.linketTicket();
        // Upload the files
        webDriverManager.Attachments(filePath);
        webDriverManager.Submit();
        webDriverManager.captureTicketId();
        PcaptureTicketId();
    }}
