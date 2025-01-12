package Pages;

import Utils.TakeErrorScreenShots;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import Base.BaseTest;

public class CareersPage extends BaseTest {
    WebDriver driver;

    // Locators using @FindBy
    @FindBy(xpath = "//*[@id=\"vl-flyout-nav\"]/ul/li[11]/a") // Deals Button
            WebElement deals;

    @FindBy(xpath = "//*[@id=\"gf-l\"]/li[1]/a") // AboutEbay
    WebElement aboutEbay;

    @FindBy(xpath = "//*[@id=\"main-nav-menu\"]/nav/ul/li[7]/a") // carreres Ebay button
    WebElement carreresLink;

    // Constructor to initialize elements
    public CareersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Method to searchFor
    public String careersOperations() {

        // Click On Deals
        deals.click();
        test = extent.createTest("Successful Navigate to Global Deals Page", "Successful Navigate to Global Deals Page");
        String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "GlobalDealsPage");
        test.pass("System Successfully Navigate to Global Deals Page").addScreenCaptureFromPath(screenshotPath1);
        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        test = extent.createTest("Successfully Navigate to Bottom Of the Page", "Successfully Navigate to Bottom Of the Page");
        String screenshotPath2 = TakeErrorScreenShots.takeScreenshot(driver, "PageBottom");
        test.pass("System Successfully Navigate to Bottom Of the Page").addScreenCaptureFromPath(screenshotPath2);

        // Click On About E-Bay
        aboutEbay.click();
        test = extent.createTest("Successfully Navigate to About Page", "Successfully Navigate to About Page");
        String screenshotPath3 = TakeErrorScreenShots.takeScreenshot(driver, "AboutPage");
        test.pass("System Successfully Navigate to About Page").addScreenCaptureFromPath(screenshotPath3);

        // Click On Careers Link
        carreresLink.click();
        test = extent.createTest("Successfully Navigate to Careers Page", "Successfully Navigate to Careers Page");
        String screenshotPath4 = TakeErrorScreenShots.takeScreenshot(driver, "CareersPage");
        test.pass("System Successfully Navigate to Careers Page").addScreenCaptureFromPath(screenshotPath4);

        // Create an explicit wait with a timeout of 30 seconds
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        try {
            // Wait for the element to be visible
            WebElement startSearchJob = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("typehead")));

            // Perform actions on the element
            startSearchJob.sendKeys("Engineering" + Keys.ENTER);
            test = extent.createTest("Successfully Navigate to Engineering Jobs Page", "Successfully Navigate to Engineering Jobs Page");
            String screenshotPath5 = TakeErrorScreenShots.takeScreenshot(driver, "Engineering Jobs Page");
            test.pass("System Successfully Navigate to Engineering Jobs Page").addScreenCaptureFromPath(screenshotPath5);

        } catch (Exception e) {

            System.out.println("Element not found within the specified time.");
            test = extent.createTest("Element not found within the specified time", "Element not found within the specified time");
            String screenshotPath5 = TakeErrorScreenShots.takeScreenshot(driver, "Element not found within the specified time");
            test.pass("Element not found within the specified time").addScreenCaptureFromPath(screenshotPath5);
        }

        String pageTitle = driver.getTitle();
        System.out.println(pageTitle);
        return pageTitle;

    }
}