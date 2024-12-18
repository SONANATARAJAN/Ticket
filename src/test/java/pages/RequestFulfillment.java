package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;
import java.time.Duration;
import java.util.List;

public class RequestFulfillment {
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;


    public RequestFulfillment(WebDriver driver) {
        this.driver=driver;
        this.webDriverManager = new utils.WebDriverManager(driver);

    }

    public void modeOfRequest(String ModeofRequest) {
        webDriverManager.dropDownSelect(XPathProvider.RmodeOfRequest,ModeofRequest);
    }

    public void station(String Station) {
        webDriverManager.dropDownSelect(XPathProvider.RstationXpath,Station);
    }


    public void classification(String Classification) {
        webDriverManager.dropDownSelect(XPathProvider.Rclassification,Classification);
    }

    public void subClassification(String SubClassification) {
        webDriverManager.dropDownSelect(XPathProvider.RsubClassificationXpath,SubClassification);
    }
    public void staionName(String StationName){
        webDriverManager.dropDownSelect(XPathProvider.RstationXpath,StationName);
    }

    public void priority(String Priority) {
        webDriverManager.dropDownSelect(XPathProvider.RperiorityXpath,Priority);
    }
        public void createRequestfulfiment (String modeOfRequest,String station,
                String classification, String SubClassification,String StationName,  String priority,
                String Subject,
                String filePath){

            modeOfRequest(modeOfRequest);
            station(station);
            webDriverManager.reportedTime();
            classification(classification);
            subClassification(SubClassification);
            staionName(StationName);
            priority(priority);
            webDriverManager.subjectInput(Subject);
            webDriverManager.linketTicket();
            // Upload the files
            webDriverManager.Attachments(filePath);
            webDriverManager.linketTicket();
            webDriverManager.Submit();
        }}

