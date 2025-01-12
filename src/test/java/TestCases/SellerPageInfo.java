package TestCases;

import Base.BaseTest;
import Pages.SellPage;
import Pages.HomePage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SellerPageInfo extends BaseTest {

    @Test
    public void HelpAndConatctInfoAssertion() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SellPage sellPage = new SellPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/testdata/TestData 2.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String expectedSentence = excel.getCellData(2, 1); // Row 1, Column 1

        // Step 1: Search for Samsung phone

        setReportName("Sell Page Info- Test Case 5");
        startTest("Sell Page Info- Test Case 5");
        String actualSentence = sellPage.SellerInfo();
        test = extent.createTest("Successful Navigate to Sell InFo Page", "System Successfully Navigate to Sell InFo Page");

        try {
            // Capture a screenshot before performing the assertion
            String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "Sell InfoPage");

            // Assert the strings and log success in Extent Report
            Assert.assertEquals(actualSentence, expectedSentence, "Sell info data are matching!");
            test.pass("Assertion Passed: Sell Info Page data is correct")
                    .addScreenCaptureFromPath(screenshotPath1);

        } catch (AssertionError e) {
            // Capture a screenshot on failure
            String screenshotPath4 = TakeErrorScreenShots.takeScreenshot(driver, "Sell InfoPage Failure");

            // Log failure in Extent Report with details
            test.fail("Assertion failed: Sell Info Page data does not match. Expected: " + expectedSentence
                            + ", Actual: " + actualSentence + ". Exception: " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotPath4);

            // Rethrow the AssertionError to terminate the test and mark it as failed
            throw e;
        }

        // Close workbook
        excel.closeWorkbook();
    }

}
