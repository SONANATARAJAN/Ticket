package tickets;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.WebDriver;
import pages.EventTTFlow;
import pages.IncidentTTFlow;
import pages.RequestFulfilmentTTFlow;

public class TicketProcessStage {
    private final WebDriver driver;

    public TicketProcessStage(WebDriver driver) {
        this.driver = driver;
    }
    public void EventProcessStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        ticketAction.executeTicketProcess(ticketType);
        EventTTFlow eventTTFlow=new EventTTFlow(driver);
        //1
        eventTTFlow.resoluationVeriFyStatus_yes();
        Thread.sleep(5000);
        //2
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_noc_yes();
        //3
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_noc_no_withoutV();
        //4
        //ticketAction.executeTicketProcess(ticketType);
        //eventTTFlow.resoluationVeriFyStatus_no_noc_no_withV();     //bug
        //5
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_others_without();
        //6
        //ticketAction.executeTicketProcess(ticketType);
        // eventTTFlow.resoluationVeriFyStatus_no_others_with();   //bug
    }
    public void IncidentProcessStage(String ticketType) throws InterruptedException{
        IncidentTTFlow incidentTTFlow=new IncidentTTFlow(driver);
        TicketAction ticketAction=new TicketAction(driver);
        //1
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_yes_resolve();
        //2
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_yes_notResolve();
        //3
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_yes_resolve();
        //4
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_yes_notResolve();
        //5
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_no_withoutV_resolved();
        //6
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_no_withoutV_notResolved();
        //7
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_no_withV();
        //8
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_others_withoutTP_resolved();
        //9
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_others_withoutTP_notResolved();
    }
    public void RequestFulfilmentStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        RequestFulfilmentTTFlow requestFulfilmentTTFlow=new RequestFulfilmentTTFlow(driver);
        //1
        ticketAction.executeTicketProcess(ticketType);
        requestFulfilmentTTFlow.AprovalReq_yes();


    }
}
