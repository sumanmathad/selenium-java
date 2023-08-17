package com.BrowserActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


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
        driver.findElement(By.cssSelector("label textarea")).sendKeys("text area content");

        /*
        The Selenium WebDriver API does not provide a mechanism to handle file inputs.
        Instead, we should treat input elements for uploading files as regular text inputs, so we need to simulate the user typing them.
        In particular, we need to type the absolute file path to be uploaded.
         */
        driver.findElement(By.cssSelector("test"));

    }
    @Test
    public void navigation() throws IOException, IOException {
        driver.get(bonigarciaTestPage);
        Assert.assertTrue(driver.getTitle().equals("Hands-On Selenium WebDriver with Java"));

        driver.findElement(By.xpath("//a[text()='Navigation']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().startsWith("Lorem ipsum"));

        driver.findElement(By.cssSelector(".page-item.active + li")).click();
        // adjacent combinator locator. It selects the element that is immediately preceded by the former element.
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().contains("commodo consequat"));

        driver.findElement(By.xpath("//a[text()='Next']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().startsWith("Excepteur"));

        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        String filename = tempFile.toAbsolutePath().toString();


    }







    @AfterTest
    public void teardown(){
        //driver.quit();
        //code is commented sometimes to verify the output behaviour after test run completion.
    }



}
