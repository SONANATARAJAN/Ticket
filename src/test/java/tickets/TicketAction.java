package tickets;

import org.openqa.selenium.WebDriver;
import pages.NewTicket;
import utils.XPathProvider;

public class TicketAction {
    public WebDriver driver;
    public TicketAction(WebDriver driver){
        this.driver=driver;
    }
     public void executeTicketProcess(String ticketType) throws InterruptedException {
         NewTicket ticket = new NewTicket(driver);
         ticket.newTicketClick(XPathProvider.newTicketXPath);

         TicketTest ticketTest = new TicketTest(driver);

         ticketTest.selectDropdownOption(ticketType);
         ticketTest.testTicketTypeSelection(ticketType);

         ProcessStage processStage=new ProcessStage(driver);
         processStage.Filter_TicketType(ticketType);
         processStage.Select_TTtoPickup();
     }
}
