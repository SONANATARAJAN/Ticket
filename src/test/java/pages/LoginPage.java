package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverManager;

import java.time.Duration;

public class LoginPage {
    public WebDriver driver;
    public WebDriverWait wait;
    private final utils.WebDriverManager webDriverManager;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.driver = driver;
        this.webDriverManager=new utils.WebDriverManager(driver);
    }

    // Method to enter username
    public void enterUsername(String username, String usernameXPath) {
        WebElement usernameField = driver.findElement(By.xpath(usernameXPath));
        usernameField.sendKeys(username);
    }

    // Method to enter password
    public void enterPassword(String password, String passwordXPath) {
        WebElement passwordField = driver.findElement(By.xpath(passwordXPath));
        passwordField.sendKeys(password);
    }

    // Method to click on "Force Logout"
    public void forceLogout() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("forcelogout"))).click();
    }

    // Method to click on login button
    public void clickLogin(String loginButtonXPath) {
        long startTime = System.currentTimeMillis();
        WebElement loginButton = driver.findElement(By.xpath(loginButtonXPath));
        loginButton.click();
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken to find the login button: " + (endTime - startTime) + " ms");
    }

    // Method to handle trouble ticket
    public void troubleTicket() {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-wrapper")));
            WebElement ticket = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='topmenubar']//li//a[@id='tt_menu_1']")));
            ticket.click();
            System.out.println(ticket.getText());
            WebElement opentt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='tt_menu_1_submenu']//li//a[text()='Open Tickets']")));
            opentt.click();
            System.out.println(opentt.getText());


    }
}
