package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class Logintest {
    WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless");
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.get("https://automationexercise.com");
    }
    @DataProvider(name = "Authentication")
    public static Object[][] loginData() {
        return new Object[][] {
                { "clara1234567892@gmail.com", "123456789" },
                { "test2@gmail.com", "123456789" }
        };
    }

    @Test(dataProvider = "Authentication")
    public void loginTest(String user, String pwd) {
        LoginPage login=new LoginPage(driver);
        login.logInPage();
        login.Login(user,pwd);
        if (user.equals("clara1234567892@gmail.com") && pwd.equals("123456789")) {
            // Step 6: Assert Valid Login
            Assert.assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/");
        } else {
            // Step 7: Assert Invalid Login
            Assert.assertTrue(login.isErrorMessageDisplayed());
        }
    }



}
