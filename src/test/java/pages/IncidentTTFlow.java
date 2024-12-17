package pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tickets.ProcessStage;
import utils.WebDriverManager;
import utils.XPathProvider;
import java.time.Duration;




public class IncidentTTFlow {
    private  WebDriverWait wait;
    public WebDriver driver;

    public IncidentTTFlow(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void returnToTTpage() throws InterruptedException {
        String ticketType ="Incident";
        ProcessStage processStage=new ProcessStage(driver);
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
    }
    public void resolutionverify_yes() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement ("//input[@id='causeIdentifiedCheckBox']");
        webDriverManager.submitTicket();
        webDriverManager.resolveYes();
        webDriverManager.submitTicket();
        //Problem Resolve
       webDriverManager.clickElement(XPathProvider.problemResolve1);
       webDriverManager.clickElement(XPathProvider.problemResolve2);
        //submit
        webDriverManager.submitTicket();
        //close Ticket
        Thread.sleep(5000);
        //return TT page
        returnToTTpage();
    }
    //1
    public void resolutionverify_yes_resolve() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        resolutionverify_yes();
        //resolution verification status closure
        //resolved
        webDriverManager.resolved();
        webDriverManager.submitTicket();
    }
    //2
    public void resolutionverify_yes_notResolve() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        resolutionverify_yes();
        //Notresolved Default
        webDriverManager.submitTicket();
        //return TT page
        returnToTTpage();
        //again the steps repeat

    }
    //3
    public void resolutionverify_no_noc_yes_resolve() throws InterruptedException {
        //continue with above method "resolutionverify_yes_notResolve" donot craete new tt
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement( ("//input[@id='causeIdentifiedCheckBox']"));
        webDriverManager.submitTicket();
        //no -default
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //select NOC
    //    webDriverManager.clickElement( (XPathProvider.nocSelectXpath));
        webDriverManager.submitTicket();
        webDriverManager.resolveYes();

        //stage repeated
        resolutionverify_yes_resolve();
    }
    //4
    public void resolutionverify_no_noc_yes_notResolve() throws InterruptedException{
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement( "//input[@id='causeIdentifiedCheckBox']");
        webDriverManager.submitTicket();
        //no -default
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //select NOC
    //    webDriverManager.clickElement (XPathProvider.nocSelectXpath);
        webDriverManager.submitTicket();
        Thread.sleep(3000);
        //stage repeated
        resolutionverify_yes_notResolve();
    }
    //call
    public void resolutionverify_no_noc_no_withoutV() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement( ("//input[@id='causeIdentifiedCheckBox']"));
        webDriverManager.submitTicket();
        //no -default
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //select NOC
    //    webDriverManager.clickElement( (XPathProvider.nocSelectXpath));
        webDriverManager.submitTicket();
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //without refer vendor
        webDriverManager.clickElement( ("//input[@id='supplierInvolmentCheckBox']"));
        //Problem Resolve
    //    webDriverManager.clickElement( (XPathProvider.problemResolveCheck1Xpath));
    //    webDriverManager.clickElement( (XPathProvider.getProblemResolveCheck2Xpath));
        webDriverManager.submitTicket();
        returnToTTpage();
    }
    //5
    public void resolutionverify_no_noc_no_withoutV_resolved() throws InterruptedException{
        resolutionverify_no_noc_no_withoutV();
        //resolved
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.resolved();
        webDriverManager.submitTicket();
    }
    //6
    public void resolutionverify_no_noc_no_withoutV_notResolved()throws InterruptedException{
        resolutionverify_no_noc_no_withoutV();
        //default notresolved
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.submitTicket();
    }
    //7
    public void resolutionverify_no_noc_no_withV() throws InterruptedException{
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement( ("//input[@id='causeIdentifiedCheckBox']"));
        webDriverManager.submitTicket();
        //no -default
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //select NOC
    //    webDriverManager.clickElement( (XPathProvider.nocSelectXpath));
        webDriverManager.submitTicket();
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //with vendor
        webDriverManager.clickElement( ("//button[@id='ReferVendor']"));
        //---
    }
    //call
    public void resolutionverify_no_others_withoutTP() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement( ("//input[@id='causeIdentifiedCheckBox']"));
        webDriverManager.submitTicket();
        //no -default
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        Thread.sleep(3000);
        webDriverManager.submitTicket();
        //without third party
        webDriverManager.clickElement( ("//input[@id='thirdPartyInvolmentCheckBox']"));
        webDriverManager.submitTicket();
        //Problem Resolve
//        webDriverManager.clickElement( (XPathProvider.problemResolveCheck1Xpath));
//        webDriverManager.clickElement( (XPathProvider.getProblemResolveCheck2Xpath));
        webDriverManager.submitTicket();
    }
    //8
    public void resolutionverify_no_others_withoutTP_resolved() throws InterruptedException {
        resolutionverify_no_others_withoutTP();
        //resolved
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.resolved();

    }
    //9
    public void resolutionverify_no_others_withoutTP_notResolved() throws InterruptedException {
        resolutionverify_no_others_withoutTP();
        //---



    }
}
