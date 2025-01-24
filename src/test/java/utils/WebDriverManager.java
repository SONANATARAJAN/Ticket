package utils;

import base.BrowserManager;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.ChangeTTFlow;
import pages.RequestFulfilmentTTFlow;
import tickets.ProcessStage;
import tickets.TicketTest;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static pages.Change.ticketNumber;
import static pages.Problem.ProblemticketNumber;

public class WebDriverManager {
    private BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;
    public WebDriverWait wait;
    public static ExtentTest test;
    private final List<String> executionSteps; // To store dynamic execution details

    public WebDriverManager(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        executionSteps = new ArrayList<>();
    }
    //report generated
    public static void setTest(ExtentTest extentTest) {
        test = extentTest;
    }
    private void addStep(String stepDetail) {
        executionSteps.add(stepDetail);
    }
    public void generateSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("<b> Ticket Creation :</b><br><br>");
        int stepNumber = 1;
        for (String step : executionSteps) {
            summary.append(String.format("<b>%02d:</b> %s<br>", stepNumber++, step));
        }
        test.pass(summary.toString());
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
        test.pass(ticketNumber +"  Ticket Create Sucessfully");
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
    public void clickElement(String locator){
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator))).click();
    }
    public void submitTicket(){
        clickElement("//button[@id='Submit' and text()='Submit']");
    }
    public void resolveYes(){
        clickElement("//div[@id='resolutionStatus']//a[text()='Yes']");
    }
    public void resolved(){
    }


    //Request Fulfilment
    public void approvalRequest_Yes(){
        clickElement("//div[@id='approvalRequestCheckBox']//a[text()='Yes']");
        submitTicket();
    }
    public void validateRequestCheckBox_yes(){
        RequestFulfilmentTTFlow.addstep("Validate Request - Yes");
        clickElement("//div[@id='validateRequestCheckBox']//a[text()='Yes']");
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
         RequestFulfilmentTTFlow.addstep("Applicable Request - Yes");
         clickElement("//div[@id='applicableRequestCheckBox']//a[text()='Yes']");
         submitTicket();
         RequestFulfilmentTTFlow.addstep("Process Request");
         clickElement("//input[@id='processRequestCheckBox']");
         submitTicket();
         returnToTTpage();
         Thread.sleep(5000);
         RequestFulfilmentTTFlow.addstep("Close Ticket");
         sendkeys("//div[@class='note-editable']","Closed Ticket");
         submitTicket();
     }
    public void applicableReq_no() throws InterruptedException {
        Thread.sleep(5000);
        RequestFulfilmentTTFlow.addstep("Applicable Request - No");
        submitTicket();
        returnToTTpage();
        Thread.sleep(5000);
        RequestFulfilmentTTFlow.addstep("Close Ticket");
        sendkeys("//div[@class='note-editable']","Closed Ticket");
        submitTicket();
    }

    //change TT
    public void closeTT(){
        sendkeys("//div[@class='note-editable']","Closed Ticket");
        submitTicket();
    }
    public void appealonRejectedRFCtogglebox_yes(){
        clickElement("//div[@id='appealonRejectedRFCtogglebox']//a[text()='Yes']");
        submitTicket();
        clickElement("//input[@id='modifyRFCCheckBox']");
        submitTicket();
    }
    public void acceptEvaluatedRFCtogglebox_yes(){
        clickElement("//div[@id='acceptEvaluatedRFCtogglebox']//a[text()='Yes']");
        sendkeys("//textarea[@id='AssessComments']","Access Comment");
        submitTicket();
    }
    public void approveRFCtogglebox_yes(){
        clickElement("//div[@id='acceptEvaluatedRFCtogglebox']//a[text()='Yes']");
        sendkeys("//textarea[@id='AssessComments']","Access Comment");
        submitTicket();
    }
    public void returnToTTpage_C() throws InterruptedException {
        String ticketType ="Change";
        ProcessStage processStage=new ProcessStage(driver);
        Thread.sleep(4000);
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
    }
    public void returnToTTpage_P() throws InterruptedException {
        String ticketType ="Problem";
        ProcessStage processStage=new ProcessStage(driver);
        Thread.sleep(4000);
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
    }
 public void Submitted(){
        clickElement("//div[@id='acceptRFCReviewtogglebox']//a[@name='Submitted']");
        sendkeys("//textarea[@id='ReviewComments']","Accept RFC Review");
        submitTicket();
}
//problem TT flow
    public void investigationAndDiagnosisCheckBox(){
         clickElement("//input[@id='investigationAndDiagnosisCheckBox']");
         submitTicket();
    }
    public void workAroundinKEDBDtogglebox(){
         clickElement("//div[@id='workAroundinKEDBDtogglebox']//a[text()='Yes']");
         submitTicket();
    }
    public void KEDBcreation(){
         clickElement("//input[@id='definesWorkAroundCheckBox']");
         submitTicket();
         clickElement("//form[@id='keDBUpdationDetailProcessForm']//button[@id='Submit' and text()='Create KEDB' ]");
         sendkeys("//textarea[@id='problem']","Problem");
         sendkeys("//textarea[@id='reason']","reason");
         sendkeys("//textarea[@id='correctiveAction']","corrective action");
         sendkeys("//textarea[@id='keywords']","keywords");
         submitTicket();

         clickElement("//input[@id='keDBUpdationCheckBox']");
         submitTicket();
    }
    public void ChangeTT_in_ProblemTT() throws InterruptedException {
        //problem changes --yes
         clickElement("//div[@id='problemChangestogglebox']//a[text()='Yes']");
         submitTicket();
        //request for change
         clickElement("//input[@id='requestForChangeCheckBox']");
         submitTicket();

        TicketTest ticketTest = new TicketTest(driver);
        String ticketType ="Change";
        ticketTest.testTicketTypeSelection(ticketType);

         clickElement("//input[@id='problemResolutionCheckBox']");
         submitTicket();
        //return to TT page
         clickElement("//a[@id='id_OpenTickets']");
         returnToTTpage_C();

        ChangeTTFlow changeTTFlow=new ChangeTTFlow(driver);
        changeTTFlow.reject_RFC_no();
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
//

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
        actions.doubleClick(element).perform();
        //click Process
        Thread.sleep(6000);
        webDriverManager.clickMethod("//span[text()='Process']");
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


