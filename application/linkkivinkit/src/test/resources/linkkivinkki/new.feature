Feature: User can create new items

    Scenario: something works
        Given testi
        When something happens
        Then everything is awesome

    Scenario: Adding a book with valid title and author
        Given add is selected
        When book is selected
        And title "testikirja" and author "testaaja" are entered
        Then amount of books should be 1    