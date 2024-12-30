package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.XPathProvider;
import java.time.Duration;
import java.util.Scanner;

public class Change {
    private WebDriverWait wait;
    public WebDriver driver;

    public Change (WebDriver driver){
     
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout

    }
    public void waituntilpageload() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
    }

    public void elementToBeClickable(String locatorvalue) {
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

    public void actions(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().build().perform();
    }

    public  void handledropdown(String selectlocatorvalue) throws InterruptedException {
        Select ttlist = new Select(driver.findElement(By.id(selectlocatorvalue)));
        for (WebElement list : ttlist.getOptions()) {
            System.out.println(list.getText());
            waituntilpageload();
        }
        waituntilpageload();
    }

    public  void verifySelectedDropdownValue(String locatorvalue, String value) throws InterruptedException {
        WebElement dropdownElement = driver.findElement(By.xpath(locatorvalue));
        Select dropdown = new Select(dropdownElement);
        String selectedValue = dropdown.getFirstSelectedOption().getText();
        if (selectedValue.equals(value)) {
            System.out.println("Selected option: " + selectedValue);
        }
        Thread.sleep(500);
    }

    public void verifyDropdownValueMatches(String locatorvalue, String ddvalue) throws InterruptedException {
        WebElement dropdownElement = driver.findElement(By.xpath(locatorvalue));
        String ddtext = dropdownElement.getText();
        if (ddtext.equals(ddvalue)) {
            System.out.println("Dropdown value matches username: "+ ddtext);
        }
        Thread.sleep(500);
    }

    public  void selectByVisibleText(String locatorvalue, String value) throws InterruptedException {
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locatorvalue)));
        Select dd =  new Select(dropdown);
        dd.selectByVisibleText(value);
        String ddtext = dd.getFirstSelectedOption().getText();
        System.out.println("Selected option: " + ddtext);
        Thread.sleep(500);
    }

    public void enterkeys(String locatorvalue) throws InterruptedException {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorvalue)));
        element.sendKeys(Keys.ENTER);
        System.out.println(element.getAccessibleName() + ": " + element.isDisplayed());
        Thread.sleep(500);
    }
    public void Attachments(String locator,String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(locator)));
        uploadElement.sendKeys(filEPath);
    }

    public  void sendkeys(String locatorvalue, String value) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath(locatorvalue));
        element.sendKeys(value);
        System.out.println(element.getAccessibleName() + ":" + value + " " + element.getAccessibleName());
        Thread.sleep(500);
    }


    public void createChange(String modeOfRequest,String Station,String Classification,String SubClassification,String AffectedParties,String AffectedCircuitList
    ,String triggeredBy,String imapactRisk,String ChangeImpact,String periority,String Urgency,
                                 String subject,String filePath) throws InterruptedException {


        selectByVisibleText("//select[@id='ChangemodeOfRequest']", modeOfRequest);
        selectByVisibleText("//select[@id='ChangeLocation']", Station);

        enterkeys("//input[@id='ReportedTime']");

        selectByVisibleText("//select[@id='ChangeClassification']", Classification);
        selectByVisibleText("//select[@id='ChangeSubClassification']", SubClassification);
//        selectByVisibleText("//select[@id='AffectedParties']", AffectedParties);
//        selectByVisibleText("//select[@id='AffectedCircuitList']", AffectedCircuitList);

        enterkeys("//input[@id='startTime1']");
        enterkeys("//input[@id='endTime1']");
        enterkeys("//input[@id='startTime2']");
        enterkeys("//input[@id='endTime2']");

        selectByVisibleText("//select[@id='TriggeredBy']",triggeredBy );
        selectByVisibleText("//select[@id='ImpactRisk']", imapactRisk);
        selectByVisibleText("//select[@id='ChangeImpact']", ChangeImpact);

        sendkeys("//input[@id='ReasonforChange']", "Reason for change");

        selectByVisibleText("//select[@id='Priority']", periority);
        selectByVisibleText("//select[@id='Urgency']",Urgency );

        sendkeys("//input[@id='subject']", subject);
        sendkeys("//div[@class='note-editable']",  subject);
Thread.sleep(3000);
        Attachments("//input[@id='TestPlanAttachments']", filePath);
        Thread.sleep(3000);

        Attachments("//input[@id='FallbackPlanAttachments']", filePath);
        Attachments("//input[@id='SecurityImpactAttachments']", filePath);
        Attachments("//input[@id='Attachments']", filePath);
        Attachments("//input[@class='ImpactedCircuitAttachment']", filePath);

        elementToBeClickable("//div[@id='s2id_LinkedTicket']");
        sendkeys("//input[@id='s2id_autogen8_search']", "EIG-TT");
        elementToBeClickable("//ul[@class='select2-results']//li[1]/div");

        elementToBeClickable("//button[@id='Submit']");

    }
}
