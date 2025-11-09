package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    WebDriver driver;
    @FindBy(id="input-firstname")
    private WebElement firstNameField;
    @FindBy(id="input-lastname")
    private WebElement lastNameField;
    @FindBy(id="input-email")
    private WebElement emailField;
    @FindBy(id="input-telephone")
    private WebElement telePhoneField;
    @FindBy(id="input-password")
    private WebElement passwordField;
    @FindBy(id="input-confirm")
    private WebElement confirmPasswordField;
    @FindBy(name="agree")
    private WebElement privacyPolicyField;
    @FindBy(xpath="//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath="//input[@name='newsletter' and @value='1']")
    private WebElement NewsletterRadioButton;
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement duplicateEmailAddressWarning;
    @FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
    private WebElement privacyPolicyWarning;
    @FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
    private WebElement firstNameWarning;
    @FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
    private WebElement lastNameWarning;
    @FindBy(xpath="//input[@id='input-email']/following-sibling::div")
    private WebElement emailWarning;
    @FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
    private WebElement telephoneWarning;
    @FindBy(xpath="//input[@id='input-password']/following-sibling::div")
    private WebElement passwordWarning;


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void enterFirstName(String firstNameText)
    {
        firstNameField.sendKeys(firstNameText);
    }

    public void enterLastName(String LastNameText)
    {
        lastNameField.sendKeys(LastNameText);
    }
    public void enterEmail(String emailText)
    {
        emailField.sendKeys(emailText);
    }
    public void enterTelephone(String telephoneText)
    {
        telePhoneField.sendKeys(telephoneText);
    }
    public void enterPassword(String passwordText)
    {
        passwordField.sendKeys(passwordText);
    }
    public void enterConfirmPassword(String confirmPasswordText)
    {
        confirmPasswordField.sendKeys(confirmPasswordText);
    }

    public void selectPrivacyPolicy()
    {
        privacyPolicyField.click();
    }

    public AccountSuccessPage clickOnContinueButton()
    {
        continueButton.click();
        return new AccountSuccessPage(driver);
    }



    public void clickOnYesRadioButton()
    {
        NewsletterRadioButton.click();
    }
    public String retrieveDuplicateAccountWarningMessageText()
    {
        String duplicateAccountWarningText = duplicateEmailAddressWarning.getText();
        return duplicateAccountWarningText;
    }

    public String retrievePrivacyPolicyWarningMessageText()
    {
        String privacyPolicyWarningText = privacyPolicyWarning.getText();
        return privacyPolicyWarningText;
    }

    public String retrieveFirstNameWarningMessageText()
    {
        String firstNameWarningText = firstNameWarning.getText();
        return firstNameWarningText;
    }

    public String retrieveLastNameWarningMessageText()
    {
        String lastNameWarningText = lastNameWarning.getText();
        return lastNameWarningText;
    }

    public String retrieveEmailWarningMessageText()
    {
        String emailWarningText = emailWarning.getText();
        return emailWarningText;
    }

    public String retrieveTelephoneWarningMessageText()
    {
        String telephoneWarningText = telephoneWarning.getText();
        return telephoneWarningText;
    }
    public String retrievePasswordWarningMessageText()
    {
        String passwordWarningText = passwordWarning.getText();
        return passwordWarningText;
    }

    public AccountSuccessPage registerWithMandatoryFields(String firstNameText,String LastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText)
    {
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(LastNameText);
        emailField.sendKeys(emailText);
        telePhoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        confirmPasswordField.sendKeys(confirmPasswordText);
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public AccountSuccessPage registerWithAllFields(String firstNameText,String LastNameText,String emailText,String telephoneText,String passwordText,String confirmPasswordText)
    {
        firstNameField.sendKeys(firstNameText);
        lastNameField.sendKeys(LastNameText);
        emailField.sendKeys(emailText);
        telePhoneField.sendKeys(telephoneText);
        passwordField.sendKeys(passwordText);
        confirmPasswordField.sendKeys(confirmPasswordText);
        NewsletterRadioButton.click();
        privacyPolicyField.click();
        continueButton.click();
        return new AccountSuccessPage(driver);
    }

    public boolean displayWarningStatusMessage(String expectedPrivacyPolicyWarning,String expectedFirstNameWarning,
                                      String expectedLastNameWarning, String expectedEmailWarning,String expectedTelephoneWarning,
                                      String expectedPasswordWarning)
    {
       boolean privacyPolicyWarningStatus  = privacyPolicyWarning.getText().contains(expectedPrivacyPolicyWarning);
        boolean firstNameWarningStatus = firstNameWarning.getText().equals(expectedFirstNameWarning);
        boolean lastNameWarningStatus = lastNameWarning.getText().equals(expectedLastNameWarning);
        boolean emailWarningStatus = emailWarning.getText().equals(expectedEmailWarning);
        boolean telephoneWarningStatus = telephoneWarning.getText().equals(expectedTelephoneWarning);
        boolean passwordWarningStatus = passwordWarning.getText().equals(expectedPasswordWarning);
        return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWarningStatus && emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;

    }


}
