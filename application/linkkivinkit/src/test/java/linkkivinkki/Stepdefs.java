package linkkivinkki;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import static org.junit.Assert.*;


public class Stepdefs {
    int testi;

    @Given("^testi$")
    public void testi() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        testi = 2;
    }

    @When("^something happens$")
    public void something_happens() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        testi--;
    }

    @Then("^everything is awesome$")
    public void everything_is_awesome() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(testi, 1);
    }

}