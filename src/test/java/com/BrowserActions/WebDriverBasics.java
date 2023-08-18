package com.BrowserActions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


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
    public void webForm() throws IOException {
        driver.get(bonigarciaTestPage);
        Assert.assertTrue(driver.getTitle().equals("Hands-On Selenium WebDriver with Java"));

        driver.findElement(By.cssSelector(".card-body [href='web-form.html']")).click();
        driver.findElement(By.cssSelector("#my-text-id")).sendKeys("This is a string text.");
        driver.findElement(By.cssSelector("input[name='my-password']")).sendKeys("Pass12345#");
        driver.findElement(By.cssSelector("label textarea")).sendKeys("text area content");

        /*
        **** File uploading ****
        The Selenium WebDriver API does not provide a mechanism to handle file inputs.
        Instead, we should treat input elements for uploading files as regular text inputs, so we need to simulate the user typing them.
        In particular, we need to type the absolute file path to be uploaded.
         */

        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        String filename = tempFile.toAbsolutePath().toString();
        driver.findElement(By.cssSelector("input[name='my-file']")).sendKeys(filename);

        //Range Slider

       WebElement slider = driver.findElement(By.cssSelector("input[name='my-range']"));
       for(int i=0; i<2; i++) {
           slider.sendKeys(Keys.ARROW_RIGHT);
       }
        Assert.assertEquals(slider.getAttribute("value"),"7");

       /*
       Date Picker made from BootStrap - Select last year, same date
        */
        LocalDate today = LocalDate.now();
        int dateToday = today.getDayOfMonth();
        int yearToday =today.getYear();

        driver.findElement(By.cssSelector("input[name='my-date']")).click();
        WebElement yearMonthStrip = driver.findElement(By.xpath(String.format("//th[contains(text(),'%d')]",yearToday)));
        yearMonthStrip.click();
        WebElement prevButton = driver.findElement(RelativeLocator.with(By.tagName("th")).toRightOf(yearMonthStrip));
        prevButton.click();
        WebElement monthButton = driver.findElement(RelativeLocator.with(By.xpath("//span[@class='month focused']")).below(yearMonthStrip));
        monthButton.click();
        WebElement dayButton = driver.findElement(By.xpath(String.format("//td[@class='day' and contains(text(),'%d')]",dateToday)));
        dayButton.click();

        LocalDate previousYear = today.minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='my-date']")).getAttribute("value"),expectedDate);








    }
    @Test
    public void navigation()  {
        driver.get(bonigarciaTestPage);
        Assert.assertTrue(driver.getTitle().equals("Hands-On Selenium WebDriver with Java"));

        // Pagination pages.
        driver.findElement(By.xpath("//a[text()='Navigation']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().startsWith("Lorem ipsum"));

        driver.findElement(By.cssSelector(".page-item.active + li")).click();
        // adjacent combinator locator. It selects the element that is immediately preceded by the former element.
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().contains("commodo consequat"));

        driver.findElement(By.xpath("//a[text()='Next']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("body p")).getText().startsWith("Excepteur"));





    }







    @AfterTest
    public void teardown(){
        //driver.quit();
        //code is commented out sometimes to verify the output behaviour after test run completion.
    }



}
