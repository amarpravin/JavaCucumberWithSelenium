package com.tutorial.StepsDef;

import com.tutorial.Elements;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by prkum on 08-07-2017.
 */
public class googleSearchStepDef {
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
}
