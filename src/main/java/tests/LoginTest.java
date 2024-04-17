package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import utils.TestBase;

import static java.lang.Thread.sleep;

public class LoginTest extends TestBase {

    @Test
    public void successLogin() throws InterruptedException{
        //Find element by ID user-name and set valid username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        // Find element by ID password and set valid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        // Find Login button by class and click on it
        driver.findElement(By.cssSelector(".submit-button")).click();
        //Assert that user is logged in, if this line missing, we can log in with invalid credentials as well
        driver.findElement(By.id("shopping_cart_container"));
        //gives current url System.out.println("URL: " + driver.getCurrentUrl());
        sleep(2000);
        // its no good to put sleep commands, only good when studying the process
        driver.navigate().back();
        driver.findElement(By.cssSelector(".submit-button"));
        sleep(2000);

    }

    @Test
    public void fastSuccessfulLogin(){
        insertUsername("standard_user");
        insertPassword("secret_sauce");
        clickLoginButton();
    }



    @Test
    public void loginAsBlockedUser() throws InterruptedException{
        //Find element by ID user-name
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.cssSelector(".submit-button")).click();
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]"));
        sleep(2000);
    }

    @Test
    public void loginAsInvalidUser() {
        //Find element by ID 'user-name' and set valid username
        driver.findElement(By.id("user-name")).sendKeys("invalid_username");
        //Find element by ID 'password' and set valid password
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        //Find Login button by CLASS and click on it
        driver.findElement(By.cssSelector(".submit-button")).click();
        //"User is blocked" error message should be displayed
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]"));
    }

    public void insertUsername(String username){
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    public void insertPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(By.cssSelector(".submit-button")).click();
    }
}
