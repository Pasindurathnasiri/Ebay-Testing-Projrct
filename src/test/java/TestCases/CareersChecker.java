package TestCases;

import Base.BaseTest;
import Pages.*;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CareersChecker extends BaseTest {

    @Test
    public void careersInfoAssertion() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SellPage helpAndContact = new SellPage(driver);
        DealsPage dealsPage = new DealsPage(driver);
        CareersPage careersPage = new CareersPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/testdata/TestData 2.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String expectedTitle = excel.getCellData(4, 1); // Row 1, Column 1

        setReportName("Careers Page  Verifications- Test Case 7");
        startTest("Careers Page Verifications- Test Case 7");
        String actualPageTitle = careersPage.careersOperations();
        test = extent.createTest("Engineering  Page Title Verification", "Engineering Page Title Verification");

        try {
            // Capture a screenshot before performing the assertion
            String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "Title Verification");

            // Assert the titles and log success in Extent Report
            Assert.assertEquals(actualPageTitle, expectedTitle, "Title Matching");
            test.pass("Assertion Passed: Titles are Matching").addScreenCaptureFromPath(screenshotPath1);

        } catch (AssertionError e) {
            // Capture a screenshot on failure
            String screenshotPath2 = TakeErrorScreenShots.takeScreenshot(driver, "Title Comparison Failure");

            // Log failure in Extent Report
            test.fail("Assertion failed: Titles are not Matching...Looks like Engineering page not loaded ->>> Expected: " + expectedTitle + ", Actual: " + actualPageTitle + ". Exception: " + e.getMessage()).addScreenCaptureFromPath(screenshotPath2);

            // Rethrow the AssertionError to terminate the test and mark it as failed
            throw e;
        }

        // Close workbook
        excel.closeWorkbook();
    }


}
