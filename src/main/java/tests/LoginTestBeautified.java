package tests;

import org.junit.Test;
import org.openqa.selenium.By;
import utils.TestBase;


public class LoginTestBeautified extends TestBase {


    @Test
    public void successLogin() {
        doLogin("standard_user", "secret_sauce");
        driver.findElement(By.id("shopping_cart_container"));
    }

    @Test
    public void loginAsBlockedUser() {
        fillInTheLoginForm("locked_out_user");
        clickLoginButton();
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Sorry, this user has been locked out.')]"));
    }

    @Test
    public void loginAsInvalidUser() {
        fillInTheLoginForm("invalid_user");
        clickLoginButton();
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Username and password do not match any user in this service')]"));
    }

    @Test
    public void loginWithoutUsername() {
        fillInTheLoginForm("");
        clickLoginButton();
        verifyErrorIsDisplayed();
    }

    @Test
    public void loginWithoutPassword() {
        fillInTheLoginForm("standard_user", "");
        clickLoginButton();
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: Password is required')]"));
    }

    public void insertUsername(String username){
        driver.findElement(By.id("user-name")).sendKeys(username);
    }

    public void insertPassword(String password){
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void doLogin(String username, String password){
        fillInTheLoginForm(username, password);
        clickLoginButton();
    }

    public void fillInTheLoginForm(String username, String password){
        insertUsername(username);
        insertPassword(password);
    }

    public void fillInTheLoginForm(String username){
        insertUsername(username);
        insertPassword("secret_sauce");
    }

    public void clickLoginButton(){
        driver.findElement(By.cssSelector(".submit-button")).click();
    }

    public void verifyErrorIsDisplayed(){
        driver.findElement(By.xpath("//*[contains(text(), 'Epic sadface: ')]"));
    }
}
