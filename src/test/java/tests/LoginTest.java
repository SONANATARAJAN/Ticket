package tests;

 import base.BrowserManager;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.LoginPage;
import tickets.TicketProcessStage;
import utils.XPathProvider;
import java.time.Duration;

public class LoginTest   {
    private final BrowserManager browserManager = new BrowserManager();

    @Test
    public void testValidLogin() throws InterruptedException {
         try {
                 // Ensure the driver is initialized
             WebDriver incognitoDriver = browserManager.getIncognitoDriver();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
             WebDriverWait wait = new WebDriverWait(incognitoDriver, Duration.ofSeconds(50));

             incognitoDriver.get("https://192.168.4.96:10095/oss/login.action");
             incognitoDriver.manage().window().maximize();
             LoginPage loginPage = new LoginPage(incognitoDriver);
             loginPage.enterUsername("nocuser1");
             loginPage.enterPassword("nms@123$");
             Thread.sleep(5000);
             loginPage.clickLogin(XPathProvider.loginButtonXPath);
             loginPage.troubleTicket();
             incognitoDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //{"Event"}{"Incident"}{"Problem"}{"Request Fulfillment"}{"Change"}
            String ticketType = "Request Fulfillment";

              TicketProcessStage ticketProcessStage=new TicketProcessStage(incognitoDriver);
           // ticketProcessStage.EventProcessStage(ticketType);
             ticketProcessStage.RequestFulfilmentStage(ticketType);
           //  ticketProcessStage.IncidentProcessStage(ticketType);



        } catch (Exception e) {
             throw new RuntimeException("Test case failed due to an exception.", e);
        }
    }
}

