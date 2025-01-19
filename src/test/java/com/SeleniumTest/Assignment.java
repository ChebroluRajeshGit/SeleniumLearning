package com.SeleniumTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assignment {
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
 
		try {
			// Open the URL
			driver.get("https://d3pv22lioo8876.cloudfront.net/tiptop/");

			// Test Case 1: Verify the input element is disabled
			WebElement disabled_input = driver.findElement(By.xpath(".//input[@name='my-disabled']"));
			assertFalse(disabled_input.isEnabled(), "The input is disabled");

			// Test Case 2: Verify readonly input
			WebElement readonly_input1 = driver.findElement(By.xpath(".//input[@value='Readonly input']"));

			// Test Case 3: Verify dropdown has 8 elements
			WebElement color_dropdown = driver.findElement(By.xpath(".//select[@name='my-select']"));
			List<WebElement> options1 = color_dropdown.findElements(By.tagName("option"));
			List<WebElement> options2 = driver.findElements(By.xpath(".//select[@name='my-select']/option"));
			assertEquals(options1.size() == 8, true);
			assertEquals(options2.size(), 8);
			System.out.println("URL: ....");

			// Test Case 4: Verify submit button is disabled when Name is empty
			WebElement submit_button = driver.findElement(By.xpath(".//button[@type='submit']"));
			assertFalse(submit_button.isEnabled(), "Submit button should be disabled");

			// Test Case 5: Verify submit button is enabled when Name and Password are
			// provided
			WebElement name_input = driver.findElement(By.xpath(".//input[@name='my-name']"));
			WebElement password_input = driver.findElement(By.xpath(".//input[@name='my-password']"));
			name_input.sendKeys("TestName99");
			password_input.sendKeys("TestPassword");
			assertTrue(submit_button.isEnabled(), "Submit button is enabled");

			// Test Case 6: Verify 'Received' text appears on form submission
			submit_button.click();

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("message")));

			WebElement verifytext = driver.findElement(By.id("message"));
			assertEquals(verifytext.getText(), "Received!");

			// Test Case 7: Verify form data is submitted to URL (Example verification)
			String URL = driver.getCurrentUrl();
			assertEquals(URL,
					"https://d3pv22lioo8876.cloudfront.net/tiptop/submitted.html?my-name=TestName&my-password=TestPassword&my-readonly=Readonly+input&my-select=white");
		
		} finally {
			driver.quit();
		}

	}
}
