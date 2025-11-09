package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utility.UtilityTest;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    public WebDriver driver;
    public Properties prop;
    public Properties dataProp;
    public  BaseTest()
    {
        prop = new Properties();
        File propFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\config\\Config.Properties");
        dataProp = new Properties();
        File dataPropFile = new File(System.getProperty("user.dir") + "\\src\\main\\java\\testdata\\testdata.properties");
        try {
            FileInputStream dataFis = new FileInputStream(dataPropFile);
            dataProp.load(dataFis);
        }catch (Throwable e)
        {e.printStackTrace();
        }
        try {
            FileInputStream fis = new FileInputStream(propFile);
            prop.load(fis);
        }catch (Throwable e)
        {e.printStackTrace();
        }
    }

    public WebDriver initializeBrowserAndOpenApplication(String browserName)
    {
        if(browserName.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(UtilityTest.IMPLICIT_WAIT_TIME));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(UtilityTest.PAGE_WAIT_TIME));
        driver.get(prop.getProperty("url"));
        return driver;
    }
}
