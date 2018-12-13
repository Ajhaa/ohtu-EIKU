Feature: User can log in

    Scenario: users cannot see other users data
        Given a user is logged in
        And book with title "Kiria" and author "Kirijailia" and description "book" is created
        When user logs out
        And another user is logged in
        And view is selected
        And "book" is selected
        And id "1" is entered
        Then error message "Invalid command." is shown to the user

    Scenario: Logging in with an existing username
        Given user with username "topias" exists
        When "l" is selected
        And username "topias" is entered
        Then confirmation message "User topias logged in" is shown

    Scenario: Logging in with a nonexisting username
        Given "l" is selected
        When invalid username "topias" is entered
        Then error message "Invalid username" is shown to the user
