Feature: User can edit saved items

  Scenario: Editing an existing book
    Given book with title "Kirja" and author "Testaaja" and description "This is a book" is created
    When view is selected
    And "book" is selected
    And item with id "-1" is selected
    And "edit" is selected
    And new title "UusiKirja" and author "UusiTestaaja" and an empty description are set
    Then confirmation message "Item was updated successfully" is shown