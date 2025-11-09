package utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ExtentReporter {


    public static ExtentReports generateExtentReport()
    {
        ExtentReports extentReport = new ExtentReports();
        //File extenReportFile = new File(System.getProperty("user.dir"+"\\Output\\Report.html"));
        ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"\\target\\surefire-reports\\ExtentReport\\extentReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setReportName("Ninja Test Results");
        spark.config().setDocumentTitle("Ninja Test Report");
        spark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
        spark.config().thumbnailForBase64(true);
        extentReport.attachReporter(spark);
        Properties prop1 = new Properties();
        File propFile1 = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\Config.Properties");
        try {
            FileInputStream fis1 = new FileInputStream(propFile1);
            prop1.load(fis1);
        }catch (Throwable e)
        {e.printStackTrace();
        }
        extentReport.setSystemInfo("Application URL",prop1.getProperty("url"));
        extentReport.setSystemInfo("Browser Name",prop1.getProperty("browser"));
        extentReport.setSystemInfo("Email",prop1.getProperty("validEmail"));
        extentReport.setSystemInfo("Password",prop1.getProperty("validPassword"));
        extentReport.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReport.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
    return extentReport;
    }


}
