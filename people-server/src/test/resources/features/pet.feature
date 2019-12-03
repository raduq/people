Feature: Search, create, update and delete people's pets

  Scenario Outline: Getting a pet by id
    Given the following pet should exist:
      | id | name | age |
      | 1  | coco | 1   |
    When a GET request to pet with id <id> should have status <status>
    Examples:
      | id | status |
      | 1  | 200    |
      | 2  | 404    |

  Scenario: Getting all person pets
    Given the following person should exist:
      | id | firstName | lastName | birthDate               | pet     |
      | 1  | Ana       | Wayne    | 2019-12-02 18:27:00.442 | 2-duque |
    When a GET request to all pet should return some pet

  Scenario: Creating a new pet
    Given the following person should exist:
      | id | firstName | lastName | birthDate               | pet    |
      | 1  | Ana       | Wayne    | 2019-12-02 18:27:00.442 | 3-lala |
    When a POST request to pet with:
      | id | name | age |
      | 1  | lala | 1   |
    Then the pet POST response should be:
    """
    {"id":1,"name":"lala","age":1}
    """

  Scenario: Update an existing pet
    Given the following person should exist:
      | id | firstName | lastName | birthDate               | pet     |
      | 3  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 | 4-alane |
    When a PUT request to pet with id 4 with:
      | id | name   | age |
      | 4  | alanis | 2   |
    Then the pet PUT response should be:
    """
    {"id":4,"name":"alanis","age":2}
    """

  Scenario: Delete an existing pet
    Given the following pet should exist:
      | id | name | age |
      | 4  | loid | 8   |
    When a DELETE request to pet with id 4
    Then the pet DELETE status should be 200

