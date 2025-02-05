package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {
    private WebDriver normalDriver;
    private WebDriver incognitoDriver;

    // Method to get the normal browser instance
    public WebDriver getNormalDriver() {
        if (normalDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.setAcceptInsecureCerts(true);
             normalDriver = new ChromeDriver(options);
        }
        return normalDriver;
    }

    // Method to get the incognito browser instance
    public WebDriver getIncognitoDriver() {
        if (incognitoDriver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
          //  options.addArguments("--headless=new", "--disable-gpu", "--no-sandbox", "--disable-dev-shm-usage", "--disable-extensions", "--disable-blink-features=AutomationControlled");
            options.setAcceptInsecureCerts(true);
            incognitoDriver = new ChromeDriver(options);
        }
        return incognitoDriver;
    }

    // Method to close all browser instances
    public void closeAllBrowsers() {
        if (normalDriver != null) {
            normalDriver.quit();
            normalDriver = null;
        }
        if (incognitoDriver != null) {
            incognitoDriver.quit();
            incognitoDriver = null;
        }
    }
}
