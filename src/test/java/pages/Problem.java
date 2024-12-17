package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;
import java.util.List;

public class Problem {
    private WebDriverWait wait;
    public WebDriver driver;
    public Problem(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set timeout
    }


    public void modeOfRequest(String ModeofRequest) {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.PmodeOfRequest))).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = ModeofRequest;
        boolean optionFound = false;
        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equalsIgnoreCase(criteria)) { // Using equalsIgnoreCase for case-insensitive match
                option.click(); // Click the matching option
                optionFound = true;
                break; // Exit loop once the option is found
            }}
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }}

    public void station(String Station) {
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.PstationXpath))).click();
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
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Pclassification))).click();
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
        }
    }

    public void subClassification(String SubClassification) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.PsubClassificationXpath))).click();
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
    public void severity(String Severity){
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Psevrity))).click();
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
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Purgencyxpath))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Urgency;
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
    public void subject(String Subject){
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(XPathProvider.subjectInput)));
        inputField.clear();
        inputField.sendKeys(Subject);
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
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Submit"))).click();
    }
    public void priority(String Priority) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.PperiorityXpath))).click();
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
    }

    public void createProblem(  String modeOfRequest, String station,
                               String classification, String SubClassification  , String severity, String priority,
                               String urgency, String Subject,
                                String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        reportedTime();
        classification(classification);
        subClassification(SubClassification);
        severity(severity);
        priority(priority);
        urgency(urgency);
        subject(Subject);
        linketTicket();
        // Upload the files
        Attachments(filePath);
    //    Submit();
    }}
