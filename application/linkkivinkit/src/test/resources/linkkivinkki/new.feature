Feature: User can create new items

  Scenario: Adding a book with valid title and author
    Given a user is logged in
    And add is selected
    When book is selected
    And title "testikirja", author "testaaja" and an empty description are entered
    Then amount of books should be 1

  Scenario: Adding a book with valid title and author and description
    Given a user is logged in
    And add is selected
    When book is selected
    And title "testikirja", author "testaaja" and description "testikuvaus" are entered
    Then amount of books should be 1

  Scenario: Adding an internetcontent with valid title and url
    Given a user is logged in
    And add is selected
    When internetcontent is selected
    And title "testisisalto", url "testi.url" and an empty description are entered
    Then amount of internetcontents should be 1

  Scenario: Adding an internetcontent with valid title and url and description
    Given a user is logged in
    And add is selected
    When internetcontent is selected
    And title "testisisalto", author "testi.url" and description "testikuvaus" are entered
    Then amount of internetcontents should be 1

  Scenario: Adding a podcast with valid name and title
    Given a user is logged in
    And add is selected
    When podcast is selected
    And name "testipodcast", title "testinimi" and an empty description are entered
    Then amount of podcasts should be 1

  Scenario: Adding a podcast with valid title and url and description
    Given a user is logged in
    And add is selected
    When podcast is selected
    And name "testipodcast", title "testinimi" and description "testikuvaus" are entered
    Then amount of podcasts should be 1
