Feature: Search, create, update and delete people

  Scenario Outline: Getting a person by id
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 1  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 |
    When a GET request to person with id <id> should have status <status>
    Examples:
      | id | status |
      | 1  | 200    |
      | 2  | 404    |

  Scenario: Getting all people
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 11 | Ana       | Wayne    | 2019-12-02 18:27:00.442 |
    And the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 21 | Ana       | Benner   | 2019-12-02 18:27:00.442 |
    When a GET request to all people should return some person

  Scenario Outline: Getting person by firstName
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 13 | Luan      | Wayne    | 2019-12-02 18:27:00.442 |
    When a GET request to person with firstName <name> should return <people-found> people
    Examples:
      | name   | people-found |
      | Luan   | 1            |
      | Eneias | 0            |

  Scenario Outline: Getting person by lastName
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 14 | Luis      | Inacio   | 2019-12-02 18:27:00.442 |
    When a GET request to person with lastName <name> should return <people-found> people
    Examples:
      | name   | people-found |
      | Inacio | 1            |
      | Temer  | 0            |

  Scenario: Creating a new person
    Given a POST request to person with:
      | id | firstName | lastName | birthDate               |
      | 2  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 |
    Then the person POST response should be:
    """
    {"id":2,"firstName":"Bruce","lastName":"Wayne","birthDate":1575307620442,"address":null,"pets":[]}
    """

  Scenario: Update an existing person
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 3  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 |
    When a PUT request to person with id 3 with:
      | id | firstName | lastName | birthDate               |
      | 3  | Batman    | Wayne    | 2019-12-02 18:27:00.442 |
    Then the person PUT response should be:
    """
    {"id":3,"firstName":"Batman","lastName":"Wayne","birthDate":1575307620442,"address":null,"pets":[]}
    """

  Scenario: Delete an existing person
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 4  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 |
    When a DELETE request to person with id 4
    Then the person DELETE status should be 200

