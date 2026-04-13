package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignUpTest {
    WebDriver driver;
    @BeforeTest
    public void openWebSite(){
        driver=new ChromeDriver();
        driver.get("https://automationexercise.com");
        driver.manage().window().maximize();

    }
    @Test(priority = 1)
    public void signUp() throws InterruptedException {
        SignUpPage signUp=new SignUpPage(driver);
        signUp.SignInPage();
        signUp.SignIn_from_loginPage("clara1234567892@gmail.com","Clara");
        signUp.SignInForm("123456789","Clara","Akmal","elSherif street","daher","cairo","1238754","01254798224");
        signUp.SignInBtnClick();
//        Thread.sleep(5000);
//        signUp.alertHandling();
        signUp.continueBtn();
    }

    @Test(priority = 2)
    public void logOut(){
        LogOutPage logOut=new LogOutPage(driver);
        logOut.Logout();
    }

    @Test(priority = 3)
    public void Login(){
        LoginPage login=new LoginPage(driver);
        login.logInPage();
        login.Login("clara1234567892@gmail.com","123456789");
    }
    @Test(priority = 4)
    public void addToCart() throws InterruptedException {
        AddToCartPage add =new AddToCartPage(driver);
        add.ViewProduct();
        add.AddToCart();
        Thread.sleep(3000);
        add.continueBTN();
    }

    @AfterTest
    public void LogOut(){
        LogOutPage logOut=new LogOutPage(driver);
        logOut.Logout();
    }

}
