package pages;

import base.BrowserManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tickets.TicketAction;
import tickets.TicketTest;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;

import static utils.Constants.*;

public class ProblemTTFlow {
    private final BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;
    private final utils.WebDriverManager webDriverManager;
    public ProblemTTFlow(WebDriver driver) {
        this.driver=driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        this.webDriverManager = new utils.WebDriverManager(driver);

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
    public void loginNa() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        webDriverManager.clickElement("//div[@id='resolutionApprovaltogglebox']//a[text()='Yes']");
        webDriverManager.submitTicket();
        Thread.sleep(5000);
        webDriverManager.Logout();
    }
    public void LoginNoc() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage_C();
        webDriverManager.clickElement("//input[@id='applyConfigurationCheckBox']");
        webDriverManager.submitTicket();
        Thread.sleep(5000);
        webDriverManager.Logout();
    }
    public void loginNa2() throws InterruptedException {
        WebDriver normalDriver = browserManager.getNormalDriver();
        performLogin(normalDriver, naUserName, naPwd);
        //returnToTTpage();
        Thread.sleep(4000);
        WebDriverManager webDriverManager = new WebDriverManager(normalDriver);
        webDriverManager.returnToTTpage_C();
        //final approve
        webDriverManager.clickElement("//a[@name='Approved' and text()='Approved']");
        webDriverManager.sendkeys("//textarea[@id='approvalComments']","approvalComments");
        webDriverManager.submitTicket();

        Thread.sleep(5000);
        webDriverManager.Logout();
    }
    public void LoginNoc2() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        webDriverManager.returnToTTpage_C();
        webDriverManager.closeTT();
        Thread.sleep(5000);
        webDriverManager.Logout();
    }
    public void createTT() throws InterruptedException {
        WebDriver incognitoDriver = browserManager.getIncognitoDriver();
        performLogin(incognitoDriver, nocUserName, nocPwd);
        //returnToTTpage();
        WebDriverManager webDriverManager = new WebDriverManager(incognitoDriver);
        //next stage--------------------
        TicketAction ticketAction=new TicketAction(incognitoDriver);
        String ticketType ="Change";
        ticketAction.executeTicketProcess(ticketType);

        Thread.sleep(4000);
        webDriverManager.Logout();
    }


    public void  KEDB_creation_yesChange() throws InterruptedException {
        webDriverManager.investigationAndDiagnosisCheckBox();
        webDriverManager.workAroundinKEDBDtogglebox();
//KEDB creation
        webDriverManager.KEDBcreation();
//change
        webDriverManager.ChangeTT_in_ProblemTT();
        //Problem Ticket opening
        webDriverManager.Filter_TicketType();
        webDriverManager.Select_TTtoPickup();
        //problem resolution
        webDriverManager.clickElement("//input[@id='problemResolutionCheckBox']");
        webDriverManager.submitTicket();  //--pending
        //
        Thread.sleep(4000);
        webDriverManager.Logout();
        loginNa();
        LoginNoc();
        loginNa2();
        LoginNoc2();
        //
    }

    public void KEDB_creation_nochange() throws InterruptedException{
        createTT();
        webDriverManager.investigationAndDiagnosisCheckBox();
        webDriverManager.workAroundinKEDBDtogglebox();
//KEDB creation
        webDriverManager.KEDBcreation();
        Thread.sleep(5000);
        webDriverManager.submitTicket();
        //problem resolution
        webDriverManager.clickElement("//input[@id='problemResolutionCheckBox']");
        webDriverManager.submitTicket();
        //
        Thread.sleep(4000);
        webDriverManager.Logout();
        loginNa();
        LoginNoc();
        loginNa2();
        LoginNoc2();
        //
    }
    public void  workAroundNO_yesChange() throws InterruptedException {
        createTT();
        webDriverManager.investigationAndDiagnosisCheckBox();
        Thread.sleep(4000);
        webDriverManager.submitTicket();
  //change
        webDriverManager.ChangeTT_in_ProblemTT();
        //Problem Ticket opening
        webDriverManager.Filter_TicketType();
        webDriverManager.Select_TTtoPickup();
        //problem resolution
        webDriverManager.clickElement("//input[@id='problemResolutionCheckBox']");
        webDriverManager.submitTicket();  //--pending
        //
        Thread.sleep(4000);
        webDriverManager.Logout();
        loginNa();
        LoginNoc();
        loginNa2();
        LoginNoc2();
        //
    }
    public void workAroundNO_nochange() throws InterruptedException{
        createTT();
        webDriverManager.investigationAndDiagnosisCheckBox();
        Thread.sleep(4000);
        webDriverManager.submitTicket();

        //problem resolution
        webDriverManager.clickElement("//input[@id='problemResolutionCheckBox']");
        webDriverManager.submitTicket();
        //
        Thread.sleep(4000);
        webDriverManager.Logout();
        loginNa();
        LoginNoc();
        loginNa2();
        LoginNoc2();
        //
    }
}
