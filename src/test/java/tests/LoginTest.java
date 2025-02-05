package tests;

 import base.BrowserManager;
 import com.aventstack.extentreports.ExtentReports;
 import com.aventstack.extentreports.ExtentTest;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
 import pages.*;
 import tickets.TicketProcessStage;
 import tickets.TicketTest;
 import utils.ReportManager;
 import utils.WebDriverManager;
 import utils.XPathProvider;
 import java.time.Duration;
 import java.util.Arrays;
 import java.util.List;
 import static utils.Constants.*;

public class LoginTest   {
    private final BrowserManager browserManager = new BrowserManager();
    public WebDriver driver;
    @Test
    public void testValidLogin() throws InterruptedException {
        ExtentReports extent = ReportManager.getExtentReports();
        ExtentTest test = extent.createTest("Test Case for Ticket Creation and Process Flow");
        ReportManager.setTest(test);
         try {
             WebDriver incognitoDriver = browserManager.getIncognitoDriver();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
             WebDriverWait wait = new WebDriverWait(incognitoDriver, Duration.ofSeconds(50));
             ReportManager.info("ChromeDriver initialized");
             ReportManager.info("<b>Configuration Details:</b> Browser: Chrome<br>URL: "+ BASE_URL);
             incognitoDriver.get(BASE_URL);
             incognitoDriver.manage().window().maximize();
             LoginPage loginPage = new LoginPage(incognitoDriver);
             loginPage.enterUsername(nocUserName);
             loginPage.enterPassword(nocPwd);
             Thread.sleep(5000);
             loginPage.clickLogin(XPathProvider.loginButtonXPath);
             loginPage.troubleTicket();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       /*   //Multiple Ticket Creation
             for(int i=0;i<=1;i++) {
                 System.out.println("count of : "+i);
                 List<String> ticketTypes = Arrays.asList("Event", "Incident", "Problem", "Request Fulfillment", "Change");
                 for (String ticketType : ticketTypes) {
                     NewTicket ticket = new NewTicket(incognitoDriver);
                     ticket.newTicketClick(XPathProvider.newTicketXPath);
                     TicketTest ticketTest = new TicketTest(incognitoDriver);
                     ticketTest.selectDropdownOption(ticketType);
                     ticketTest.testTicketTypeSelection(ticketType);}}*/

             //{"Event"}{"Incident"}{"Problem"}{"Request Fulfillment"}{"Change"}
             String ticketType = "Incident";
             TicketProcessStage ticketProcessStage=new TicketProcessStage(incognitoDriver);

             switch(ticketType) {
                 case "Event":
                     ticketProcessStage.EventProcessStage(ticketType);
                     break;
                 case "Incident":
                     ticketProcessStage.IncidentProcessStage(ticketType);
                     break;
                 case "Problem":
                     ticketProcessStage.ProblemStage(ticketType);
                     break;
                 case "Request Fulfillment":
                     ticketProcessStage.RequestFulfilmentStage(ticketType);
                     break;
                 case "Change":
                     ticketProcessStage.ChangeStage(ticketType);
                     break;
                 default:
                     System.out.println("Invalid ticket type");
                     break;
             }
         } catch (Exception e) {
             ReportManager.logException(e);
             ReportManager.captureScreenshot(driver,"Error");
             throw new RuntimeException("Test case failed due to an exception.", e);

        }finally {
             browserManager.closeAllBrowsers();
             extent.flush();
         }
    }
}

