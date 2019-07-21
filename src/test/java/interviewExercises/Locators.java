package interviewExercises;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Locators extends BaseTests {
    String URL = "https://www.google.com/";
    // ID
    @Test
    public void testID() throws InterruptedException{
        openPage(URL);
        //inspect <a ... id="gb_70" ...>Sign in</a>
        //Value to be added in the By.id method: "gb_70"
        driver.findElement(By.id("gb_70")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }
}
