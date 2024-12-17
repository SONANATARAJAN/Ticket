package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tests.LoginTest;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public WebDriverWait wait;
    protected static Logger logger;
public Properties p;
    @BeforeMethod
    public void setUp() throws IOException {
//        FileReader file=new FileReader("../config/config.properties");
//        p=new Properties();
//        p.load(file);

        ChromeOptions options = new ChromeOptions();
        // Add incognito mode to Chrome
        options.addArguments("--incognito");
        options.setAcceptInsecureCerts(true);
        driver =new ChromeDriver(options);
        driver.get("https://192.168.4.96:10095/oss/login.action");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
       // wait = new WebDriverWait(driver, Duration.ofSeconds(50));

    }
    @BeforeClass
    public void loggerMethod(){
        logger= LogManager.getLogger(LoginTest.class);
    }



    //@AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

