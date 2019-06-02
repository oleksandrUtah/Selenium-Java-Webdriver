package parametersTestsExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginParameters extends BaseTestParameters{  // Example of Parametric testing in TestNG (testng2.xml file)
    private String Url = "https://www.shocase.com/go/signup";
    private String email;
    private String password;
    private Actions a;
    //All WebElements are identified by @FindBy annotation:
    @FindBy(linkText = "SIGN IN")
    private WebElement loginShocase;
    @FindBy(xpath = "//input[@name='email_address']")
    private WebElement inputLogin;
    @FindBy(xpath = "//input[@name='j_password']")
    private WebElement inputPassword;
    @FindBy(xpath = "//button[@name='submit_login_button']")
    private WebElement submitLog;
    @FindBy(xpath = "//a[@class='sis-header-profile']")
    private WebElement resultsElement;
    @FindBy(linkText = "Sign out")
    private WebElement outElement;

    @Test(priority = 1)
    @Parameters ({ "email", "password"})
       //Happy Path: Verify "Email" = "koz84075+007@gmail.com" + "Password" = "1234567a".
    public void loginSuite1(String email, String password) throws InterruptedException{
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, resultsElement, outElement);
    }
    @Test(priority = 2)
       //Positive: Verify masking Password type (type="password").
    public void loginSuite2() {
        openLoginPage(Url, loginShocase);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
        System.out.println(email + " " + password);
    }
    @Test(priority = 3)
    @Parameters ({ "email3", "password"})
       //Positive: Make sure, that "Email" not Case Sensitive.
    public void loginSuite3(String email3, String password) throws InterruptedException{
        email = email3;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, resultsElement, outElement);
    }
    @Test(priority = 4)
    @Parameters ({"email", "password4"})
       //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginSuite4(String email, String password4) throws InterruptedException{
        password = password4;
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, resultsElement, outElement);
    }
    @Test(priority = 5)
    @Parameters ({"email", "password5"})
       //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginSuite5(String email, String password5) throws InterruptedException{
        password = password5;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 6)
    @Parameters ({"email6", "password6"})
       //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginSuite6(String email6, String password6) throws InterruptedException{
        email = email6;
        password = password6;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 7)
    @Parameters ({"email7", "password7"})
       //Negative: Make sure, that "Username" field is required.
    public void loginSuite7(String email7, String password7) throws InterruptedException{
        email = email7;
        password = password7;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 8)
    @Parameters ({"email8", "password8"})
       //Negative: Make sure, that "Password" field is required.
    public void loginSuite8(String email8, String password8) throws InterruptedException{
        email = email8;
        password = password8;
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}