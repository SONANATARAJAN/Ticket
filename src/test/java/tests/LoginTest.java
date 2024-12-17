package tests;


import base.BaseTest;
import org.testng.annotations.Test;
import pages.Events;
import pages.LoginPage;
import pages.NewTicket;
import tickets.ProcessStage;
import tickets.TicketAction;
import tickets.TicketProcessStage;
import tickets.TicketTest;
import utils.XPathProvider;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void testValidLogin() throws InterruptedException {
        logger.info("**Starting TestCase 1....**");
        try {
            LoginPage loginPage = new LoginPage(driver);

            loginPage.enterUsername("nocuser1", XPathProvider.usernameXPath);
            loginPage.enterPassword("nms@123$", XPathProvider.passwordXPath);
            Thread.sleep(5000);
            loginPage.clickLogin(XPathProvider.loginButtonXPath);
            loginPage.troubleTicket( );

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            //{"Event"}{"Incident"}{"Problem"}{"Request Fulfillment"}{"Change"}
            String ticketType = "Event";

            TicketProcessStage ticketProcessStage=new TicketProcessStage(driver);
            ticketProcessStage.EventProcessStage(ticketType);
            //ticketProcessStage.RequestFulfilmentStage(ticketType);*/
            /*NewTicket ticket = new NewTicket(driver);
            ticket.newTicketClick(XPathProvider.newTicketXPath);

            TicketTest ticketTest = new TicketTest(driver);

            ticketTest.selectDropdownOption(ticketType);
            ticketTest.testTicketTypeSelection(ticketType);

            ProcessStage processStage=new ProcessStage(driver);
            processStage.Filter_TicketType(ticketType);
            processStage.Select_TTtoPickup();*/


        } catch (Exception e) {
            logger.error("An error occurred during the testValidLogin process.", e);
            throw new RuntimeException("Test case failed due to an exception.", e);
        }
    }
}

