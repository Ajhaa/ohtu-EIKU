Feature: User can view all items

  Scenario: viewing all items in alphabetical order
    Given a user is logged in
    And book with title "Kiria" and author "Kirijailia" and description "book" is created
    And content with title "Sivu" and url "www.google.com" and description "content" is created
    And podcast with name "Jarin tarina" and title "Aamu" and description "podcast" is created
    When view is selected
    And "all" is selected
    And "alphabetical" order is selected
    Then book "Kiria" and content "Sivu" and podcast "Jarin tarina" are listed
    And the order is "Jarin tarina", "Kiria", "Sivu"

  Scenario: viewing all items in order of creation date
    Given a user is logged in
    And book with title "Kiria" and author "Kirijailia" and description "book" is created
    And podcast with name "Jarin tarina" and title "Aamu" and description "podcast" is created
    And content with title "Sivu" and url "www.google.com" and description "content" is created
    When view is selected
    And "all" is selected
    And "date" order is selected
    Then book "Kiria" and content "Sivu" and podcast "Jarin tarina" are listed
    And the order is "Sivu", "Jarin tarina", "Kiria"

  Scenario: viewing all items in a random order
    Given a user is logged in
    And book with title "Kiria" and author "Kirijailia" and description "book" is created
    And podcast with name "Jarin tarina" and title "Aamu" and description "podcast" is created
    And content with title "Sivu" and url "www.google.com" and description "content" is created
    When view is selected
    And "all" is selected
    And "random" order is selected
    Then book "Kiria" and content "Sivu" and podcast "Jarin tarina" are listed

  Scenario: single item can be viewed from all listing
    Given a user is logged in
    And book with title "Kiria" and author "Kirijailia" and description "book" is created
    And content with title "Sivu" and url "www.google.com" and description "content" is created
    And podcast with name "Jarin tarina" and title "Aamu" and description "podcast" is created
    When view is selected
    And "all" is selected
    And "alphabetical" is selected
    And id "1" is entered
    Then the information of the podcast is shown
