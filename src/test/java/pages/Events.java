package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;
import java.util.List;
public class Events {

    private WebDriverWait wait;
    public WebDriver driver;

    public Events(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout
    }

    public void modeOfRequest(String ModeofRequest) {

        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.EmodeOfRequest);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = ModeofRequest;
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

    public void station(String Station){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod((XPathProvider.EstationXpath)) ;
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
    public void classification(String Classification){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
         webDriverManager.clickMethod(XPathProvider.Eclassification);
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
    public void subClassification(String SubClassification){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
         webDriverManager.clickMethod(XPathProvider.EsubClassificationXpath);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = SubClassification;
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
     WebDriverManager webDriverManager=new WebDriverManager(driver);
     webDriverManager.clickMethod(XPathProvider.Esevrity);
     List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath( XPathProvider.commonDropSelect)));
     String criteria = Severity;
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
    public void periority(String Periority){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.EperiorityXpath);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = Periority;
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
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.Eurgencyxpath);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath( XPathProvider.commonDropSelect)));
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
    public void eventType(String EventType){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.EeventTypexpath);
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath( XPathProvider.commonDropSelect)));
         String criteria = EventType;
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
    public void categoryInput(String Category){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.EcategoryXpath);
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
         String criteria = Category;
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
    public void subjectInput(String SubjectInput){
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(XPathProvider.subjectInput)));
        inputField.clear();
        inputField.sendKeys(SubjectInput);
        System.out.println("Value inputted: " + SubjectInput);
    }
    public void notifyToIRU(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.notifyToIRU))).click();
    }


    public void linketTicket(){
        WebElement ele = driver.findElement(By.xpath("//div[@id='s2id_LinkedTicket']"));
        ele.click();
        WebElement sk =  driver.findElement(By.xpath("//input[@id='s2id_autogen8_search']"));
        sk.sendKeys("EIG-TT");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li[1]/div")));
        option.click();
    }

    public void NotifyToIRUwithAttachment(){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.EnotifyIRUToIRUAttachment))).click();
    }
    public void Attachments(String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
    }

    public void ImpactedCircuitAttachment(String filePath){
        String filEPath = System.getProperty("user.dir")+"/" + filePath;
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
    }

    public void Submit(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.submit))).click();
    }

    public void createEvent(  String modeOfRequest, String station,
                               String classification, String subClassification, String severity, String priority,
                               String urgency, String eventType, String category, String subjectInput,
                             String filePath) {

        modeOfRequest(modeOfRequest);
        station(station);
        reportedTime();
        classification(classification);
        subClassification(subClassification);
        severity(severity);
        periority(priority);
        urgency(urgency);
        eventType(eventType);
        categoryInput(category);
        subjectInput(subjectInput);
        notifyToIRU();
        linketTicket();
        NotifyToIRUwithAttachment();
        // Upload the files
        Attachments(filePath);
        ImpactedCircuitAttachment(filePath);
        Submit();
    }

}




