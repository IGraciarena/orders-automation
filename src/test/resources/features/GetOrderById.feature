Feature: Information get individual order

  Scenario: The user send the orderId to retrieve the order detail
    When Call Get Order API
    Then Should see the code 200