package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.testng.Assert;


//Using TestNG framework. There are some feature in TestNG that are not available in JUnit
public class LoginTests {
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("chrome") String browser){
        System.out.println("Starting..");

        //Create driver

        switch (browser){
            case "chrome":
                System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver","src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Do not know how to start " + browser + ", starting chrome instead");
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }


        //sleep for 3 seconds just to slow down
        //sleep(3000);

        //maximize browser window
        driver.manage().window().maximize();
    }

    @Test(priority = 1, groups = {"positiveTests", "smokeTests"} )
    public void PositiveTests(){

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
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successMessage.getText();

        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message does not contain expected.\nActual Message: "+actualMessage+ "\nExpected Message: "+ expectedMessage);

        //Close Browser
        tearDown();
    }

    @Parameters({"username","password","expectedMessage"})
    @Test(priority = 2, groups = {"negativeTests", "smokeTests"} )
    public void negativeLoginTest(String username, String password, String expectedErrorMessage){
        System.out.println("Starting IncorrectUsername Test");

        //open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("Page is opened");

        //enter username
        WebElement usernameElement = driver.findElement(By.id("username"));
        usernameElement.sendKeys(username);

        //enter password
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.sendKeys(password);

        //click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //Verifications
        WebElement errorMessage = driver.findElement(By.id("flash"));
        String actualErrorMessage = errorMessage.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Actual error message does not contain Expected \n Actual: "+
                actualErrorMessage + "\n" + "Expected: " + expectedErrorMessage);
        tearDown();

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown(){
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
