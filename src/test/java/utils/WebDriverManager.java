package utils;

import base.BrowserManager;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ChangeTTFlow;
import tickets.ProcessStage;
import tickets.TicketTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static pages.Change.ticketNumber;
import static pages.IncidentTTFlow.addstepI;
import static pages.Problem.ProblemticketNumber;
import static pages.RequestFulfilmentTTFlow.addstep;
import static pages.ChangeTTFlow.addstepC;
import static pages.ProblemTTFlow.addstepP;
import static pages.EventTTFlow.addstepE;

public class WebDriverManager {
    private BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;
    public WebDriverWait wait;
    public static ExtentTest test;
    private final List<String> executionSteps; // To store dynamic execution details

    public WebDriverManager(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(120));
        executionSteps = new ArrayList<>();
    }
    //report generated

    private void addStep(String stepDetail) {
        executionSteps.add(stepDetail);
    }
    public  void generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("<b> Ticket Creation :</b><br><br>");
        int stepNumber = 1;
        for (String step : executionSteps) {
            summary.append(String.format("<b>%02d:</b> %s<br>", stepNumber++, step));
        }
        ReportManager.pass(summary.toString());
    }
    public static void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted state
            System.out.println("Sleep interrupted: " + e.getMessage());
        }
    }
    //TT creation Process

    public void Attachments(String filePath) {
        String filEPath = System.getProperty("user.dir") + "/" + filePath;
        System.out.println(filEPath);
        WebElement uploadElement = wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.attachment)));
        uploadElement.sendKeys(filEPath);
    }

    public void dropDownSelect(String clickXpath, String Value) {
         clickMethod(clickXpath);
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(XPathProvider.commonDropSelect)));
        String criteria = Value;
        boolean optionFound = false;
        for (WebElement option : options) {
            String optionText = option.getText();
            if (optionText.equalsIgnoreCase(criteria)) {
                option.click();
                System.out.println("Select Option :"+optionText);
                addStep("Select Option :"+optionText);
                optionFound = true;
                break;
            }}
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }
    }

    public void selectByIndex(String locatorValue, int value)  {
        try {
            WebElement dropdown = driver.findElement(By.xpath(locatorValue));
            Select dd = new Select(dropdown);
            // Check if the dropdown has enough options to select
            if (dd.getOptions().size() > value) {
                dd.selectByIndex(value);
            } else {
                System.out.println("Option with index " + value + " is not available, proceeding to next step.");
            }
            // Find the label element associated with the dropdown
            WebElement label = driver.findElement(By.xpath(locatorValue + "/preceding-sibling::label"));
            // Check if the label contains a red-colored span with an asterisk (*)
            boolean isMandatory = label != null &&
                    label.findElement(By.xpath(".//span[@style='color:red']")) != null &&
                    label.getText().contains("*");
            // If field is mandatory and no selection is made, throw an exception
            if (isMandatory && dd.getFirstSelectedOption() == null) {
                throw new Exception("Mandatory field is not selected: " + locatorValue);
            }
            Thread.sleep(500);
        } catch (Exception e) {
            System.out.println("Dropdown element not found: " + locatorValue);
        }
    }


    public void keys(String locatorValue, String value) throws InterruptedException {
        WebElement input =  driver.findElement(By.xpath(locatorValue));
        input.sendKeys(value);
        Thread.sleep(500);
    }


    public void reportedTime(){
         visibleMethod((XPathProvider.reportedTime));
        Actions action=new Actions(driver);
        action.sendKeys(Keys.ENTER).perform();
    }
    public void subjectInput(String SubjectInput){
        WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(XPathProvider.subjectInput)));
        inputField.clear();
        inputField.sendKeys(SubjectInput);
        System.out.println("Value inputted: " + SubjectInput);
        addStep("Value inputted: " + SubjectInput);
    }
    public void linketTicket(){
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='s2id_LinkedTicket']")));
        ele.click();
        WebElement sk =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='s2id_autogen8_search']")));
        sk.sendKeys("EIG-TT");
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='select2-results']//li[1]/div")));
        option.click();
    }

    public void Submit(){
        wait.until(ExpectedConditions.elementToBeClickable(By.id(XPathProvider.submit))).click();

    }
    public void captureTicketId() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='content']//div[@class='message']")));
        String ticketId = alert.getText();
        ticketNumber = ticketId.split(":")[1].trim(); // Assign to static variable
        System.out.println("Full String: " + ticketId);
        System.out.println("Split String into Ticket ID: " + ticketNumber);
        ReportManager.pass(ticketNumber +"  Ticket Create Sucessfully");
    }
//Process flow

    public void clickMethod(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
     }
     public void visibleMethod(String locator){
      wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
     }
    public void sendkeys(String locator,String input){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).sendKeys(input);
    }

    //Ticket flow
    public void resolveYes(){
        clickElement("//div[@id='resolutionStatus']//a[text()='Yes']");
              }
    public void clickElement(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
    }
    public void submitTicket(){
        clickElement("//button[@id='Submit' and text()='Submit']");
    }



    //Request Fulfilment
    public void approvalRequest_Yes(){
        clickElement("//div[@id='approvalRequestCheckBox']//a[text()='Yes']");
        addstep(driver,"Approval Request - Yes",true);
        submitTicket();
    }
    public void validateRequestCheckBox_yes(){
        clickElement("//div[@id='validateRequestCheckBox']//a[text()='Yes']");
        addstep(driver,"Validate Request - Yes",true);
        submitTicket();

    }
    public void Logout() throws InterruptedException {
        Thread.sleep(4000);
        clickElement("//a[@id='userIcon']//label[@id='image-checkbox']/span");
        clickElement("//i[@class='fa fa-power-off ']");
        clickElement("//button[@id='Yes' and text()='Yes']");

    }
    public void returnToTTpage() throws InterruptedException {
        String ticketType ="Request Fulfillment";
        ProcessStage processStage=new ProcessStage(driver);
        Thread.sleep(4000);
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
    }
     public void applicableReq_yes() throws InterruptedException {
         clickElement("//div[@id='applicableRequestCheckBox']//a[text()='Yes']");
         addstep(driver,"Applicable Request - Yes",true);
         submitTicket();
         clickElement("//input[@id='processRequestCheckBox']");
         addstep(driver,"Process Request",true);
         submitTicket();
         returnToTTpage();
         Thread.sleep(5000);
         sendkeys("//div[@class='note-editable']","Closed Ticket");
         addstep(driver,"Close Ticket",true);
         submitTicket();
     }
    public void applicableReq_no() throws InterruptedException {
        Thread.sleep(5000);
        addstep(driver,"Applicable Request - No",true);
        submitTicket();
        returnToTTpage();
        Thread.sleep(5000);
        sendkeys("//div[@class='note-editable']","Closed Ticket");
        addstep(driver,"Close Ticket",true);
        submitTicket();
    }

    //change TT
    public void closeTT(){
        sendkeys("//div[@class='note-editable']","Closed Ticket");
        addstepC(driver,"Closed Ticket",true);
        addstepP(driver,"Ticket Closed",true);
        submitTicket();
    }
    public void appealonRejectedRFCtogglebox_yes(){
        clickElement("//div[@id='appealonRejectedRFCtogglebox']//a[text()='Yes']");
        addstepC(driver,"Appeal on Rejected RFC - Yes",true);
        submitTicket();
        clickElement("//input[@id='modifyRFCCheckBox']");
        addstepC(driver,"Modify RFC Detail",true);
        submitTicket();
    }
    public void acceptEvaluatedRFCtogglebox_yes(){
        clickElement("//div[@id='acceptEvaluatedRFCtogglebox']//a[text()='Yes']");
        sendkeys("//textarea[@id='AssessComments']","Access Comment");
        addstepC(driver,"Accept Evaluated RFC - Yes",true);
        submitTicket();
    }

    public void returnToTTpage_C() throws InterruptedException {
        String ticketType ="Change";
        ProcessStage processStage=new ProcessStage(driver);
        Thread.sleep(4000);
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
    }

 public void Submitted(){
        clickElement("//div[@id='acceptRFCReviewtogglebox']//a[@name='Submitted']");
        sendkeys("//textarea[@id='ReviewComments']","Accept RFC Review");
         addstepC(driver,"Accept RFC Review - Submitted",true);
        submitTicket();
}
    public void ProblemResolved() throws InterruptedException {
        //Problem Resolve
         clickElement(XPathProvider.problemResolve1);
         clickElement(XPathProvider.problemResolve2);
        addstepE(driver,"Problem Resolved for :"+ ticketNumber,true);
         submitTicket();
        Thread.sleep(5000);
         sendkeys("//div[@class='note-editable']","Closed Ticket");
        addstepE(driver,"Close Ticket",true);
         submitTicket();

    }
//Third party Ticket
public void TPreturnToTTpage() throws InterruptedException {
    Thread.sleep(5000);
    String ticketType ="Incident";
    ProcessStage processStage=new ProcessStage(driver);
    processStage.Filter_ParentTicketType(ticketType);
    processStage.Third_selectToPickup();
}
    public static String thirtparty;

public  void thirdTicketID() throws InterruptedException {
    Thread.sleep(4000);
    clickMethod("//a[@name='TroubleTicketDetails']");
    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@id, 'EIG-TT-G-') and contains(@class, 'cyg-keyValue-value')]")));
    String extractedValue = element.getText();
    thirtparty = (extractedValue);
    System.out.println("Third Party Ticket ID : "+thirtparty);
}
//Incident Ticket

    public void problemResolved(){
        clickElement(XPathProvider.problemResolve1);
        clickElement(XPathProvider.problemResolve2);
        addstepI(driver,"Problem Resolved",true);
        //submit
        submitTicket();
    }
    public void ResolutionVerification_resolveYes(){
        clickElement("//div[@id='resolutionStatus']//a[text()='Yes']");
        addstepI(driver,"Resolution Verification Status -Yes",true);
        submitTicket();
    }

    public void causeIdentifiedCheckBox(){
         clickElement ("//input[@id='causeIdentifiedCheckBox']");
         addstepI(driver,"Cause Identification",true);
         submitTicket();
    }
    public void resolved() throws InterruptedException{
        Thread.sleep(3000);
        clickElement("//div[@id='resolutionVerificationClosuretogglebox']//a[text()='Resolved']");
        addstepI(driver,"Resolution Verification Closure - resolved ",true);
        submitTicket();
    }
    public void ResolutionVerificationbyCustomer_Resolved(){
       clickElement("//div[@id='resolutionVerificationbyCustomerCheckBox']//a[text()='Resolved']");
       sendkeys("//div[@class='note-editable']","Resolution Verification by Customer -Ticket goes to close");
       addstepI(driver,"Resolution Verification by Customer - Resolved",true);
       submitTicket();
    }
    public void noc(){
         clickElement("//div[@id='incidentBoundarycheck']//a[text()='NOC']");
        addstepI(driver,"Incident Boundary Check - NOC",true);
         submitTicket();
    }
//problem TT flow
    public void investigationAndDiagnosisCheckBox(){
         clickElement("//input[@id='investigationAndDiagnosisCheckBox']");
         addstepP(driver,"Investigation & Diagnosis",true);
         submitTicket();
    }
    public void workAroundinKEDBDtogglebox(){
         clickElement("//div[@id='workAroundinKEDBDtogglebox']//a[text()='Yes']");
        addstepP(driver,"Work Around in KEDB - Yes",true);
        submitTicket();
    }
    public void KEDBcreation(){
         clickElement("//input[@id='definesWorkAroundCheckBox']");
        addstepP(driver,"Define WorkAround",true);
        submitTicket();
         clickElement("//form[@id='keDBUpdationDetailProcessForm']//button[@id='Submit' and text()='Create KEDB' ]");
         sendkeys("//textarea[@id='problem']","Problem");
         sendkeys("//textarea[@id='reason']","reason");
         sendkeys("//textarea[@id='correctiveAction']","corrective action");
         sendkeys("//textarea[@id='keywords']","keywords");
        addstepP(driver,"KEDB Updation - Create KEDB",true);
        submitTicket();

         clickElement("//input[@id='keDBUpdationCheckBox']");
         submitTicket();
    }
    public void ChangeTT_in_ProblemTT() throws InterruptedException {
        //problem changes --yes
        clickElement("//div[@id='problemChangestogglebox']//a[text()='Yes']");
        addstepP(driver,"Problem Changes - Yes",true);
        submitTicket();
        //request for change
         clickElement("//input[@id='requestForChangeCheckBox']");
        addstepP(driver,"Request For Change",true);
        submitTicket();
        TicketTest ticketTest = new TicketTest(driver);
        ReportManager.info("Change Ticket Creation for Problem Ticket");
        String ticketType ="Change";
        addstepP(driver,"Change Ticket Creation Stage :",false);
        ticketTest.testTicketTypeSelection(ticketType);

         clickElement("//input[@id='problemResolutionCheckBox']");
         submitTicket();
         //return to TT page
         clickElement("//a[@id='id_OpenTickets']");
         returnToTTpage_C();
        addstepP(driver,"<b>Start Change Ticket Flow </b>",false);
        ChangeTTFlow changeTTFlow=new ChangeTTFlow(driver);
        changeTTFlow.reject_RFC_no();
    }
    public void returnToTTpage_P() throws InterruptedException {
        Thread.sleep(4000);
        Filter_TicketType();
        Select_TTtoPickup();
    }

    public void Filter_TicketType() {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='tb_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_toolbar_item_w2ui-search-advanced']//span[@class='fa fa-filter']"))).click();
        WebElement sample = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_operator_2']")));
        Select drpDown = new Select(sample);
        drpDown.selectByValue("is");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_field_2']"))).sendKeys(ProblemticketNumber);
        webDriverManager.clickMethod("//button[text()=\"Search\"]");
    }

    public void Select_TTtoPickup() throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        Thread.sleep(5000);
        System.out.println("Problem Ticket Id : "+ProblemticketNumber);
        String dynamicXPath = String.format("//div[@title='%s']//span[text()='%s']", ProblemticketNumber, ProblemticketNumber);
        WebElement element = driver.findElement(By.xpath(dynamicXPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
        // Click on the "Pick Up" button
        webDriverManager.clickMethod("//button[text()='Pickup']");
        Thread.sleep(5000);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
        Actions actions = new Actions(driver);
        ((JavascriptExecutor) driver).executeScript("document.getElementById('super-ac-wrapper').style.display='none';");
        actions.doubleClick(element).perform();
        //click Process
        Thread.sleep(6000);
        WebElement processButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Process']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", processButton);

    }
    public  void selectByVisibleText(String locatorvalue, String value) throws InterruptedException {
        WebElement dropdown = driver.findElement(By.xpath(locatorvalue));
        Select dd =  new Select(dropdown);
        dd.selectByVisibleText(value);
        String ddtext = dd.getFirstSelectedOption().getText();
        System.out.println("Selected option: " + ddtext);
        Thread.sleep(500);


    }
    }


