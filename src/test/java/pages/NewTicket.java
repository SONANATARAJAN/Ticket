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
    public NewTicket(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void newTicketClick(String newTicketXPath) {
        long startTime = System.currentTimeMillis();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
        WebElement newTicket = driver.findElement(By.xpath(newTicketXPath));
        newTicket.click();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to find the New Ticket: " + (endTime - startTime) + " ms");
    }
}
