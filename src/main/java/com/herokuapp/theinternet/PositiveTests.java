package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


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


        //enter password
        //click login button


//        verifications:
//        new url
//        logout button is visible
//        succesful login message

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
