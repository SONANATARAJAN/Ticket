package tickets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;
import java.util.List;


import static utils.XPathProvider.dropdownXPath;

public class TicketTest {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final utils.WebDriverManager webDriverManager;

    // Constructor to initialize WebDriver and WebDriverWait
    public TicketTest(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.webDriverManager=new utils.WebDriverManager(driver);
    }
    public void selectDropdownOption(String ticketType) {
        WebElement selectDropDown = driver.findElement(By.xpath(dropdownXPath));
        selectDropDown.click();
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@class='select2-results']//li")));
        boolean optionFound = false;
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(ticketType)) {
                option.click();
                optionFound = true;
                break;
            }}
        if (!optionFound) {
            System.out.println("Option not found for ticket type: " + ticketType);
        }
    }

    public void testTicketTypeSelection(String ticketType) throws InterruptedException {
        switch (ticketType) {
            case "Event":
                Events eventsTicket = new Events(driver);
                eventsTicket.createEvent(
                      "Email", "London",
                        "Business Continuity Plan", "Disaster Recovery", "S1", "P1",
                        "High", "Service Affecting", "Warning", "Demo_Event_TT_sona",
                        "src/test/resources/upload_files/empty.xls"
                );
                break;

            case "Incident":
                Incident incidentTicket = new Incident(driver);
                incidentTicket.createIncident(
                          "Email", "London",
                        "Circuit", "Loop", "BSNL", "EIG/FUJ/MRS/VC4/A1931",
                        "S1", "P1", "High", "Incident-demo-sona",
                        "src/test/resources/upload_files/empty.xls"
                );
                break;
            case "Problem":
                Problem problemTicket= new Problem(driver);
                problemTicket.createProblem(
                         "Email", "London", "Terrestrial Segment",
                        "S1", "S1", "P1", "High", "Problem_Demo",   "src/test/resources/upload_files/empty.xls"
                );
            case "Request Fulfillment":
                RequestFulfillment requestFulfillmentTicket=new RequestFulfillment(driver);
                requestFulfillmentTicket.createRequestfulfiment(
                        "Email", "London", "Testing",
                        "MSP1+1 Testing", "London", "P1" , "RequestFulfilment_Demo",   "src/test/resources/upload_files/empty.xls"
                );
            case "Change":
                Change change=new Change(driver);
                change.changettcreation("Email","ABU TALAT","Circuit Change","Group of Ciruits","AT&T","EIG/BCT/MON/10G/A0864",
                        "General","High","Traffic affecting","P1","High","create change tt","src/test/resources/upload_files/empty.xls");

            default:
                System.out.println("Invalid ticket type: " + ticketType);
                break;
        }
    }


}
