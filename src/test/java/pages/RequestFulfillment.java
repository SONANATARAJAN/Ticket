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
    private WebDriverWait wait;
    public WebDriver driver;

    public RequestFulfillment(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(50)); // Set timeout
    }

    public void modeOfRequest(String ModeofRequest) {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickMethod(XPathProvider.RmodeOfRequest);
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

    public void station(String Station) {
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.RstationXpath))).click();
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
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.Rclassification))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath( XPathProvider.commonDropSelect)));
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
         wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.RsubClassificationXpath))).click();
         List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath( XPathProvider.commonDropSelect)));
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
    public void staionName(String StationName){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.RstationXpath))).click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = StationName;
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
    public void Submit(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id("Submit"))).click();
    }
    public void linketTicket(){
        WebElement ele = driver.findElement(By.xpath("//div[@id='s2id_LinkedTicket']"));
        ele.click();
        WebElement sk =  driver.findElement(By.xpath("//input[@id='s2id_autogen8_search']"));
        sk.sendKeys("EIG-TT");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li[1]/div")));
        option.click();
    }
    public void priority(String Priority) {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPathProvider.RperiorityXpath))).click();
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
        public void createRequestfulfiment (String modeOfRequest,String station,
                String classification, String SubClassification,String StationName,  String priority,
                String Subject,
                String filePath){

            modeOfRequest(modeOfRequest);
            station(station);
            reportedTime();
            classification(classification);
            subClassification(SubClassification);
            staionName(StationName);
            priority(priority);
            subject(Subject);
            //linketTicket(linketTicket);
            // Upload the files
            Attachments(filePath);
            linketTicket();
            Submit();
        }}

