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
    public Incident(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set timeout
    }

    public void modeOfRequest(String ModeofRequest) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.ImodeOfRequest))).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = ModeofRequest;
        boolean optionFound = false;
        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equalsIgnoreCase(criteria)) {
                option.click();
                optionFound = true;
                break;
            }
        }
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }
    }

    public void station(String Station) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath( XPathProvider.IstationXpath))).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = Station;
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

    public void classification(String Classification) {
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Iclassification))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Classification;
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
        }}

    public void subClassification(String SubClassification) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath( XPathProvider.IsubClassificationXpath))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = SubClassification;
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

    public void partyName(String PartyName) {
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.IpartyName))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = PartyName;
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
    public void circuits(String Circuits){
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Icircuit))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Circuits;
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

  /*  public void ProtectionStatus() {
        try {
             wait.until(ExpectedConditions.elementToBeClickable(By.id("ProtectionStatus"))).click();
            System.out.println("ProtectionStatus element clicked successfully.");
        } catch (Exception e) {
             System.err.println("Error clicking on ProtectionStatus element: " + e.getMessage());
            e.printStackTrace();
        }} */

    public void priority(String Priority){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.IperiorityXpath))).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = Priority;
        boolean optionFound = false;
        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equalsIgnoreCase(criteria)) {
                option.click();
                optionFound = true;
                break;
            }
        }
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }
    }
    public void severity(String Severity){
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath( XPathProvider.Isevrity))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Severity;
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

    public void urgency(String Urgency){
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Iurgencyxpath))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Urgency;
         boolean optionFound = false;
         for (WebElement option : options) {
            String optionText = option.getText();
             if (optionText.equalsIgnoreCase(criteria)) {
                option.click();
                optionFound = true;
                break;
            }
        }
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }
    }
    public void subject(String Subject){
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(XPathProvider.subjectInput)));
        inputField.clear();
        inputField.sendKeys(Subject); // Input the value
        System.out.println("Value inputted: " + Subject);
    }
    public void Attachments(String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
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
    public void createIncident(  String modeOfRequest, String station,
                               String classification, String SubClassification,String PartyName,String  Circuits , String severity, String priority,
                               String urgency, String Subject,
                                String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        reportedTime();
        classification(classification);
        subClassification(SubClassification);
        partyName(PartyName);
        circuits(Circuits);
        //ProtectionStatus();
        severity(severity);
        priority(priority);
        urgency(urgency);
        subject(Subject);
        linketTicket();
        // Upload the files
       Attachments(filePath);
        Submit();
    }

}



