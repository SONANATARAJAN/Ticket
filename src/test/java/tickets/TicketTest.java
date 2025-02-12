package tickets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

import java.time.Duration;
import java.util.List;


import static pages.Change.test;
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
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
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
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
                eventsTicket.createEvent(
                      "Email", "London",
                        "Business Continuity Plan", "Disaster Recovery", "S1", "P1",
                        "High", "Service Affecting", "Warning", "Event ticket Testing",
                        "src/test/resources/upload_files/ImpactedCircuits.xlsx"
                );
                break;

            case "Incident":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
                Incident incidentTicket = new Incident(driver);
                incidentTicket.createIncident(
                          "Email", "London",
                        "Circuit", "Loop", "BSNL", "EIG/FUJ/MRS/VC4/A1932",
                        "S1", "P1", "High", "Incident Ticket testing",
                        "src/test/resources/upload_files/empty.xls"
                );
                break;
            case "Problem":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
                Problem problemTicket= new Problem(driver);
                problemTicket.createProblem(
                         "Email", "London", "Terrestrial Segment",
                        "S1", "S1", "P1", "High", "Problem Ticket Testing",   "src/test/resources/upload_files/empty.xls"
                );
                break;
            case "Change":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
                Change change=new Change(driver);
                change.changett("Email","ABU TALAT","Capacity Enhancement","General",
                        "High","Traffic affecting","P1","High","Change Ticket for testing","src/test/resources/upload_files/empty.xls","src/test/resources/upload_files/ImpactedCircuits.xlsx");
                break;

            case "Request Fulfillment":
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
                RequestFulfillment requestFulfillmentTicket=new RequestFulfillment(driver);
                requestFulfillmentTicket.createRequestfulfiment(
                        "Email", "London", "Testing",
                        "MSP1+1 Testing", "London", "P1" , "Request Fullfillment Ticket for Testing ",   "src/test/resources/upload_files/empty.xls"
                );
                break;

            default:
                System.out.println("Invalid ticket type: " + ticketType);
                break;
        }
    }


}
