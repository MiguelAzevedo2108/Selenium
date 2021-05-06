package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.Assert;


//Using TestNG framework. There are some feature in TestNG that are not available in JUnit
public class PositiveTests {

    @Test
    public void loginTest(){
        System.out.println("Starting..");

        //Create driver
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
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
        password.sendKeys("SuperSecretPassword!");

        //click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();


        //verifications:

        //new url
        String expectedURL = "http://the-internet.herokuapp.com/secure";
        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL,expectedURL, "Actual page URL is not the same as expected");

        //logout button is visible
        WebElement logOutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logOutButton.isDisplayed(),"Log out button is not visible");

        //successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("div#flash"));
        String expectedMessage = "aYou logged into a secure area!";
        String actualMessage = successMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message does not contain expected.\nActual Message: "+actualMessage+ "\nExpected Message: "+ expectedMessage);

        //Close Browser
        driver.quit();
    }

    private void sleep(long ms) {
        //sleep slow down
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
