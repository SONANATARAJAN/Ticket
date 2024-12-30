//package base;
//
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxOptions;
//import org.openqa.selenium.firefox.FirefoxProfile;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.File;
//import java.time.Duration;
//
//public class BaseTest {
//    protected WebDriver driver;
//    protected WebDriverWait wait;
//    protected static Logger logger;
//
//    @BeforeClass
//    public void initializeLogger() {
//        logger = LogManager.getLogger(BaseTest.class);
//    }
//
//  //  @BeforeMethod
//    public void setUp() {
//        String browser = System.getProperty("browser", "chrome").toLowerCase(); // Default browser is Chrome
//        try {
//            switch (browser) {
//                case "chrome":
//                    setUpChrome();
//                    break;
//                case "firefox":
//                    setUpFirefox();
//                    break;
//                default:
//                    throw new IllegalArgumentException("Unsupported browser: " + browser);
//            }
//        } catch (Exception e) {
//            logger.error("Error setting up the browser: " + e.getMessage(), e);
//            throw new RuntimeException("Failed to initialize WebDriver.", e);
//        }
//    }
//
//    private void setUpChrome() {
//        logger.info("Setting up Chrome browser...");
//        try {
//            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--incognito"); // Enable incognito mode
//            options.setAcceptInsecureCerts(true); // Accept insecure certificates
//            options.setBinary("/usr/bin/google-chrome");
//            driver = new ChromeDriver(options);
//            initializeBrowser();
//        } catch (Exception e) {
//            logger.error("Failed to initialize Chrome browser: " + e.getMessage(), e);
//            throw new RuntimeException("Error initializing Chrome browser.", e);
//        }
//    }
//
//    private void setUpFirefox() {
//        logger.info("Setting up Firefox browser...");
//        try {
//            // Load Firefox profile path from system property or use default
//            String firefoxProfilePath = System.getProperty("firefox.profile.path",
//                    "/home/your-username/.mozilla/firefox/j1txgs7i.default");
//
//            FirefoxProfile profile = new FirefoxProfile(new File(firefoxProfilePath));
//            FirefoxOptions options = new FirefoxOptions();
//            options.setProfile(profile);
//
//            // Set GeckoDriver path from system property or use default
//            System.setProperty("webdriver.gecko.driver", System.getProperty("gecko.driver.path", "/snap/bin/geckodriver"));
//
//            driver = new FirefoxDriver(options);
//            initializeBrowser();
//        } catch (Exception e) {
//            logger.error("Failed to initialize Firefox browser: " + e.getMessage(), e);
//            throw new RuntimeException("Error initializing Firefox browser.", e);
//        }
//    }
//
//    public void initializeBrowser() {
//        try {
//            logger.info("Initializing browser settings...");
//            driver.get("https://192.168.4.96:10095/oss/login.action"); // Open the login page
//            driver.manage().window().maximize(); // Maximize the browser window
//            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30)); // Set implicit wait
//            wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Set explicit wait
//        } catch (Exception e) {
//            logger.error("Failed to initialize browser settings: " + e.getMessage(), e);
//            throw new RuntimeException("Error during browser initialization.", e);
//        }
//    }
//
//    @AfterMethod
//    public void tearDown() {
//        if (driver != null) {
//            try {
//                logger.info("Closing the browser...");
//                driver.quit();
//            } catch (Exception e) {
//                logger.error("Error while closing the browser: " + e.getMessage(), e);
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
