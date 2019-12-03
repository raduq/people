Feature: Search, update and delete people's address

  Scenario: Create person with address
    Given the following person should exist:
      | id | firstName | lastName | birthDate               | address |
      | 1  | Bruce     | Wayne    | 2019-12-02 18:27:00.442 | 1       |
    Then a GET request to address with id 1 should have status 200
