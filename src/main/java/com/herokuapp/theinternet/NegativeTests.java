package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {



    @Test(priority = 2, enabled = false, groups = {"negativeTests"})
   /* public void incorrectPasswordTest() {
        System.out.println("Starting IncorrectPassword Test");

        //Create driver
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        //sleep for 3 seconds just to slow down
        sleep(3000);

        //maximize browser window
        driver.manage().window().maximize();

        //open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

        //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

        //enter password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("IncorrectPassword!");

        //click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //Verifications
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage), "Actual error message does not contain Expected \n Actual: " +
                actualErrorMessage + "\n" + "Expected: " + expectedErrorMessage);

        driver.quit();
    }*/

    private void sleep(long ms) {
        //sleep slow down
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
