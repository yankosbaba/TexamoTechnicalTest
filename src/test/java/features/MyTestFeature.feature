Feature: As a Go rest user, I should be able to create a new user, check for duplicates and invalid entries

  Scenario: Verify User Created Successfully and no duplicate entries
    Given I setup My HTTPBuilder and its headers
    And I access the endpoint 'users' and set request POST method with valid data
    When I build the request
    And I send the request to the server
    Then I validate that user was created if it exists and the returned status is 201
    When I access the endpoint 'users' and set request POST method with valid data retry
    When I build the request
    And I send the request to the server
    Then I validate that user was created if it exists and the returned status is 422

  Scenario: Verify that userData cannot be created with invalid email
    Given I setup My HTTPBuilder and its headers
    And I access the endpoint 'users' and set request POST method with invalid data
    When I build the request
    When I send the request to the server
    Then I validate that user was created if it exists and the returned status is 422