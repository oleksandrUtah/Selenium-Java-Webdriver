package parametersTestsExamples;
import realAutomation.Tools;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
public class BaseTestParameters {
    protected WebDriver driver;

    @BeforeSuite
    public void suiteSetup(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //This initElements method will create all WebElements:
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
    @AfterTest
    public void closeBrowser() {
        try {
            driver.close();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    @AfterSuite
    public void terminateBrowser() {
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }

    public void testPattern(String baseUrl, String username, String password, WebElement inputLogin, WebElement inputPassword,
                            WebElement submitLog, WebElement resultsElement, WebElement logoutElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(resultsElement);
        logOut(resultsElement, logoutElement);
    }
    public void testPatternNegative(String baseUrl, String username, String password, WebElement inputLogin, WebElement inputPassword,
                                    WebElement submitLog, WebElement spanElement){
        openLoginPage(baseUrl);
        typeCredentials(username, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(spanElement);
    }

    public void explicitWait(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void openLoginPage(String baseUrl) {
        driver.get(baseUrl);
        Tools.waitForPageLoaded(driver);
    }
    public void typeCredentials(String username, String password, WebElement inputLogin, WebElement inputPassword) {
        inputLogin.sendKeys(username);
        inputPassword.sendKeys(password);
    }
    public void submitLogin(WebElement submitLog) {
        submitLog.submit();
    }

    public void assertResults(WebElement resultsElement) {
        explicitWait(driver, resultsElement);
        System.out.println("Log In was successful - " + resultsElement.getText());
        Assert.assertTrue(resultsElement.isDisplayed());
    }
    public void assertResultsNegative(WebElement spanElement) {
        explicitWait(driver, spanElement);
        Assert.assertTrue(spanElement.isDisplayed());
        System.out.println(spanElement.getText());
    }
    public void logOut(WebElement resultsElement, WebElement logoutElement) {
        resultsElement.click();
        explicitWait(driver, logoutElement);
        logoutElement.click();
    }
}
