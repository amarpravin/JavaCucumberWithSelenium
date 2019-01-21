# JavaCucumberWithSelenium for Beginner
Automation Testing Using Java Cucumber and Selenium for Beginner

# Install Java
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

# Install IntelliJ IDEA Editor 
In this tutorial I am using InetlliJ, we need to install IntelliJ Editor first if not installed into your system. You can install editor from :-
https://www.jetbrains.com/idea/download/#section=windows

# To Create a new project
Click on "Create New Projet" or go to File -> New -> Project

Under New Project -> Select "Maven" -> Project SDK 1.8 (if 1.8 is not showing select java sdk path by clicking New button) -> 
Checked "Create from archetype" -> select "org.apache.maven.archetypes:mvn-archetype-quickstart" from list -> Click on Next button. 
Enter below field information :-

GroupId : com.tutorial

ArtifactId : javaCucumber

Version : 1.0-SNAPSHOT


Click on "Next" Button -> Next -> Provide "Project Name" and "Project Location" -> Click on Finish button.

It will create project structure like this :-
```
<Project Name>
    .idea
    src
      main
        java  
          com.tutorial
            App
      test
        java
          com.tutorial
            AppTest
      JavaCucumberWithSelenium.iml
      pom.xml
```
Now open pom.xml file and add below dependency:-
```
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>2.52.0</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/info.cukes/cucumber-java -->
    <dependency>
      <groupId>info.cukes</groupId>
      <artifactId>cucumber-java</artifactId>
      <version>1.2.4</version>
    </dependency>
    <!-- https://mvnrepository.com/artifact/info.cukes/cucumber-junit -->
    <dependency>
      <groupId>info.cukes</groupId>
      <artifactId>cucumber-junit</artifactId>
      <version>1.2.4</version>
      <scope>test</scope>
    </dependency>
    <!-- https://mvnrepository.com/artifact/info.cukes/cucumber-core -->
    <dependency>
      <groupId>info.cukes</groupId>
      <artifactId>cucumber-core</artifactId>
      <version>1.2.4</version>
    </dependency>
```

Validate below package should be installed if not then install.
Go to File -> Settings -> Plugins ->

Type Cucumber and check "Cucumber for Groovy" and "Cucumber for Java" should be checked.
Type Gherkin and check "Gherkin" should be checked.

Delete "App.java" and "AppTest.java" file.

Go to src\main\java\com\tutorial path and create below java files
 1. DriverManager.java
 
    Add below code inside the class :-
    ```
    public static WebDriver driver;

    public void openBrowser(){
        //Download Webdriver from http://www.seleniumhq.org/download/ and provide unzipped exe path
        File file = new File("src/test/resources/seleniumdrivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", file.getAbsoluteFile().toString());
        driver=new ChromeDriver();
        driver.get("https://google.com");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void closeBrowser(){
        driver.quit();
    }
    ```
    
 2. Elements.java
 
    Extends Elements class from DriverManager and create three methods as below  :-
    
  ```
    public class Elements extends DriverManager {
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
  ```
    
Go to src\test\resources path and create below stucture

```
resources (Directory)
  features (Directory)
    search.feature (File)
```

Content of search.feature
```
Feature: Search in Google

Scenario: Search Google
   Given I am home page
   When I search as "Testing with Selenium"
   Then I found results

```

Go to src\test\java\com\tutorial path and create below structure
```
  StepsDef (Package)
    googleSearchStepDef (Java file)
  CucumberRunTest (Java file)
  Hooks (Java file)
```

copy below contents and paste inside googleSearchStepDef.java class
```
  Elements elements;

    public googleSearchStepDef(){
        elements = new Elements();
    }

    @Given("^I am home page$")
    public void i_am_home_page() throws Throwable {
        elements.isOnHomePage();
    }
    @When("^I search as \"([^\"]*)\"$")
    public void i_search_as(String searchString) throws Throwable {
        elements.searchText(searchString);
    }

    @Then("^I found results$")
    public void i_found_results() throws Throwable {
        elements.isResultFound();
    }
```

Copy and paste below contents into CucumberRunTest.java
```
@RunWith(Cucumber.class)
@CucumberOptions(features = ".")
public class CucumberRunTest {
}

```

Copy below contents and paste inside the Hooks.java class
```
package com.tutorial;

import cucumber.api.java.After;
import cucumber.api.java.Before;

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

```

Now go to CucumberRunTest -> Right click -> Click on "Run 'CucumberRunTest'". It should run and pass all your scenarios under features folder.  


