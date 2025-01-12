package TestCases;

import Base.BaseTest;
import Pages.SellPage;
import Pages.HomePage;
import Pages.DealsPage;
import Pages.ProductPage;
import Pages.SearchResultsPage;
import Utils.ExcelHandler;
import Utils.TakeErrorScreenShots;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DealsPageInfoChecker extends BaseTest {

    @Test
    public void dealsInfoAssertion() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        ProductPage productPage = new ProductPage(driver);
        SellPage helpAndContact = new SellPage(driver);
        DealsPage dealsPage = new DealsPage(driver);

        // Initialize Excel Information
        String excelFilePath = "src/test/resources/testdata/TestData.xlsx";
        String sheetName = "Data";

        // Initialize ExcelUtils
        ExcelHandler excel = new ExcelHandler(excelFilePath, sheetName);

        // Read data
        String expectedSentence = excel.getCellData(3, 1); // Row 1, Column 1

        setReportName("DealsInfo- Test Case 6");
        startTest("DealsInfo- Test Case 6");
        String actualSentence = dealsPage.dealsInfoAssertion();
        test = extent.createTest("Deals InFo Page Data Verification", "SDeals InFo Page Data Verification");
        try {
            // Capture a screenshot before performing the assertion
            String screenshotPath1 = TakeErrorScreenShots.takeScreenshot(driver, "InfoPage");

            // Assert that the actual sentence matches the expected sentence
            Assert.assertEquals(actualSentence, expectedSentence, "Strings matching!");

            // Log success in Extent Report with a screenshot
            test.pass("System successfully navigated to Info Page")
                    .addScreenCaptureFromPath(screenshotPath1);

        } catch (AssertionError e) {
            // Capture a screenshot on assertion failure
            String screenshotPath4 = TakeErrorScreenShots.takeScreenshot(driver, "InfoPage_Failure");

            // Log failure in Extent Report with exception details and a screenshot
            test.fail("Assertion failed: " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotPath4);

            // Rethrow the AssertionError to terminate the test and mark it as failed
            throw e;
        }

        // Close workbook
        excel.closeWorkbook();
    }


}
