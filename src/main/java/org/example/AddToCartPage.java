package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class AddToCartPage {
    WebDriver driver;

    @FindBy(xpath = "/html/body/section[2]/div/div/div[2]/div[1]/div[2]/div/div[2]/ul/li/a")
    WebElement viewProduct;

    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[2]/div[2]/div/span/button")
    WebElement AddTocart;


    @FindBy(xpath = "/html/body/section/div/div/div[2]/div[1]/div/div/div[3]/button")
    WebElement continuebtn;

    public AddToCartPage(WebDriver d) {
        this.driver = d;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 15);
        PageFactory.initElements(factory, this);
    }

    public void ViewProduct(){
        viewProduct.click();
    }
    public void AddToCart(){
        AddTocart.click();
    }
    public void continueBTN(){
        continuebtn.click();
    }

}
