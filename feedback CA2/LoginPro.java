package com.selenium.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class LoginPro {

    public static void main(String[] args) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver",
                "C:\\SEM 6\\DevOps\\NewProj\\chromedriver-win64\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // 🔹 Run all test cases
        
        testLogin(driver, wait, "wrong", "admin123", "INVALID USERNAME");
        testLogin(driver, wait, "admin", "wrong", "INVALID PASSWORD");
        testLogin(driver, wait, "", "", "EMPTY FIELDS");
        testLogin(driver, wait, "admin", "admin123", "VALID TEST");

        driver.quit();
    }

    // 🔹 Common function for login testing
    public static void testLogin(WebDriver driver, WebDriverWait wait,
                                 String username, String password, String testType) throws InterruptedException {

        // Open login page
        driver.get("C:\\SEM 6\\projects\\feedback CA2\\login.html");

        Thread.sleep(1000);

        // Clear fields
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("password")).clear();

        // Enter values
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        Thread.sleep(1000);

        // Click login
        driver.findElement(By.tagName("button")).click();

        // Handle alert
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String message = alert.getText();
        System.out.println(testType + " → " + message);

        alert.accept();

        Thread.sleep(1000);
    }
}