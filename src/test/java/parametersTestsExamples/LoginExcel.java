package parametersTestsExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginExcel extends BaseTestParameters{
    private String Url = "https://www.shocase.com/go/signup";
    private int indexSheet;
    private int indexRow;
    private int indexCell;
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
    private WebElement profileElement;
    @FindBy(css = "#sis-header-container > div > div.sis-header-inner-wrap > div > ul > li.sis-header-tab.ng-scope > ul > li:nth-child(5) > a")
    private WebElement signOutElement;
    @Test(priority = 1)
        //Happy Path: Verify "Email" = "koz84075+007@gmail.com" + "Password" = "1234567a".
    public void loginTest1() throws Exception{
        email = ExcelRead(0,1, 0);
        password = ExcelRead(0,1, 1);
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 2)
    //Positive: Verify masking Password type (type="password").
    public void loginTest2() throws Exception{
        email = ExcelRead(0,2, 0);
        password = ExcelRead(0,2, 1);
        openLoginPage(loginShocase, Url);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
        System.out.println(email + " " + password);
        driver.navigate().to(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
    @Test(priority = 3)
    //Positive: Make sure, that "Email" not Case Sensitive.
    public void loginTest3() throws Exception{
        email = ExcelRead(0,3, 0);
        password = ExcelRead(0,3, 1);
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(priority = 4)
    //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginTest4() throws Exception{
        email = ExcelRead(0,4, 0);
        password = ExcelRead(0,4, 1);
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 5)
    //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginTest5() throws Exception{
        email = ExcelRead(0,5, 0);
        password = ExcelRead(0,5, 1);
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 6)
    //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginTest6() throws Exception{
        email = ExcelRead(0,6, 0);
        password = ExcelRead(0,6, 1);
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 7)
    //Negative: Make sure, that "Username" field is required.
    public void loginTest7() throws Exception{
        email = ExcelRead(0,7, 0);
        password = ExcelRead(0,7, 1);
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
    @Test(priority = 8)
    //Negative: Make sure, that "Password" field is required.
    public void loginTest8() throws Exception{
        email = ExcelRead(0,8, 0);
        password = ExcelRead(0,8, 1);
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}

