Feature: retrieving annonces
  Scenario: client makes call to GET /api/annonce
    When the client calls /api/annonce
    Then the client receives status code of 200
    And the client receives anonnces