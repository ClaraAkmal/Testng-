package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignUpPage {
    WebDriver driver;

    @FindBy(xpath = "//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")
    WebElement SignUpLoginbtn;


    @FindBy(name = "name")
    WebElement SignUpName;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/input[3]")
    WebElement SignUpemail;

    @FindBy(xpath = "//*[@id=\"form\"]/div/div/div[3]/div/form/button")
    WebElement SignUpBtn;


    @FindBy(xpath = "//*[@id=\"id_gender2\"]")
    WebElement Title;

    @FindBy(xpath = "//*[@id=\"password\"]")
    WebElement pass;

    @FindBy(xpath = "//*[@id=\"first_name\"]")
    WebElement firstname;

    @FindBy(xpath = "//*[@id=\"last_name\"]")
    WebElement lastname;

    @FindBy(xpath = "//*[@id=\"address1\"]")
    WebElement addr;

    @FindBy(xpath = "//*[@id=\"state\"]")
    WebElement state;

    @FindBy(xpath = "//*[@id=\"city\"]")
    WebElement city;

    @FindBy(xpath = "//*[@id=\"zipcode\"]")
    WebElement code;

    @FindBy(xpath = "//*[@id=\"mobile_number\"]")
    WebElement phone;

    @FindBy(xpath = "/html/body/section/div/div/div/div[1]/form/button")
    WebElement btn;

    @FindBy(xpath = "/html/body/section/div/div/div/div/a")
    WebElement continuebtn;

    public SignUpPage(WebDriver d) {
        this.driver = d;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }
    public void SignInPage(){
        SignUpLoginbtn.click();
    }
    public void SignIn_from_loginPage(String email,String name){
        SignUpName.sendKeys(name);
        SignUpemail.sendKeys(email);
        SignUpBtn.click();
    }
    public void SignInForm(String pass,String firstName,String lastname,String addrr,String state , String city , String code, String phone){
        Title.click();
        this.pass.sendKeys(pass);
        this.firstname.sendKeys(firstName);
        this.lastname.sendKeys(lastname);
        this.addr.sendKeys(addrr);
        this.state.sendKeys(state);
        this.city.sendKeys(city);
        this.code.sendKeys(code);
        this.phone.sendKeys(phone);
    }
    public void SignInBtnClick(){
        btn.click();
    }
    public void alertHandling(){
        Alert a=driver.switchTo().alert();
        a.accept();

    }
    public void continueBtn(){
        continuebtn.click();
    }
}
