package com.example;

import java.io.File;
 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
import io.github.bonigarcia.wdm.WebDriverManager;
 
/**
 * Unit test for simple App.
 */
public class AppTest
{
static ExtentTest test;
static ExtentReports report;
public static WebDriver driver;

@BeforeClass
public static void startTest()
{
    report = new ExtentReports("ExtentReportResults.html");
    test = report.startTest("ExtentDemo");
}
@Test
public void extentReportsDemo()
{
    WebDriverManager.chromedriver().setup();
    driver = new ChromeDriver();
    driver.get("https://www.google.co.in");
    if(driver.getTitle().equals("Google"))
    {
        test.log(LogStatus.PASS, "Navigated to the specified URL");
    }
    else
    {
        test.log(LogStatus.FAIL, "Test Failed");
//captureScreenshot();
    }
captureScreenshot();
}
@AfterClass
public static void endTest()
{
report.endTest(test);
report.flush();
driver.quit();
}
// Method to capture screenshot
   public void captureScreenshot() {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            // Capture screenshot and convert to file format
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            // Define destination path for the screenshot
            File dest = new File("C:\\Users\\VishwanathD\\Desktop\\Software_Testing\\Projects\\demo1\\screenshot.png");
            // Copy file to destination
            FileUtils.copyFile(src, dest);
            // Add screenshot to the extent report
            test.log(LogStatus.PASS, "Screenshot below: " + test.addScreenCapture(dest.getAbsolutePath()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
 