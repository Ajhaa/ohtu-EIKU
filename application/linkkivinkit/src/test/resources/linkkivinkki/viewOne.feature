Feature: User can view single item

  Scenario: correct information is show when viewing existing book
    Given a user is logged in
    And book with title "Kirja" and author "Testaaja" and description "This is a book" is created
    When view is selected
    And "book" is selected
    And id "1" is entered
    Then the information of the book is shown

  Scenario: correct information is shown when viewing existing internetcontent
    Given a user is logged in
    And content with title "Sisalto" and url "www.url" and description "This is a description" is created
    When view is selected
    And "internetcontent" is selected
    And id "1" is entered
    Then the information of the content is shown

  Scenario: correct information is shown when viewing existing podcast
    Given a user is logged in
    And podcast with name "Nimi" and title "Otsikko" and description "This is a description" is created
    When view is selected
    And "podcast" is selected
    And id "1" is entered
    Then the information of the podcast is shown  

  Scenario: error message is shown when attempting to view nonexistent book
    Given a user is logged in
    And view is selected
    When "book" is selected
    And id "1" is entered
    Then error message "Invalid command." is shown to the user

  Scenario: error message is shown when attempting to view nonexistent internetcontent
    Given a user is logged in
    And view is selected
    When "internetcontent" is selected
    And id "1" is entered
    Then error message "Invalid command." is shown to the user

  Scenario: error message is shown when attempting to view nonexistent podcast
    Given a user is logged in
    And view is selected
    When "podcast" is selected
    And id "1" is entered
    Then error message "Invalid command." is shown to the user
