package com.BrowserActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class MouseActions {
    WebDriver driver ;
    String herokuTestPage = "https://the-internet.herokuapp.com/";
    @BeforeTest
        public void setUp(){
        WebDriver browserrdriver = WebDriverManager.chromedriver().avoidShutdownHook().create();
        this.driver=browserrdriver;
    }


    @Test
    public void HoverTest(){
        driver.get(herokuTestPage);
        Assert.assertTrue(driver.getTitle().equals("The Internet"));
        driver.findElement(By.cssSelector("a[href='/hovers']")).click();
    }


    @AfterTest
    public void teardown(){
        driver.quit();
    }



}
