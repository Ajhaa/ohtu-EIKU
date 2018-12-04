Feature: User can view all items

  Scenario: when one book, internetcontent and podcast exist, viewing all in alphabetical order lists them
    Given book with title "Kiria" and author "Kirijailia" and description "book" is created
    And   content with title "Sivu" and url "www.google.com" and description "content" is created
    And   podcast with name "Jarin tarina" and title "Aamu" and description "podcast" is created
    When  view is selected
    And   "all" is selected
    And   "alphabetical" order is selected
    Then  book "Kiria" and content "Sivu" and podcast "Jarin tarina" are listed