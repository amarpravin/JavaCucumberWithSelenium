package com.tutorial;

import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Created by prkum on 08-07-2017.
 */
public class Hooks {
    @Before
    public void setup() {
        new DriverManager().openBrowser();
    }

    @After
    public void after() {
        new DriverManager().closeBrowser();
    }
}
