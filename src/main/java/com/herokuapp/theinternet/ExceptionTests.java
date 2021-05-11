package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;

public class ExceptionTests {

    //Using TestNG framework. There are some feature in TestNG that are not available in JUnit

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

            //implicit wait
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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


