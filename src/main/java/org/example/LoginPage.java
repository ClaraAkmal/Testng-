package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement SignUpLoginbtn;


    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div[1]/form/input[2]")
    WebElement LoginEmail;

    @FindBy(name = "password")
    WebElement LoginPass;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[1]/div[1]/form/button")
    WebElement LoginBtn;

    public LoginPage(WebDriver d){
        this.driver=d;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);

    }
    public void logInPage(){
        SignUpLoginbtn.click();
    }
    public void Login(String email,String pass){
        this.LoginEmail.sendKeys(email);
        this.LoginPass.sendKeys(pass);
        this.LoginBtn.click();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            // Use Explicit Wait to give the ad or error time to appear
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'incorrect')]")));
            return error.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
