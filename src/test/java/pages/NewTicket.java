package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class NewTicket {
    private final WebDriver driver;
    private final WebDriverWait wait;
    public NewTicket(WebDriver driver) {
        if (driver == null) {
            throw new IllegalArgumentException("WebDriver instance is null.");
        }
        if (((org.openqa.selenium.remote.RemoteWebDriver) driver).getSessionId() == null) {
            throw new IllegalStateException("WebDriver session is already closed.");
        }
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void newTicketClick(String newTicketXPath) {
        long startTime = System.currentTimeMillis();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("super-ac-wrapper")));
        WebElement newTicket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='TroubleTicketView']//div//div//button[normalize-space(text())='New Ticket']")));
        newTicket.click();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to find the New Ticket: " + (endTime - startTime) + " ms");
    }

}
