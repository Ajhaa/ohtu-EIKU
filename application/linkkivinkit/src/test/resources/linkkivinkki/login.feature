Feature: User can log in

    Scenario: Logging in with an existing username
        Given user with username "topias" exists
        When "l" is selected
        And username "topias" is entered
        Then confirmation message "User topias logged in" is shown

    Scenario: Logging in with a nonexisting username
        Given "l" is selected
        When invalid username "topias" is entered
        Then error message "Invalid username" is shown to the user
