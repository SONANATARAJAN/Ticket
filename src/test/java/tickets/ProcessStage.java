package tickets;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import java.time.Duration;
import static pages.Change.ticketNumber;

public class ProcessStage {
    private WebDriverWait wait;
    public WebDriver driver;

    public ProcessStage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void Filter_TicketType(String ticketType) {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@id='tb_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_toolbar_item_w2ui-search-advanced']//span[@class='fa fa-filter']"))).click();
        WebElement sample = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_operator_2']")));
        Select drpDown = new Select(sample);
        drpDown.selectByValue("is");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_field_2']"))).sendKeys(ticketNumber);
        webDriverManager.clickMethod("//button[text()=\"Search\"]");
    }

    public void Select_TTtoPickup() throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager(driver);

        Thread.sleep(5000);
        String dynamicXPath = String.format("//div[@title='%s']//span[text()='%s']", ticketNumber, ticketNumber);
        WebElement element = driver.findElement(By.xpath(dynamicXPath));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        // Click on the "Pick Up" button
        webDriverManager.clickMethod("//button[text()='Pickup']");
        Thread.sleep(5000);
        element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(dynamicXPath)));
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        //click Process
        Thread.sleep(6000);
        webDriverManager.clickMethod("//span[text()='Process']");

    }
}
