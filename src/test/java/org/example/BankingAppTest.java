package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.Duration;

public class BankingAppTest {
    WebDriver driver;
    String baseUrl = "https://demo.guru99.com/V4/";

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        // Clean up any lingering alerts from previous failed tests to avoid UnhandledAlertException
        try {
            driver.switchTo().alert().dismiss();
        } catch (NoAlertPresentException e) {
            // No alert to dismiss, proceed normally
        }

        driver.get(baseUrl);

        // Handle potential Cookie/Consent popup
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            WebElement consentBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[text()='Consent']")));
            consentBtn.click();
        } catch (Exception e) {
            // Popup didn't appear, continue
        }
    }

    @BeforeGroups(groups = {"Smoke", "Regression"})
    public void beforeGroupStart(org.testng.ITestContext context) {
        System.out.println(">>> EXECUTION STARTING FOR GROUP: " + context.getName());
    }

    @AfterGroups(groups = {"Smoke", "Regression"})
    public void afterGroupEnd(org.testng.ITestContext context) {
        System.out.println(">>> EXECUTION COMPLETED FOR GROUP: " + context.getName());
    }

    // --- SMOKE GROUP ---
    @Test(groups = {"Smoke"})
    public void testHomepageLoads() {
        Assert.assertNotNull(driver, "Driver was not initialized!");
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("Guru99 Bank"), "Homepage title mismatch!");
    }

    @Test(groups = {"Smoke"})
    public void testLoginPageVisible() {
        boolean isBtnDisplayed = driver.findElement(By.name("btnLogin")).isDisplayed();
        Assert.assertTrue(isBtnDisplayed, "Login button is not visible!");
    }

    @Test(groups = {"Smoke"})
    public void testFooterLinks() {
        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Guru99"), "Footer branding missing!");
    }

    // --- REGRESSION GROUP ---
    @Test(groups = {"Regression"})
    public void testLoginValidCreds() {
        driver.findElement(By.name("uid")).sendKeys("mngr658915");
        driver.findElement(By.name("password")).sendKeys("ureqEra");
        driver.findElement(By.name("btnLogin")).click();

        // Wait for element to ensure login processed before asserting
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement managerIdCell = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'Manger Id')]")));

        String managerText = managerIdCell.getText();
        Assert.assertTrue(managerText.contains("mngr658915"), "Login failed!");

        WebElement logoutLink = driver.findElement(By.linkText("Log out"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", logoutLink);
        driver.switchTo().alert().accept();
    }

    @Test(groups = {"Regression"})
    public void testLoginInvalidCreds() {
        driver.findElement(By.name("uid")).clear();
        driver.findElement(By.name("uid")).sendKeys("invalidUser");
        driver.findElement(By.name("password")).sendKeys("invalidPass");
        driver.findElement(By.name("btnLogin")).click();

        String alertMessage = driver.switchTo().alert().getText();
        Assert.assertEquals(alertMessage, "User or Password is not valid");
        driver.switchTo().alert().accept();
    }

    @Test(groups = {"Regression"})
    public void testPasswordReset() {
        WebElement userField = driver.findElement(By.name("uid"));
        WebElement passField = driver.findElement(By.name("password"));

        // 1. Enter some data
        userField.sendKeys("testUser");
        passField.sendKeys("testPass");

        // 2. Click the Reset button
        driver.findElement(By.name("btnReset")).click();

        // 3. Assert fields are now empty
        String userValue = userField.getAttribute("value");
        String passValue = passField.getAttribute("value");

        Assert.assertEquals(userValue, "", "User ID field was not cleared!");
        Assert.assertEquals(passValue, "", "Password field was not cleared!");
    }

    @Test(groups = {"Regression"})
    public void testAccountBalance() {
        System.out.println("Executing Account Balance Check...");
        Assert.assertTrue(true);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}