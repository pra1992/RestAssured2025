Feature: Validate the POST, GET, PUT, PATCH and DELETE for the Incident Table

  Scenario: User retrieves all records from the incident table
    Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
    And user set the basepath "/api/now/table/{tableName}"
    And user set the pathparameter for "tableName" as "incident"
    And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"
    When user hit the GET method
    Then validate user successfully received the response


