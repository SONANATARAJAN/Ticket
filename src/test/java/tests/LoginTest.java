package tests;

 import base.BrowserManager;
 import com.aventstack.extentreports.ExtentReports;
 import com.aventstack.extentreports.ExtentTest;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
 import pages.*;
 import tickets.TicketProcessStage;
  import utils.ReportManager;
 import utils.WebDriverManager;
 import utils.XPathProvider;
 import java.time.Duration;
 import static utils.Constants.*;

public class LoginTest   {
    private final BrowserManager browserManager = new BrowserManager();

    @Test
    public void testValidLogin()  {
        ExtentReports extent = ReportManager.getExtentReports();
        ExtentTest test = extent.createTest("Test Case for Ticket Creation and Process Flow");

         try {
               WebDriver incognitoDriver = browserManager.getIncognitoDriver();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
             WebDriverWait wait = new WebDriverWait(incognitoDriver, Duration.ofSeconds(50));
             test.info("ChromeDriver initialized");
             test.info("<b>Configuration Details:</b> Browser: Chrome<br>URL: "+ BASE_URL);
             incognitoDriver.get(BASE_URL);
              incognitoDriver.manage().window().maximize();
             LoginPage loginPage = new LoginPage(incognitoDriver);
             loginPage.setTest(test);
             loginPage.enterUsername(nocUserName);
             loginPage.enterPassword(nocPwd);
             Thread.sleep(5000);
             loginPage.clickLogin(XPathProvider.loginButtonXPath);
             loginPage.troubleTicket();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
          //Multiple Ticket Creation
           /*  for(int i=0;i<=4;i++) {
                 System.out.println("count of"+i);
                 List<String> ticketTypes = Arrays.asList("Event", "Incident", "Problem", "Request Fulfillment", "Change");
                 for (String ticketType : ticketTypes) {
                     NewTicket ticket = new NewTicket(incognitoDriver);
                     ticket.newTicketClick(XPathProvider.newTicketXPath);
                     TicketTest ticketTest = new TicketTest(incognitoDriver);
                     ticketTest.selectDropdownOption(ticketType);
                     ticketTest.testTicketTypeSelection(ticketType);
                 }
             }
*/

             //{"Event"}{"Incident"}{"Problem"}{"Request Fulfillment"}{"Change"}
              String ticketType = "Problem";
            TicketProcessStage ticketProcessStage=new TicketProcessStage(incognitoDriver);
             WebDriverManager.setTest(test);
             EventTTFlow eventTTFlow=new EventTTFlow(incognitoDriver);
             eventTTFlow.setTest(test);
             RequestFulfilmentTTFlow requestFulfilmentTTFlow=new RequestFulfilmentTTFlow(incognitoDriver);
             requestFulfilmentTTFlow.setTest(test);

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
                     Change change=new Change(incognitoDriver);
                     change.setTest(test);
                     ticketProcessStage.ChangeStage(ticketType);
                     break;
                 default:
                     System.out.println("Invalid ticket type");
                     break;
             }
             extent.flush();


        } catch (Exception e) {
             throw new RuntimeException("Test case failed due to an exception.", e);

        }

    }
}

