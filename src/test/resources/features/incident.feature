Feature: Validate the POST, GET, PUT, PATCH and DELETE for the Incident Table

  Scenario: Create an Incident from the Incident Table
    Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
    And user set the basepath "/api/now/table/{tableName}"
    And user set the pathparameter for "tableName" as "incident"
    And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"
    And user gives the payload body
    When user hit the POST method
    Then validate record is successfully created

  Scenario: User retrieves all records from the incident table
    Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
    And user set the basepath "/api/now/table/{tableName}"
    And user set the pathparameter for "tableName" as "incident"
    And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"
    When user hit the GET method
    Then validate user successfully received the response

#  Scenario: User retrieves a single record from the incident table
#    Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
#    And user set the basepath "/api/now/table/{tableName}"
#    And user set the pathparameter for "tableName" as "incident"
#    And user set the pathparameter for "sys_id" as "afa29e2e83a62e10557ae5d0deaad3ef"
#    And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"
#    When user hit the GET method
#    Then validate user successfully received the response
#      | 200 | OK | application/json |

    Scenario: user should be able to retrieve a single record using DataTable
      Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
      And user set the basepath "/api/now/table/{tableName}/{sys_id}"
      And user set the multiple pathparameters
        | tableName | incident                         |
        | sys_id    | afa29e2e83a62e10557ae5d0deaad3ef |
      And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"
      When user hit the GET method
      Then validate user successfully received the response with the correct sysid
        | 200 | OK | application/json | afa29e2e83a62e10557ae5d0deaad3ef |




