package tests;

 import base.BrowserManager;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import tickets.TicketProcessStage;
import utils.XPathProvider;
import java.time.Duration;

 import static utils.Constants.*;

public class LoginTest   {
    private final BrowserManager browserManager = new BrowserManager();

    @Test
    public void testValidLogin() throws InterruptedException {
         try {
                 // Ensure the driver is initialized
             WebDriver incognitoDriver = browserManager.getIncognitoDriver();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
             WebDriverWait wait = new WebDriverWait(incognitoDriver, Duration.ofSeconds(50));

             incognitoDriver.get(BASE_URL);
             incognitoDriver.manage().window().maximize();
             LoginPage loginPage = new LoginPage(incognitoDriver);
             loginPage.enterUsername(nocUserName);
             loginPage.enterPassword(nocPwd);
             Thread.sleep(5000);
             loginPage.clickLogin(XPathProvider.loginButtonXPath);
             loginPage.troubleTicket();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //{"Event"}{"Incident"}{"Problem"}{"Request Fulfillment"}{"Change"}
            String ticketType = "Change";

              TicketProcessStage ticketProcessStage=new TicketProcessStage(incognitoDriver);
           // ticketProcessStage.EventProcessStage(ticketType);
           //  ticketProcessStage.RequestFulfilmentStage(ticketType);
           //  ticketProcessStage.IncidentProcessStage(ticketType);
               ticketProcessStage.ChangeStage(ticketType);
            // ticketProcessStage.ProblemStage(ticketType);



        } catch (Exception e) {
             throw new RuntimeException("Test case failed due to an exception.", e);
        }
    }
}

