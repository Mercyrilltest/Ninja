package login;

import Pages.AccountPage;
import Pages.HomePage;
import Pages.LoginPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.UtilityTest;
import static utility.UtilityTest.generateEmailWithTimeStamp;


public class LoginTest extends BaseTest {
    public WebDriver driver;
    LoginPage loginPage;

    public LoginTest()
    {
        super();
    }

      @BeforeMethod
    public void setup()
    {

        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser")) ;
        HomePage homepage = new HomePage(driver);
        loginPage = homepage.navigateToLoginPage();
           }
    @Test(priority = 1, dataProvider = "ValidCredentialsSupplier")
    public void verifyLoginWithValidCredentials(String email,String password)
    {

        AccountPage accountPage  = loginPage.login(email,password);
        Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformation(),"Edit Your Account Information is not displayed");
            }
            @DataProvider(name="ValidCredentialsSupplier")
            public Object [][] supplyTestData()
            {
            Object [][] data= UtilityTest.getTestDataFromExcel("Login");
            return data;
            }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials()
    {

        loginPage.login(generateEmailWithTimeStamp(),dataProp.getProperty("invalidPassword"));
        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String ExpectedWarningMessage = dataProp.getProperty("loginWarningMessage");
        Assert.assertEquals(actualWarningMessage,ExpectedWarningMessage,"Warning message is not correct");
           }
    @Test(priority = 3)
    public void verifyLoginWithInvalidEmailAndValidPassword()
    {

        loginPage.login(generateEmailWithTimeStamp(),prop.getProperty("validPassword"));
        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String ExpectedWarningMessage = dataProp.getProperty("loginWarningMessage");
        Assert.assertEquals(actualWarningMessage,ExpectedWarningMessage,"Warning message is not correct");
           }

    @Test(priority = 4)
    public void verifyLoginWithValidEmailAndInvalidPassword()
    {
        loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String ExpectedWarningMessage = dataProp.getProperty("loginWarningMessage");
        Assert.assertEquals(actualWarningMessage,ExpectedWarningMessage,"Warning message is not correct");
    }

    @Test(priority = 5)
    public void verifyLoginNoEmailAndPassword()
    {

        loginPage.clickOnLoginButton();
        String actualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
        String ExpectedWarningMessage = dataProp.getProperty("loginWarningMessage");
        Assert.assertEquals(actualWarningMessage,ExpectedWarningMessage,"Warning message is not correct");

    }

@AfterMethod
public void tearDown()
{
    driver.quit();
}
}
