package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
public class Change{
    private WebDriverWait wait;
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;

    public Change(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout
         this.webDriverManager = new utils.WebDriverManager(driver); // Create an instance

     }

    public  void waituntilpageload() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
    }

    public   void elementToBeClickable(String locatorvalue) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorvalue)));
        actions(element);
        try {
            if(!element.getText().isEmpty()) {
                System.out.println(element.getAccessibleName() + ": " + element.getText());
            }
        } catch (Exception e) {
            System.out.println("No text found");
        }
        waituntilpageload();
    }

    public   void actions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public   void selectByVisibleText(String locatorvalue, String value) throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
        Select dd =  new Select(dropdown);
        dd.selectByVisibleText(value);
        String ddtext = dd.getFirstSelectedOption().getText();
        System.out.println("Selected option: " + ddtext);
        Thread.sleep(500);
    }

    public   void selectByIndex(String locatorvalue, int value) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath(locatorvalue));
        Select dd =  new Select(dropdown);
        dd.selectByIndex(value);
        String ddtext = dd.getFirstSelectedOption().getText();
        System.out.println("Selected option: "+ ddtext);
        Thread.sleep(500);
    }

    public   void enterkeys(String locatorvalue) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
        element.sendKeys(Keys.ENTER);
        System.out.println(element.getAccessibleName() + ": " + element.isDisplayed());
        Thread.sleep(500);
    }

    public   void sendkeys(String locatorvalue, String value) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(locatorvalue));
        element.sendKeys(value);
        System.out.println(element.getAccessibleName() + ": " + value + " " + element.getAccessibleName());
        Thread.sleep(500);
    }
    public void Attachments_xpath(String locator,String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
        uploadElement.sendKeys(filEPath);
    }
    public static String ticketNumber; // Shared variable

    public   void classification_Subclassification(String class_text, int subclss_index) throws InterruptedException {
        List<WebElement> dropdown = driver.findElements(By.xpath("//select[@id='ChangeClassification']"));
        for (int i = 0; i < dropdown.size(); i++) {
            Select dd = new Select(dropdown.get(i));
            if (!dd.getOptions().isEmpty()) {
                dd.selectByVisibleText(class_text);
                    if (class_text == "Capacity Enhancement" || class_text == "Corrective Maintenance" ||
                            class_text == "DCN" || class_text == "Network Modification" || class_text == "Network Support System" ||
                            class_text == "NSS Server Related Activity" || class_text == "Others" || class_text == "Port Movement" ||
                            class_text == "Schedule Maintenance" || class_text == "Shifting of Traffic" || class_text == "Terrestrial Segment" ||
                            class_text == "UpGradation" || class_text == "Wet Segment") {
                            selectByIndex("//select[@id='AffectedStations']", 1);
                            selectByIndex("//select[@id='AffectedSections']", 1);
                            selectByIndex("//select[@id='AffectedWLs']", 1);
                            selectByIndex("//select[@id='NonCircuitEquipmentType']", 1);
                            selectByIndex("//select[@id='AffectedEquipments']", 1);
                            selectByIndex("//select[@id='AffectedCardTypes']", 1);
                            selectByIndex("//select[@id='AffectedCard']", 1);

            }

            Thread.sleep(500);
            List<WebElement> scdropdown = driver.findElements(By.xpath("//select[@id='ChangeSubClassification']"));
            for (int j = 0; j < scdropdown.size(); j++) {
                Select sc_dd = new Select(scdropdown.get(j));
                if (!sc_dd.getOptions().isEmpty()) {
                    sc_dd.selectByIndex(subclss_index);
                }
                Thread.sleep(500);
            }
        }
    }
}


public void changett() throws InterruptedException {
      selectByVisibleText("//select[@id='ChangemodeOfRequest']", "Email");
    selectByVisibleText("//select[@id='ChangeLocation']", "ABU TALAT");
    enterkeys("//input[@id='ReportedTime']");
    classification_Subclassification("Capacity Enhancement", 1);
        enterkeys("//input[@id='startTime1']");
        enterkeys("//input[@id='endTime1']");
        enterkeys("//input[@id='startTime2']");
        enterkeys("//input[@id='endTime2']");
        selectByIndex("//select[@id='TriggeredBy']", 1);
        selectByIndex("//select[@id='ImpactRisk']", 1);
        selectByIndex("//select[@id='ChangeImpact']", 1);
        sendkeys("//input[@id='ReasonforChange']", "Reason for change");
        selectByIndex("//select[@id='Priority']", 1);
        selectByIndex("//select[@id='Urgency']", 1);
        sendkeys("//input[@id='subject']", "Change Ticket for testing");
        sendkeys("//div[@class='note-editable']", "create change tt");
        Attachments_xpath("//input[@id='FallbackPlanAttachments']", "src/test/resources/upload_files/empty.xls");
        Attachments_xpath("//input[@id='SecurityImpactAttachments']", "src/test/resources/upload_files/empty.xls");
        Attachments_xpath("//input[@id='Attachments']", "src/test/resources/upload_files/empty.xls");
        Attachments_xpath("//input[@class='ImpactedCircuitAttachment']", "src/test/resources/upload_files/empty.xls");
      //  elementToBeClickable("//div[@id='s2id_LinkedTicket']");
     //   sendkeys("//input[@id='s2id_autogen8_search']", "EIG-TT");
        //elementToBeClickable("//ul[@class='select2-results']//li[1]/div");
        elementToBeClickable("//button[@id='Submit']");
       webDriverManager.captureTicketId();
}

}
