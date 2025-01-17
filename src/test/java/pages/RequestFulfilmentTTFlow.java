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

public class RequestFulfilmentTTFlow  {

    private final BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;

    public RequestFulfilmentTTFlow(WebDriver driver) {
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
        ProcessStage processStage=new ProcessStage(normalDriver);
        Thread.sleep(4000);
        String ticketType ="Request Fulfillment";
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
         WebDriverManager webDriverManager=new WebDriverManager(normalDriver);
         webDriverManager.validateRequestCheckBox_yes();
         webDriverManager.Logout();

    }

    public void loginNOC() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager=new WebDriverManager(incognitoDriver);
       webDriverManager.returnToTTpage();
       webDriverManager.applicableReq_no();
       //create another ticket
        //repeat next step process
        System.out.println("case 4-----------");
        TicketAction ticketAction=new TicketAction(incognitoDriver);
        String ticketType ="Request Fulfillment";
        ticketAction.executeTicketProcess(ticketType);
        webDriverManager.approvalRequest_Yes();
        //move to NA user
         webDriverManager.Logout();

    }
    public void loginNOC2() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage();
        Thread.sleep(5000);
        //
        webDriverManager.applicableReq_yes();
        //
        System.out.println("case 5-----------");
        TicketAction ticketAction=new TicketAction(incognitoDriver);
        String ticketType ="Request Fulfillment";
        ticketAction.executeTicketProcess(ticketType);
        webDriverManager.approvalRequest_Yes();
        //move to NA user
         webDriverManager.Logout();
     }
    public void LoginNA1() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        ProcessStage processStage=new ProcessStage(normalDriver);
        Thread.sleep(4000);
        String ticketType ="Request Fulfillment";
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
        WebDriverManager webDriverManager=new WebDriverManager(normalDriver);
        Thread.sleep(4000);
        webDriverManager.submitTicket();
         webDriverManager.Logout();
    }
    public void loginNOC3() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage();
         Thread.sleep(5000);
        webDriverManager.sendkeys("//div[@class='note-editable']","Closed Ticket");
        webDriverManager.submitTicket();
        //

    }

    //1
    public void AprovalReq_no_applicable_yes() throws InterruptedException {
        System.out.println("1 case");
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        webDriverManager.submitTicket();
        webDriverManager.applicableReq_yes();
        //move to NA user
    }
    //2
    public void AprovalReq_no_applicable_no() throws InterruptedException {
        System.out.println("case 2");
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        webDriverManager.submitTicket();
        webDriverManager.applicableReq_no();
    }

    //3
    public void AprovalReq_yes_validReq_yes_applicableReq_no() throws InterruptedException {
        System.out.println("case 3");
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        webDriverManager.approvalRequest_Yes();
        //move to NA user
        Thread.sleep(4000);
        webDriverManager.Logout();
        //String ticketType="Request Fulfillment";
        Thread.sleep(4000);
        //Login to NA USER
        LoginNA();
        //Login NOC user
        loginNOC();  //--2nd continue
    }
    //4
    public void AprovalReq_yes_validReq_yes_applicableReq_processReq() throws InterruptedException {
        //Login to NA USER
        LoginNA();
        //Login NOC user
        Thread.sleep(5000);
        loginNOC2();  //-3rd continue
    }
    //5
    public void AprovalReq_yes_validReq_no_closed() throws InterruptedException {
        LoginNA1();
        loginNOC3();
    }


}

