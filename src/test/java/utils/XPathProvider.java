package utils;

public class XPathProvider {
    public static final String usernameXPath = "//input[@id='username']";
    public static final String passwordXPath = "//input[@id='password']";
    public static final String forceLogoutXPath = "forcelogout";
    public static final String loginButtonXPath = "//button[@class='btn btn-default']";
    public static final String troubleTicketXPath = "//div[text()='TT']";
    public static final String openTicketsXPath = "//div[@id='wd_floatingMenuCtnr']//a//span[text()='Open Tickets']";

    // XPaths as variables (you can modify or expand as needed)
    public static final String newTicketXPath = "//div[@class='ui icon buttons user-catalog-control']//button[text()='New Ticket']//i";
    public static final String dropdownXPath = "//div[@id='s2id_TypeofTicket']//a//span[text()='------Select------']";
    public static final String eventXPath = "//*[@id='select2-result-label-3']";

    //common
    public static final String commonDropSelect="//ul[@class='select2-results']/li";
    public static final String reportedTime="//input[@id='ReportedTime']";
    public static final String subjectInput="subject";
    public static final String attachment="Attachments";
    public static final String submit="Submit";


    //Events
    public static final String EmodeOfRequest="//div[@id='s2id_EventmodeOfRequest']/a";
    public static final String EstationXpath="//div[@id='s2id_EventLocation']/a";
    public static final String Eclassification="//div[@id='s2id_EventClassification']/a";
    public static final String EsubClassificationXpath="//div[@id='s2id_EventSubClassification']/a";
    public static final String Esevrity="//div[@id='s2id_Severity']/a";
    public static final String EperiorityXpath="//div[@id='s2id_Priority']/a";
    public static final String Eurgencyxpath="//div[@id='s2id_Urgency']/a";
    public static final String EeventTypexpath="//div[@id='s2id_EventType']/a";
    public static final String EcategoryXpath="//div[@id='s2id_category']/a";
    public static final String notifyToIRU="//div[@id='NotifyToIRU']//a[@name='Yes' and text()='Yes']";
    public static final String EnotifyIRUToIRUAttachment="//div[@id='NotifyToIRUwithAttachment']//a[@name='Yes' and text()='Yes']";
    public static final String EimpactCircuitAttachment="//*[@id=\"ImpactedCircuit\"]/div[2]/div/div[2]/input";

    //Incident
    public static final String ImodeOfRequest="//div[@id='s2id_IncidentmodeOfRequest']/a";
    public static final String IstationXpath="//div[@id='s2id_IncidentLocation']/a";
    public static final String Iclassification="//div[@id='s2id_IncidentClassification']/a";
    public static final String IsubClassificationXpath="//div[@id='s2id_IncidentSubClassification']/a";
    public static final String IpartyName="//div[@id='s2id_partyName']/a";
    public static final String Icircuit="//div[@id='s2id_circuit']/a";
    public static final String Isevrity="//div[@id='s2id_Severity']/a";
    public static final String IperiorityXpath="//div[@id='s2id_Priority']/a";
    public static final String Iurgencyxpath="//div[@id='s2id_Urgency']/a";
    public static final String IeventTypexpath="//div[@id='s2id_EventType']/a";
    public static final String IcategoryXpath="//div[@id='s2id_category']/a";

    //Problem
    public static final String PmodeOfRequest="//div[@id='s2id_ProblemmodeOfRequest']/a";
    public static final String PstationXpath="//div[@id='s2id_ProblemLocation']/a";
    public static final String Pclassification="//div[@id='s2id_ProblemClassification']/a";
    public static final String PsubClassificationXpath="//div[@id='s2id_ProblemSubClassification']/a";
    public static final String Psevrity="//div[@id='s2id_Severity']/a";
    public static final String PperiorityXpath="//div[@id='s2id_Priority']/a";
    public static final String Purgencyxpath="//div[@id='s2id_Urgency']/a";

    //RequestFulfiment
    public static final String RmodeOfRequest="//div[@id='s2id_RequestFulfillmentmodeOfRequest']/a";
    public static final String RstationXpath="//div[@id='s2id_RequestFulfillmentLocation']/a";
    public static final String Rclassification="//div[@id='s2id_RequestFulfillmentClassification']/a";
    public static final String RsubClassificationXpath="//div[@id='s2id_RequestFulfillmentSubClassification']/a";
    public static final String RstionName="//div[@id='s2id_RequestFulFillmentstationName']/a";
    public static final String RperiorityXpath="//div[@id='s2id_Priority']/a";
    public static final String Rurgencyxpath="//div[@id='s2id_Urgency']/a";
    public static final String causeIdentificationXpath="//input[@id='causeIdentifiedCheckBox']";


    public static final String problemResolve1="//input[@id='problemResolvedBox']";
    public static final String problemResolve2="//input[@id='resolutionTimeManualOverride']";
  /*  public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String
    public static final String

   */


}
