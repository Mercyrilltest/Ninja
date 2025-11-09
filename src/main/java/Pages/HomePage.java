package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage {
    WebDriver driver;
    //Objects
    @FindBy (xpath="//span[text()='My Account']")
    private WebElement myAccountDropMenu;
    @FindBy (linkText="Login")
    private WebElement LoginOption;
    @FindBy (linkText="Register")
    private WebElement RegisterOption;
    @FindBy (xpath="//input[@name='search']")
    private WebElement searchBoxField;
    @FindBy (xpath="//button[@class='btn btn-default btn-lg']")
    private WebElement searchButton;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    //Actions
    public void clickOnMyAccount()
    {
        myAccountDropMenu.click();
    }

    public LoginPage selectLoginOption()
    {
        LoginOption.click();
        return new LoginPage(driver);
    }

    public LoginPage navigateToLoginPage()
    {
        myAccountDropMenu.click();
        LoginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage navigateToRegisterPage()
    {
        myAccountDropMenu.click();
        RegisterOption.click();
        return new RegisterPage(driver);
    }

    public RegisterPage selectRegisterOption()
    {
       RegisterOption.click();
       return new RegisterPage(driver);
    }

    public void enterProductNameInSearchBox(String productText)
    {
        searchBoxField.sendKeys(productText);
    }

    public SearchPage clickOnSearchButton()
    {
        searchButton.click();
        return new SearchPage(driver);
    }

    public SearchPage searchForAProduct(String productText)
    {
        searchBoxField.sendKeys(productText);
        searchButton.click();
        return new SearchPage(driver);
    }


}
