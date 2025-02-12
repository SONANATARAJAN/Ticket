package tickets;

import org.openqa.selenium.WebDriver;
import pages.*;

public class TicketProcessStage {
    private final WebDriver driver;

    public TicketProcessStage(WebDriver driver) {
        this.driver = driver;
    }
    public void EventProcessStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        EventTTFlow eventTTFlow=new EventTTFlow(driver);
        //1
        System.out.println("case 1");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_yes();
        //2
        System.out.println("case 2");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_noc_yes();
        //3
        System.out.println("case 3");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_noc_no_withoutV();
        //4
        System.out.println("case 4");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_noc_no_withV();
        //5
        System.out.println("case 5");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_others_without();
//        //6
        System.out.println("case 6");
        ticketAction.executeTicketProcess(ticketType);
        eventTTFlow.resoluationVeriFyStatus_no_others_with();

        System.out.println("case 7");
        eventTTFlow.resoluationVeriFyStatus_no_others_with_MLO();
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
         incidentTTFlow.resolutionverify_no_noc_yes_resolve();
        //4
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_yes_notResolve();
        //5
         incidentTTFlow.resolutionverify_no_noc_no_withoutV_resolved();
        //6
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_no_withoutV_notResolved();
        //7
         incidentTTFlow.resolutionverify_yes_resolve_closure_notResolved();
        //8
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_others_withoutTP_resolved();
        //8.1
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_noc_no_withV();
        //9
        ticketAction.executeTicketProcess(ticketType);
        incidentTTFlow.resolutionverify_no_others_withoutTP_notResolved();
        //10
        incidentTTFlow.resolutionverify_no_others_withTP_notResolved();
        //11
        incidentTTFlow.resolutionverify_no_others_withTP_MLO();
    }
    public void RequestFulfilmentStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        RequestFulfilmentTTFlow requestFulfilmentTTFlow=new RequestFulfilmentTTFlow(driver);
        //1
        ticketAction.executeTicketProcess(ticketType);
        requestFulfilmentTTFlow.AprovalReq_no_applicable_yes();
        //2
       ticketAction.executeTicketProcess(ticketType);
        requestFulfilmentTTFlow.AprovalReq_no_applicable_no();
        //3
         ticketAction.executeTicketProcess(ticketType);
         requestFulfilmentTTFlow.AprovalReq_yes_validReq_yes_applicableReq_no();
        //4
        requestFulfilmentTTFlow.AprovalReq_yes_validReq_yes_applicableReq_processReq();
         //5
        requestFulfilmentTTFlow.AprovalReq_yes_validReq_no_closed();
    }

    public void ChangeStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        ChangeTTFlow changeTTFlow=new ChangeTTFlow(driver);
        //1
        ticketAction.executeTicketProcess(ticketType);
        changeTTFlow.reject_RFC_no();
      //2
        ticketAction.executeTicketProcess(ticketType);
        changeTTFlow.reject_RFC_yes_rejectRFCmodify_repeat();
        //3
        ticketAction.executeTicketProcess(ticketType);
        changeTTFlow.submit_NA_acceptRFC_No_rejectRFC_no();
        //4
        changeTTFlow.submit_NA_acceptRFC_No_rejectRFC_yes();
        //5
        changeTTFlow.submit_NA_acceptRFC_yes_ApproveRFC_no();
        //6
      //  changeTTFlow.submit_NA_acceptRFC_yes_ApproveRFC_yes();

    }
    public void ProblemStage(String ticketType) throws InterruptedException {
        TicketAction ticketAction=new TicketAction(driver);
        ProblemTTFlow problemTTFlow =new ProblemTTFlow(driver);
        //1
         ticketAction.executeTicketProcess(ticketType);
          problemTTFlow.KEDB_creation_yesChange();
        //2
      /*   problemTTFlow.KEDB_creation_nochange();
        //3
        problemTTFlow.workAroundNO_yesChange();
        //4
        problemTTFlow.workAroundNO_nochange();
        //5
        problemTTFlow.workAroundNo_noChange_ResolutionApproveNo();*/




    }
}
