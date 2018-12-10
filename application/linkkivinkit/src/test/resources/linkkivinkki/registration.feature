Feature: A new user can be registered

  Scenario: Registering with a valid username (length <= 25)
    Given "r" is selected
    When username "tama-on-validi-tunnukseni" is entered
    Then amount of users should be 1
