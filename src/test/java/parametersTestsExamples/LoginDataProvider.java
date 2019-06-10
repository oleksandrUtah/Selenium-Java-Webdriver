package parametersTestsExamples;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginDataProvider extends BaseTestParameters {
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
    private WebElement profileElement;
    @FindBy(css = "#sis-header-container > div > div.sis-header-inner-wrap > div > ul > li.sis-header-tab.ng-scope > ul > li:nth-child(5) > a")
    private WebElement signOutElement;
    @Test(dataProvider = "login-password", dataProviderClass = DataproviderClass.class)
    public void testMethod1 (String email, String password) throws InterruptedException {
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(dataProvider = "login-password", dataProviderClass = DataproviderClass.class)
    public void testMethod2 (String email, String password) throws InterruptedException {
        openLoginPage(loginShocase, Url);
        System.out.println("Password type: " + inputPassword.getAttribute("type"));
        Assert.assertEquals(inputPassword.getAttribute("type"), "password");
        System.out.println(email + " " + password);
        driver.navigate().to(Url);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        Thread.sleep(5000);
    }
    @Test(dataProvider = "login-password", dataProviderClass = DataproviderClass.class)
    public void testMethod3 (String email, String password) throws InterruptedException {
        testPattern(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog, profileElement, signOutElement);
    }
    @Test(dataProvider = "login-password", dataProviderClass = DataproviderClass.class)
    public void testMethod4_8 (String email, String password) throws InterruptedException {
        testPatternNegative(Url, loginShocase, email, password, inputLogin, inputPassword, submitLog);
    }
}
