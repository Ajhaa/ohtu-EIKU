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
import linkkivinkki.domain.InternetContent;
import linkkivinkki.domain.Item;
import linkkivinkki.domain.Podcast;
import linkkivinkki.io.Color;

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
        somethingIsSelected("add");
    }

    @Given("^book with title \"([^\"]*)\" and author \"([^\"]*)\" and description \"([^\"]*)\" is created$")
    public void bookIsCreated(String title, String author, String desc) {
        bookDao.add(new Book(author, title, desc));
    }

    @Given("^content with title \"([^\"]*)\" and url \"([^\"]*)\" and description \"([^\"]*)\" is created$")
    public void content_with_title_and_url_and_description_is_created(String title, String url, String desc) throws Throwable {
        icDao.add(new InternetContent(title, url, desc));
    }

    @Given("^podcast with name \"([^\"]*)\" and title \"([^\"]*)\" and description \"([^\"]*)\" is created$")
    public void podcast_with_name_and_title_and_description_is_created(String name, String title, String desc) throws Throwable {
        pDao.add(new Podcast(name, title, desc));
    }

    @When("^\"([^\"]*)\" is selected$")
    public void somethingIsSelected(String s) {
        inputLines.add(s);
    }

    @When("^book is selected$")
    public void bookIsSelected() {
        somethingIsSelected("book");
    }

    @When("^internetcontent is selected$")
    public void internetcontent_is_selected() throws Throwable {
        somethingIsSelected("internetcontent");
    }

    @When("^podcast is selected$")
    public void podcast_is_selected() throws Throwable {
        somethingIsSelected("podcast");
    }

    @When("^view is selected$")
    public void view_is_selected() throws Throwable {
        somethingIsSelected("view");
    }

    @When("^edit is selected$")
    public void edit_is_selected() throws Throwable {
        somethingIsSelected("edit");
    }
    
    @When("^\"([^\"]*)\" order is selected$")
    public void order_is_selected(String order) throws Throwable {
        somethingIsSelected(order);
        inputLines.add("quit");
        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }


    @When("^item with id \"([^\"]*)\" is selected$")
    public void item_with_id_is_selected(String id) throws Throwable {
        somethingIsSelected(id);
    }


    @When("^id \"([^\"]*)\" is entered$")
    public void id_is_entered(String id) throws Throwable {
        inputLines.add(id);
        inputLines.add("main");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
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

    @When("^new title \"([^\"]*)\" and author \"([^\"]*)\" and an empty description are set$")
    public void new_title_and_author_and_an_empty_description_are_set(String title, String author) throws Throwable {
        inputLines.add(title);
        inputLines.add(author);
        inputLines.add("");
        inputLines.add("return");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^new title \"([^\"]*)\" and url \"([^\"]*)\" and an empty description are set$")
    public void new_title_and_url_and_an_empty_description_are_set(String title, String url) throws Throwable {
        inputLines.add(title);
        inputLines.add(url);
        inputLines.add("");
        inputLines.add("return");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }

    @When("^new name \"([^\"]*)\" and title \"([^\"]*)\" and an empty description are set$")
    public void new_name_and_title_and_an_empty_description_are_set(String name, String title) throws Throwable {
        inputLines.add(name);
        inputLines.add(title);
        inputLines.add("");
        inputLines.add("return");
        inputLines.add("quit");

        io = new StubIO(inputLines);
        app = new App(io, bookDao, icDao, pDao);
        app.start();
    }



    @Then("^confirmation message \"([^\"]*)\" is shown$")
    public void confirmation_message_is_shown(String message) throws Throwable {
        assertTrue(io.getPrints().contains(Color.greenText(message)));
    }

    @Then("^the information of the podcast is shown$")
    public void the_information_of_the_podcast_is_shown() throws Throwable {
        assertTrue(io.getPrints().contains(pDao.findOne(-1).toString())); // USING ID -1 B/C IT'S THE DEFAULT ID
    }

    @Then("^the information of the content is shown$")
    public void the_information_of_the_content_is_shown() throws Throwable {
        assertTrue(io.getPrints().contains(icDao.findOne(-1).toString())); // USING ID -1 B/C IT'S THE DEFAULT ID
    }

    @Then("^the information of the book is shown$")
    public void the_information_of_the_book_is_shown() throws Throwable {
        assertTrue(io.getPrints().contains(bookDao.findOne(-1).toString())); // USING ID -1 B/C IT'S THE DEFAULT ID
    }

    @Then("^error message \"([^\"]*)\" is shown to the user$")
    public void error_message_is_shown_to_the_user(String error) throws Throwable {
        assertTrue(io.getPrints().contains(Color.redText(error)));
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
    
    @Then("^book \"([^\"]*)\" and content \"([^\"]*)\" and podcast \"([^\"]*)\" are listed$")
    public void book_and_content_and_podcast_are_listed(String book, String content, String podcast) throws Throwable {
        System.out.println(io.getPrints());
        boolean foundBook = false;
        boolean foundContent = false;
        boolean foundPodcast = false;
        for (String line : io.getPrints()) {
            if (line.contains(book)) {
                foundBook = true;
            }
            if (line.contains(content)) {
                foundContent = true;
            }
            if (line.contains(podcast)) {
                foundPodcast = true;
            }
        }
        
        assertTrue(foundBook && foundContent && foundPodcast);
    }


}
