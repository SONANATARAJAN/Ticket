package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import tickets.TicketAction;
import utils.WebDriverManager;
import utils.XPathProvider;

import java.time.Duration;

public class RequestFulfilmentTTFlow {
    private WebDriverWait wait;
    public WebDriver driver;

    public RequestFulfilmentTTFlow(WebDriver driver) {
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(70));
    }

    public void Logout(){
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.clickElement("//label[@id='image-checkbox']/span");
        webDriverManager.clickMethod("//i[@class='fa fa-power-off ']");
        webDriverManager.clickMethod("//button[@id='Yes' and text()='Yes']");

    }
    public void LoginNA(String ticketType) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername("nauser1", XPathProvider.usernameXPath);
        loginPage.enterPassword("nms@123$", XPathProvider.passwordXPath);
        loginPage.clickLogin(XPathProvider.loginButtonXPath);
        loginPage.troubleTicket();
        TicketAction ticketAction=new TicketAction(driver);
        ticketAction.executeTicketProcess(ticketType);

    }

    public void AprovalReq_yes() throws InterruptedException {
        WebDriverManager webDriverManager=new WebDriverManager(driver);
        webDriverManager.approvalRequest_Yes();
        //move to NA user
        Logout();
        String ticketType="Request Fulfillment";
        LoginNA(ticketType);

    }
}
