package pages;

import base.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tickets.ProcessStage;
import tickets.TicketAction;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;

import static utils.Constants.*;

public class ChangeTTFlow {
    private final BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;

    public ChangeTTFlow(WebDriver driver) {
        this.driver=driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void performLogin(WebDriver driver, String username, String password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        Thread.sleep(5000);
        loginPage.clickLogin(XPathProvider.loginButtonXPath);
        loginPage.troubleTicket();
    }

    public void LoginNA() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
         Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        Thread.sleep(4000);
        //default no
        webDriverManager.sendkeys("//textarea[@id='AssessComments']","Access Comment");
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        //default no
        Thread.sleep(4000);
        webDriverManager.submitTicket();
         webDriverManager.Logout();
    }
    public void loginNOC() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage_C();
        webDriverManager.closeTT();
        //next stage----
        TicketAction ticketAction=new TicketAction(incognitoDriver);
        String ticketType ="Change";
        ticketAction.executeTicketProcess(ticketType);
        System.out.println("case 4:");
        webDriverManager.Submitted();
         webDriverManager.Logout();

    }
    public void LoginNA2() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        Thread.sleep(4000);
        //default no
        webDriverManager.sendkeys("//textarea[@id='AssessComments']","Access Comment");
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        //default no
        webDriverManager.appealonRejectedRFCtogglebox_yes();
         System.out.println("again it continue Accept RCC Review ");
        webDriverManager.Logout();
    }
    public void loginNOC2() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        //next stage--------------------
        TicketAction ticketAction=new TicketAction(incognitoDriver);
        String ticketType ="Change";
        ticketAction.executeTicketProcess(ticketType);
        webDriverManager.Submitted();
        Thread.sleep(4000);
         webDriverManager.Logout();
    }
    public void LoginNA3() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        Thread.sleep(4000);
         webDriverManager.acceptEvaluatedRFCtogglebox_yes();
         webDriverManager.sendkeys("//textarea[@id='ApprovalComments']","ApprovalComments");
         webDriverManager.submitTicket();
         webDriverManager.returnToTTpage_C();
         Thread.sleep(4000);
         //again handover to accept evaluated RFC
        webDriverManager.sendkeys("//textarea[@id='AssessComments']","Access Comment");
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        //default no
        webDriverManager.submitTicket();
         System.out.println("again it continue Accept RCC Review ");
        Thread.sleep(4000);
        webDriverManager.Logout();
    }
    public void LoginNA4() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        Thread.sleep(4000);
        webDriverManager.acceptEvaluatedRFCtogglebox_yes();
        //  ---//bug
        webDriverManager.clickElement("//div[@id='approveRFCtogglebox']//a[text()='Yes']");
        webDriverManager.submitTicket();
        Thread.sleep(4000);
        webDriverManager.Logout();

     }
    public void LoginNoc() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage_C();
    }

    public void reject_RFC_no() throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        System.out.println("case 1:");
        webDriverManager.sendkeys("//textarea[@id='ReviewComments']","Rejected RFC Review");
        //default no
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        //appeal on Rejected RFC
        Thread.sleep(4000);
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        webDriverManager.closeTT();
    }
    public void reject_RFC_yes_rejectRFCmodify_repeat() throws InterruptedException {
        System.out.println("case 2:");
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        webDriverManager.sendkeys("//textarea[@id='ReviewComments']","Rejected RFC Review");
        //default no
        webDriverManager.submitTicket();
        webDriverManager.returnToTTpage_C();
        webDriverManager.appealonRejectedRFCtogglebox_yes();
        System.out.println("Stage repeated as ACCEPT RFC REVIEW");
       //--stage repeated
    }
    public void submit_NA_acceptRFC_No_rejectRFC_no() throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        System.out.println("case 3:");
        webDriverManager.Submitted();
         webDriverManager.Logout();
        LoginNA();
        loginNOC(); //---case 4
    }
    //4
    public void submit_NA_acceptRFC_No_rejectRFC_yes() throws InterruptedException {
        LoginNA2();
        System.out.println("stage repeated in the ACCEPT RFC Review");
    }
    //5
    public void submit_NA_acceptRFC_yes_ApproveRFC_no() throws InterruptedException {
        System.out.println("case 5:");
        loginNOC2();
        LoginNA3();
    }
    public void submit_NA_acceptRFC_yes_ApproveRFC_yes() throws InterruptedException {
        System.out.println("case 6:");
        loginNOC2();
        LoginNA4();
        LoginNoc();
        //bug
    }

}
