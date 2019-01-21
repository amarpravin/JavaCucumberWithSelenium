package com.tutorial;

import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

/**
 * Created by prkum on 08-07-2017.
 */
public class Elements extends DriverManager{
    public void searchText(String searchString){
        driver.findElement(By.xpath("//input[@aria-label='Search']")).sendKeys(searchString);
        driver.findElement(By.name("btnK")).click();
    }

    public boolean isOnHomePage() {
        String title = driver.getTitle();
        Assert.assertEquals("Title does not have Google","Google", title);
        return  true;
    }

    public boolean isResultFound() {
        String result = driver.findElement(By.id("resultStats")).getText();
        Assert.assertTrue("result not found", Integer.parseInt(result.split(" ")[1].replace(",", "")) > 1);
        return true;
    }
}
