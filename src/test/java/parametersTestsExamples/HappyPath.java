package parametersTestsExamples;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

public class HappyPath { // Example of Linear Scripting framework
    WebDriver driver;
    String URL = "https://www.shocase.com";
    @BeforeSuite

    public void suiteSetup(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    @AfterSuite
    public void terminateBrowser() throws InterruptedException{
        Thread.sleep(3000);
        try {
            driver.quit();
        }
        catch (RuntimeException e1) {
            System.err.println("Caught0 RuntimeException: " + e1.getMessage());
        }
    }
    @Test
    public void testBasicAuth() throws InterruptedException{

        String email = "koz84075+007@gmail.com";
        String password = "1234567a";

        driver.findElement(By.linkText("SIGN IN")).click();
        driver.findElement(By.xpath("//input[@name='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@name='j_password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@name='submit_login_button']")).sendKeys(Keys.ENTER);

        WebElement resultsElement = driver.findElement(By.xpath("//a[@class='sis-header-profile']"));
        System.out.println("Log In was successful - " + resultsElement.getText());

        WebDriverWait wait1 = new WebDriverWait(driver, 30);
        wait1.until(ExpectedConditions.visibilityOf(resultsElement));

        Actions a = new Actions(driver);
        a.moveToElement(resultsElement).build().perform();
        WebElement outElement = driver.findElement(By.linkText("Sign out"));

        wait1.until(ExpectedConditions.visibilityOf(outElement));

        outElement.click();
        Thread.sleep(1000);
    }
}


