package register;

import Pages.AccountSuccessPage;
import Pages.HomePage;
import Pages.RegisterPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utility.UtilityTest;

import java.time.Duration;
import java.util.Date;

import static utility.UtilityTest.generateEmailWithTimeStamp;

public class RegisterTest extends BaseTest {
    public WebDriver driver;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;

    public RegisterTest()
    {
        super();
    }
    @BeforeMethod
    public void setup()
    {
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser")) ;
        HomePage homepage = new HomePage(driver);
        registerPage  = homepage.navigateToRegisterPage();
          }
    @Test(priority = 1)
    public void verifyRegisteringAnAccountWithMandatoryFields()
    {
        accountSuccessPage = registerPage.registerWithMandatoryFields(dataProp.getProperty("firstName"),
                dataProp.getProperty("LastName"),
                UtilityTest.generateEmailWithTimeStamp(),
                dataProp.getProperty("telePhoneNumber"),
                prop.getProperty("validPassword"),
                prop.getProperty("validPassword")
                );

       String actualText = accountSuccessPage.retrieveAccountCreatedMessageText();
       String expectedText = dataProp.getProperty("accountCreatedMessage");
        Assert.assertEquals(actualText,expectedText,"Actual Text is not correct");

    }

    @Test(priority = 2)
public void verifyRegisteringByProvidingAllFields()
{

    accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
            dataProp.getProperty("LastName"),
            UtilityTest.generateEmailWithTimeStamp(),
            dataProp.getProperty("telePhoneNumber"),
            prop.getProperty("validPassword"),
            prop.getProperty("validPassword")
    );


    String actualText = accountSuccessPage.retrieveAccountCreatedMessageText();
    String expectedText = dataProp.getProperty("accountCreatedMessage");
    Assert.assertEquals(actualText,expectedText,"Actual Text is not correct");

}

    @Test(priority = 3)
    public void verifyRegisteringWithExistingAccount()
    {

        accountSuccessPage = registerPage.registerWithAllFields(dataProp.getProperty("firstName"),
                dataProp.getProperty("LastName"),
                prop.getProperty("validEmail"),
                dataProp.getProperty("telePhoneNumber"),
                prop.getProperty("validPassword"),
                prop.getProperty("validPassword")
        );
        String actualText =  registerPage.retrieveDuplicateAccountWarningMessageText();
        String expectedText = dataProp.getProperty("existingAccountWarningMessage");
        Assert.assertEquals(actualText,expectedText,"Warning message regarding duplicate email is not displayed");

    }

    @Test(priority = 4)
    public void verifyRegisteringWithoutFillingAnyDetails()
    {

        registerPage.clickOnContinueButton();
       Assert.assertTrue(registerPage.displayWarningStatusMessage(dataProp.getProperty("privacyPolicyWarning"),dataProp.getProperty("firstNameWarning"),
                dataProp.getProperty("lastNameWarning"),dataProp.getProperty("emailWarning"),dataProp.getProperty("telephoneWarning"),
                dataProp.getProperty("passwordWarning")),"Warning message (s) are not displayed");
    }

    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
