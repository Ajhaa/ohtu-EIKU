package linkkivinkki;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import linkkivinkki.app.App;
import linkkivinkki.dao.InMemoryDao;
import linkkivinkki.dao.Dao;
import linkkivinkki.io.StubIO;
import linkkivinkki.domain.Book;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


public class Stepdefs {
    int testi;
    App app;
    StubIO io;
    Dao bookDao = new InMemoryDao();
    List<String> inputLines = new ArrayList<>();


    @Given("^testi$")
    public void testi() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        testi = 2;
    }

    @Given("^add is selected$")
    public void addIsSelected() {
        inputLines.add("add");
    }

    @Given("^book with title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" is created")
    public void bookIsCreated(String title, String author, String desc) {
        bookDao.add(new Book(title, author, desc));
    }

    @When("^book is selected$")
    public void bookIsSelected() {
        inputLines.add("book");
    }

    @When("^\"([^\"]*)\" is selected$")
    public void somethingIsSelected(String s) {
        inputLines.add(s);
    }

    @When("^title \"([^\"]*)\", author \"([^\"]*)\" and an empty description are entered")
    public void titleAndAuthorEntered(String title, String author) {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add("");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, new InMemoryDao(), new InMemoryDao());
        app.start();
    }

    @Then("^amount of books should be 1$")
    public void amountOfBooksOne() {
        assertEquals(bookDao.findAll().size(), 1);
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