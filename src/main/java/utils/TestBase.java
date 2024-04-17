package utils;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static java.lang.Thread.sleep;

public class TestBase {
    public WebDriver driver;

    @Before
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //Open Sauce Demo Login page
        driver.get("https://www.saucedemo.com/");
        sleep(2000);
    }

    @After
    public void shutDown() {
        driver.close();
        // closing the window
        driver.quit();
        // kills the process
    }
}
