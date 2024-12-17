package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class InputHandler {
    public final WebDriver driver;
    private final WebDriverWait wait;

    public InputHandler(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
    }

    // Method to select from a dropdown or input a value in a text field
    public void selectOrInputValue(By locator, String value) {
        // Open the dropdown or focus on the input field
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

        // If it's a dropdown, wait for options to be visible
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[contains(@id,'select2-results')]/li")));

        // Flag to check if the option is found
        boolean optionFound = false;

        // Iterate through the options to find a match
        for (WebElement option : options) {
            if (option.getText().equalsIgnoreCase(value)) {
                option.click(); // Click the matching option
                optionFound = true;
                break; // Exit loop once the option is found
            }
        }

        // If no option is found in the dropdown, check if it's a text input
        if (!optionFound) {
            // If it's not a dropdown, try to input the value directly
            try {
                WebElement inputField = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                inputField.clear(); // Clear the field if necessary
                inputField.sendKeys(value); // Input the value
                System.out.println("Value inputted: " + value);
            } catch (Exception e) {
                System.out.println("No matching option found and unable to input value: " + value);
            }
        }
    }
}

