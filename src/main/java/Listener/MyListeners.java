package Listener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utility.ExtentReporter;
import utility.UtilityTest;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;

public class MyListeners implements ITestListener
{

    ExtentReports extentReports;
    ExtentTest extent;
    String testName;
    @Override
    public void onStart(ITestContext context) {
        extentReports = ExtentReporter.generateExtentReport();
    }

    @Override
    public void onTestStart(ITestResult result) {
        testName = result.getName();
        extent = extentReports.createTest(testName);
        extent.log(Status.INFO,testName+" test started executing");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
         extent.log(Status.PASS,testName+" test got successfully executed");
           }

    @Override
    public void onTestFailure(ITestResult result) {
          WebDriver  driver = null;
        try{
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        }catch (Exception e)
        {e.printStackTrace();}



        //String destinationScreenshotFile=System.getProperty("user.dir"+"\\Screenshots\\"+testName+".png");
        //try {
         //   FileHandler.copy(srcScreenshot, new File(destinationScreenshotFile));
        //}catch(IOException e)
        //{e.printStackTrace();}

        String testScreenshot=UtilityTest.captureScreenshot(driver,testName);
        extent.addScreenCaptureFromPath(testScreenshot);
        extent.log(Status.INFO,result.getThrowable());
        extent.log(Status.FAIL,testName+" test got failed");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extent.log(Status.INFO,result.getThrowable());
        extent.log(Status.SKIP,testName+" test got skipped");

    }

    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush();
        String test =  System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentReport\\extentReport.html";
        File extentReport = new File(test);
        try{
           Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }

            }
}
