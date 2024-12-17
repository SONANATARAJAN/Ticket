package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static utils.XPathProvider.dropdownXPath;


public class NewTicket {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Constructor to initialize WebDriver
    public NewTicket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Method to click "New Ticket" button by passing XPath from another class
    public void newTicketClick(String newTicketXPath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(70));
        long startTime = System.currentTimeMillis();
        // Wait for super-ac-wrapper to be invisible
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
        // Find and click the "New Ticket" element
        WebElement newTicket = driver.findElement(By.xpath(newTicketXPath));
        newTicket.click();
        long endTime = System.currentTimeMillis();

        // Print the time taken to find Trouble Ticket element
        System.out.println("Time taken to find the New Ticket: " + (endTime - startTime) + " ms");
    }

//    // Method to select type of ticket by passing XPath from another class
//    public void typeOfTicket(String dropdownXPath, String eventXPath) {
//        WebElement selectDropDown = driver.findElement(By.xpath(dropdownXPath));
//        selectDropDown.click();
//        long startTime = System.currentTimeMillis();
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
//
//        // Find and click the Event element
//        WebElement eventElement = driver.findElement(By.xpath(eventXPath));
//        eventElement.click();
//        long endTime = System.currentTimeMillis();

        // Print the time taken to select the Event
      //  System.out.println("Time taken to load Event Ticket: " + (endTime - startTime) + " ms");
 //   }
    //
    public void TestTicketTypeSelection(String ticketType){
        WebElement selectDropDown = driver.findElement(By.xpath(dropdownXPath));
      selectDropDown.click();
        // Open the dropdown to display options
       // wait.until(ExpectedConditions.elementToBeClickable(By.id("select2-chosen-13"))).click();
// Wait for the dropdown options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='select2-results-1']/li")));
// Determine the criteria for selection dynamically
        String criteria = ticketType; // Assuming ModeofRequest has the value you want to match
// Flag to check if the option is found
        boolean optionFound = false;
// Iterate through the options to find a match
        for (WebElement option : options) {
            String optionText = option.getText();
            // Check if the option matches the criteria dynamically
            if (optionText.equalsIgnoreCase(criteria)) { // Using equalsIgnoreCase for case-insensitive match
                option.click(); // Click the matching option
                optionFound = true;
                break; // Exit loop once the option is found
            }
        }
        if (!optionFound) {
            System.out.println("Option not found for criteria: " + criteria);
        }

    }
}
