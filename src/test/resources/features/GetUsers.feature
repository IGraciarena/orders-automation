Feature: Get users from the Database
  Scenario: Get successfully a list of Users by Page into the Database

    Given Ivan is a client that wants to see his users
    When he sends the page number 2 for the GET method
    Then he will expect a successful response with the list of users by Page
    And Ivan uses a user that matches one of the responses