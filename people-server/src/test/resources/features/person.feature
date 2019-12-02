Feature: Search, create, update and delete people

  Scenario: Getting a person by id
    Given the following person should exist:
      | id | firstName | lastName | birthDate               |
      | 1  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 |
    When a GET request to person with id 1 named get-person-by-id
    Then the request get-person-by-id should have status code 200
    And the request get-person-by-id should respond with:
    """
    {"id":1,"firstName":"Bruce","lastName":"Wayne","birthDate":1575307620442,"address":null,"pets":[]}
    """
