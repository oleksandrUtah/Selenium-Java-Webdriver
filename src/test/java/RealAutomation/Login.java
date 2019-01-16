package RealAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.*;
import org.testng.Assert;


public class Login extends BaseTest{
    private String baseUrl = "http://hrm.seleniumminutes.com";
    private String username = "admin";
    private String password = "Password";


    private By inputLogin = By.id("txtUsername");
    private By inputPassword = By.id("txtPassword");
    private By submitLog = By.id("btnLogin");
    private By resultsElement = By.id("welcome");
    private By logoutElement = By.linkText("Logout");
    private By spanElement = By.id("spanMessage");
    /*
    @FindBy(id="txtUsername") WebElement inputLogin;
    @FindBy(id="txtPassword") WebElement inputPassword;
    @FindBy(id="btnLogin") WebElement submitLog;
    @FindBy(id="welcome") WebElement resultsElement;
    @FindBy(linkText = "Logout") WebElement logoutElement;
    @FindBy(id="spanMessage") WebElement spanElement;
     */




    @Test(priority = 1) //Happy Path: Verify "Username" = "admin" + "Password" = "Password".
    private void loginSuite1() {
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog, resultsElement, logoutElement);
    }
    @Test(priority = 2) //Positive: Verify masking Password type (type="password").
    public void loginSuite2() {
        openLoginPage(baseUrl);
        WebElement ps = driver.findElement(inputPassword);
        System.out.println("Password type: " + ps.getAttribute("type"));
        Assert.assertEquals(ps.getAttribute("type"), "password");
    }
    @Test(priority = 3) //Positive: Make sure, that "Username" not Case Sensitive.
    public void loginSuite3() {
        username = "Admin";
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog, resultsElement, logoutElement);
    }
    @Test(priority = 4) //Positive: Make sure, that "Password" not Case Sensitive.
    public void loginSuite4() {
        username = "admin";
        password = "password";
        testPattern(baseUrl, username, password, inputLogin, inputPassword, submitLog, resultsElement, logoutElement);
    }
    @Test(priority = 5) //Negative: Verify registered "Username" + uncreated "Password" cannot be used.
    public void loginSuite5() {
        username = "admin";
        password = "pass";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword, submitLog, spanElement);
    }
    @Test(priority = 6) //Negative: Verify unregistered "Username" + created "Password" cannot be used.
    public void loginSuite6() {
        username = "xxxxx";
        password = "Password";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword, submitLog, spanElement);
    }
    @Test(priority = 7) //Negative: Make sure, that "Username" field is required.
    public void loginSuite7() {
        username = "";
        password = "Password";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword, submitLog, spanElement);
    }
    @Test(priority = 8) //Negative: Make sure, that "Password" field is required.
    public void loginSuite8() {
        username = "admin";
        password = "";
        testPatternNegative(baseUrl, username, password, inputLogin, inputPassword, submitLog, spanElement);
    }
}
