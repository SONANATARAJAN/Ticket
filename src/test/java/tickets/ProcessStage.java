package tickets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProcessStage {
    private WebDriverWait wait;
    public WebDriver driver;

    public ProcessStage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void Filter_TicketType(String ticketType) {
        WebDriverManager webDriverManager = new WebDriverManager(driver);
        webDriverManager.clickMethod("//td[@id='tb_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_toolbar_item_w2ui-search-advanced']//span[@class='fa fa-filter']");
        WebElement sample = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_operator_3']")));
        Select drpDown = new Select(sample);
        drpDown.selectByValue("is");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_field_3']"))).sendKeys(ticketType);
        webDriverManager.clickMethod("//button[text()=\"Search\"]");
    }

    public void Select_TTtoPickup() throws InterruptedException {
        WebDriverManager webDriverManager = new WebDriverManager(driver);

        // Wait for the pagination element and get its text
        WebElement pagination = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='pagination_pageDetails']")));
        String paginationText = pagination.getText();
        System.out.println("Pagination text: " + paginationText);
        Pattern pattern = Pattern.compile("1-10 of (\\d+)");
        Matcher matcher = pattern.matcher(paginationText);
        int totalCount = 0;
        if (matcher.find()) {
            totalCount = Integer.parseInt(matcher.group(1));
            System.out.println("Total count is: " + totalCount);
        }
        // Locate and interact with the first row in the table
        WebElement tableRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_rec_1']")));
        tableRow.click(); // Single click
        // Click on the "Pick Up" button
        webDriverManager.clickMethod("//button[text()='Pickup']");
        Thread.sleep(5000);
        tableRow = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id='grid_TroubleTicketTableViewdataReload_cygnetTable_zbnsmajhek_rec_1']")));
        Actions actions = new Actions(driver);
        actions.doubleClick(tableRow).perform();
        //click Process
        Thread.sleep(5000);
        webDriverManager.clickMethod("//span[text()='Process']");

    }
}
