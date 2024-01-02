Feature: Post user into the Database
  Scenario: Post successfully a new User into the Database

    Given Ivan is a client that wants to manage his orders
    When he sends the information require for the POST
    Then he will expect a successful response