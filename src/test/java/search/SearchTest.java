package search;
//test
import Pages.HomePage;
import Pages.SearchPage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

//updated comment

public class SearchTest extends BaseTest {
    public WebDriver driver;
    HomePage homepage;
    SearchPage searchPage;
    public SearchTest()
    {
        super();
    }
    @BeforeMethod
    public void setup() {
        driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
        homepage = new HomePage(driver);
    }
    @Test(priority = 1)
    public void verifySearchWithValidProduct()
    {
        searchPage = homepage.searchForAProduct(dataProp.getProperty("validProduct"));
        Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid product list is not displayed");
    }

    @Test(priority = 2)
    public void searchWithInvalidProduct()
    {
        searchPage = homepage.searchForAProduct(dataProp.getProperty("inValidProduct"));
        String actualText = searchPage.retrieveNoProductMessageText();
        String expectedText = "abcd";
                //dataProp.getProperty("invalidProductErrorMessage");
        Assert.assertEquals(actualText,expectedText,"No product error message is not displayed");
    }

    @Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","searchWithInvalidProduct"})
    public void searchWithoutAnyProduct()
    {

        searchPage=homepage.clickOnSearchButton();
        String actualText = searchPage.retrieveNoProductMessageText();
        String expectedText = dataProp.getProperty("invalidProductErrorMessage");
        Assert.assertEquals(actualText,expectedText,"No product error message is not displayed");
    }
    @AfterMethod
    public void tearDown()
    {
        driver.quit();
    }
}
