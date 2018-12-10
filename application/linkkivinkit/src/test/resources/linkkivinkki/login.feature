Feature: logged in users can see only their own items

    Scenario: users cannot see other users data
        Given a user is logged in
        And book with title "Kiria" and author "Kirijailia" and description "book" is created
        When user logs out
        And another user is logged in
        And view is selected
        And "book" is selected
        And id "1" is entered
        Then error message "Invalid command." is shown to the user

