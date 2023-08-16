package com.BrowserActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class WebDriverBasics {
    WebDriver driver ;
    //practice web pages
    String herokuTestPage = "https://the-internet.herokuapp.com/";
    String bonigarciaTestPage = "https://bonigarcia.dev/selenium-webdriver-java/";

    @BeforeTest
        public void setUp(){
        WebDriver browserrdriver = WebDriverManager.chromedriver().avoidShutdownHook().create();
        this.driver=browserrdriver;
    }

    @Test
    public void webForm(){
        driver.get(bonigarciaTestPage);
        Assert.assertTrue(driver.getTitle().equals("Hands-On Selenium WebDriver with Java"));

        driver.findElement(By.cssSelector(".card-body [href='web-form.html']")).click();
        driver.findElement(By.cssSelector("#my-text-id")).sendKeys("This is a string text.");
        driver.findElement(By.cssSelector("input[name='my-password']")).sendKeys("Pass12345#");
        driver.findElement(By.cssSelector("label textarea")).sendKeys("test 1" +" test2");


    }


    @AfterTest
    public void teardown(){
        //driver.quit();
    }



}
