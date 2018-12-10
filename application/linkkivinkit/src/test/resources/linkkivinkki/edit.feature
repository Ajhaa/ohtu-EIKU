Feature: User can edit saved items

  Scenario: Editing an existing book
    Given a user is logged in
    And book with title "Kirja" and author "Testaaja" and description "This is a book" is created
    When view is selected
    And "book" is selected
    And item with id "1" is selected
    And "edit" is selected
    And new title "UusiKirja" and author "UusiTestaaja" and an empty description are set
    Then confirmation message "Item was updated successfully" is shown

  Scenario: Editing an existing internetcontent
    Given a user is logged in
    And content with title "Sivu" and url "sivu.fi" and description "This is an internetcontent" is created
    When view is selected
    And "internetcontent" is selected
    And item with id "1" is selected
    And "edit" is selected
    And new title "UusiSivu" and url "sivu.fi/yolo" and an empty description are set
    Then confirmation message "Item was updated successfully" is shown

  Scenario: Editing an existing podcast
    Given a user is logged in
    And podcast with name "Podcast" and title "Title" and description "This is a podcast" is created
    When view is selected
    And "podcast" is selected
    And item with id "1" is selected
    And "edit" is selected
    And new name "UusiPodcast" and title "UusiTitle" and an empty description are set
    Then confirmation message "Item was updated successfully" is shown