package pages;
import base.BrowserManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import tickets.ProcessStage;
import tickets.TicketAction;
import utils.WebDriverManager;
import utils.XPathProvider;
import java.time.Duration;
public class RequestFulfilmentTTFlow  {
    private BrowserManager browserManager = new BrowserManager();
 //   public WebDriverManager webDriverManager=new WebDriverManager();
    private WebDriverWait wait;
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;

    public RequestFulfilmentTTFlow(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        this.webDriverManager = new utils.WebDriverManager(driver);

    }
    public void LoginNA() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
         normalDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
         normalDriver.get("https://192.168.4.96:10095/oss/login.action");
        normalDriver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(normalDriver);
        loginPage.enterUsername("nauser1");
        loginPage.enterPassword("nms@123$");
        Thread.sleep(5000);
        loginPage.clickLogin(XPathProvider.loginButtonXPath);
        loginPage.troubleTicket();
        //returnToTTpage();
        ProcessStage processStage=new ProcessStage(normalDriver);
        Thread.sleep(4000);
        String ticketType ="Request Fulfillment";
        processStage.Filter_TicketType(ticketType);
        processStage.Select_TTtoPickup();
         WebDriverManager webDriverManager=new WebDriverManager(normalDriver);
         webDriverManager.validateRequestCheckBox_yes();
        Thread.sleep(4000);
        webDriverManager.Logout();

    }

    public void loginNOC() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        incognitoDriver.get("https://192.168.4.96:10095/oss/login.action");
        incognitoDriver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(incognitoDriver);
        loginPage.enterUsername("nocuser1");
        loginPage.enterPassword("nms@123$");
        Thread.sleep(5000);
        loginPage.clickLogin(XPathProvider.loginButtonXPath);
        loginPage.troubleTicket();
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
        Thread.sleep(4000);
        webDriverManager.Logout();
        Thread.sleep(4000);

    }
    public void loginNOC2() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        incognitoDriver.get("https://192.168.4.96:10095/oss/login.action");
        incognitoDriver.manage().window().maximize();
        LoginPage loginPage = new LoginPage(incognitoDriver);
        loginPage.enterUsername("nocuser1");
        loginPage.enterPassword("nms@123$");
        Thread.sleep(5000);
        loginPage.clickLogin(XPathProvider.loginButtonXPath);
        loginPage.troubleTicket();
        //returnToTTpage
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage();
        Thread.sleep(5000);
        //
        webDriverManager.applicableReq_yes();

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

}

