package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.suite.api.*;

public class LoginTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // No need to set property for chromedriver, if it's in C:/windows/system32/
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void testCorrectLogin() {
        // Test for correct login
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("tomsmith");
        passwordInput.sendKeys("SuperSecretPassword!");
        loginButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector(".flash.success"));
        assertTrue(successMessage.getText().contains("You logged into a secure area!"));
    }

    @Test
    public void testIncorrectLogin() {
        // Test for incorrect login
        WebElement usernameInput = driver.findElement(By.id("username"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.cssSelector("button[type='submit']"));

        usernameInput.sendKeys("неправильный_логин");
        passwordInput.sendKeys("неправильный_пароль");
        loginButton.click();

        WebElement errorMessage = driver.findElement(By.cssSelector(".flash.error"));
        assertTrue(errorMessage.getText().contains("Your username is invalid!"));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}