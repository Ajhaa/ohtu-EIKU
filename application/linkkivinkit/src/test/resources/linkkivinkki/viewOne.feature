Feature: user can view single item 

    Scenario: correct information is show when viewing existing book
        Given book with title "Kirja" and author "Testaaja" and description "This is a book" is created
        When "view" is selected
        And "book" is selected
        And "1" is selected
        Then output contains "1 Kirja by Testaaja"
        And output contains "Description: This is a book"


    