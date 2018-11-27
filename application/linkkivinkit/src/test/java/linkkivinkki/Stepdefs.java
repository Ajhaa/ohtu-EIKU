package linkkivinkki;

import cucumber.api.PendingException;
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
    Dao icDao = new InMemoryDao();
    Dao pDao = new InMemoryDao();
    List<String> inputLines = new ArrayList<>();

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
        
    @When("^internetcontent is selected$")
    public void internetcontent_is_selected() throws Throwable {
        inputLines.add("internetcontent");
    }

    @When("^podcast is selected$")
    public void podcast_is_selected() throws Throwable {
        inputLines.add("podcast");
    }

    @When("^title \"([^\"]*)\", author \"([^\"]*)\" and an empty description are entered")
    public void titleAndAuthorEntered(String title, String author) {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add("");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^title \"([^\"]*)\", author \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void title_author_and_description_are_entered(String title, String author, String description) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add(description);
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^title \"([^\"]*)\", url \"([^\"]*)\" and an empty description are entered$")
    public void title_url_and_an_empty_description_are_entered(String title, String url) throws Throwable {
        inputLines.add(title);
        inputLines.add(url);
        inputLines.add("");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^name \"([^\"]*)\", title \"([^\"]*)\" and an empty description are entered$")
    public void name_title_and_an_empty_description_are_entered(String name, String title) throws Throwable {
        inputLines.add(name);
        inputLines.add(title);
        inputLines.add("");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^name \"([^\"]*)\", title \"([^\"]*)\" and description \"([^\"]*)\" are entered$")
    public void name_title_and_description_are_entered(String name, String title, String description) throws Throwable {
        inputLines.add(name);
        inputLines.add(title);
        inputLines.add(description);
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @Then("^amount of books should be 1$")
    public void amountOfBooksOne() {
        assertEquals(bookDao.findAll().size(), 1);
    }

    @Then("^amount of internetcontents should be 1$")
    public void amount_of_internetcontents_should_be() throws Throwable {
        assertEquals(1, icDao.findAll().size());
    }

    @Then("^amount of podcasts should be 1$")
    public void amount_of_podcasts_should_be() throws Throwable {
        assertEquals(1, pDao.findAll().size());
    }

}
