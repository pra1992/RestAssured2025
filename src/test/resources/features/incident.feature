Feature: Validate the POST, GET, PUT, PATCH and DELETE for the Incident Table

  Background:
    Given user set the baseuri "https://dev214398.service-now.com" of the service now instance
    And user set the pathparameter for "tableName" as "incident"
    And user set the basic authentication for username and password as "admin" and "gz^1@wDVaL3B"

  Scenario: Create an Incident from the Incident Table
    Given user set the basepath "/api/now/table/{tableName}"
    Given user set the header "Content-Type" as "application/json"
    And user gives the Short Description as "Create an Incident using Cucumber"
    And user gives the Description as "Incident using cucumber"
    And user gives Active as "true"
    When user hit the POST method
    Then validate record is successfully created

  Scenario: User retrieves all records from the incident table
    Given user set the basepath "/api/now/table/{tableName}"
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
      Given user set the basepath "/api/now/table/{tableName}/{sys_id}"
      And user set the multiple pathparameters
        | tableName | incident |
      When user hit the GET method
      Then validate user successfully received the response with the correct sysid
        | StatusCode    | 200              |
        | StatusMessage | OK               |
        | Content-Type  | application/json |

  Scenario: Update an Incident from the Incident Table
    Given user set the basepath "/api/now/table/{tableName}"
    Given user set the header "Content-Type" as "application/json"
    And user gives the Short Description as "Update an Incident using Cucumber"
    And user gives the Description as "Update using cucumber"
    And user gives Active as "true"
    When user hit the PUT method
    Then validate record is successfully Updated

  Scenario: user should be able to Update a single record using DataTable
    Given user set the basepath "/api/now/table/{tableName}/{sys_id}"
    And user set the multiple pathparameters
      | tableName | incident |
    When user hit the GET method
    Then validate user successfully received the response with the correct sysid
      | StatusCode    | 200              |
      | StatusMessage | OK               |
      | Content-Type  | application/json |


  Scenario Outline: Create an Incident from the Incident Table
    Given user set the basepath "/api/now/table/{tableName}"
    And user set the header "Content-Type" as "application/json"
    And user gives the Short Description as "<Short Description>"
    And user gives the Description as "<Description>"
    And user gives Active as "<Active>"
    When user hit the POST method
    Then validate record is successfully created
    Examples:
      | Short Description | Description     | Active |
      | Record 1          | Reecord 1 Desc1 | true   |
      | Record 2          | Record 2 Desc2  | true   |




