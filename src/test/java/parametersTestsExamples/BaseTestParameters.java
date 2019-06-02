package parametersTestsExamples;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;
public class BaseTestParameters {                        // Class B in POM
    public WebDriver driver;
    @BeforeTest
    public void suiteSetup(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") +
                "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        //This initElements method will create all WebElements:
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterTest
    public void terminateBrowser() {
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    public void testPattern(String Url, WebElement loginShocase, String email, String password,
                            WebElement inputLogin, WebElement inputPassword, WebElement submitLog,
                            WebElement resultsElement, WebElement outElement) throws InterruptedException{
        openLoginPage(Url, loginShocase);
        typeCredentials (email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResults(resultsElement);
        logOut(resultsElement, outElement, loginShocase);
    }
    public void testPatternNegative(String Url, WebElement loginShocase, String email, String password,
                                    WebElement inputLogin, WebElement inputPassword,
                                    WebElement submitLog) throws InterruptedException{
        openLoginPage(Url, loginShocase);
        typeCredentials(email, password, inputLogin, inputPassword);
        submitLogin(submitLog);
        assertResultsNegative(submitLog);
    }
    public void explicitWait(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void openLoginPage(String Url, WebElement loginShocase) {
        driver.get(Url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginShocase.click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
    public void typeCredentials(String email, String password, WebElement inputLogin, WebElement inputPassword)
            throws InterruptedException{
        System.out.println(email + " " + password);
        inputLogin.clear();
        inputLogin.sendKeys(email);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
    public void submitLogin(WebElement submitLog) {
        submitLog.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //Assert.assertTrue(resultsElement.isDisplayed());
    }
    public void assertResults(WebElement resultsElement) {
        explicitWait(driver, resultsElement);
        Assert.assertTrue(resultsElement.isDisplayed());
        System.out.println("Log In was successful - " + resultsElement.getText());
    }
    public void assertResultsNegative(WebElement submitLog) {
        explicitWait(driver, submitLog);
        Assert.assertTrue(submitLog.isDisplayed());
        System.out.println(submitLog.getText());
    }
    public void logOut(WebElement resultsElement, WebElement outElement, WebElement loginShocase) throws InterruptedException{
        explicitWait(driver, resultsElement);
        Actions a = new Actions(driver);
        explicitWait(driver, resultsElement);
        a.moveToElement(resultsElement).build().perform();
        explicitWait(driver, outElement);
        outElement.click();
        Thread.sleep(1000);
    }
}
