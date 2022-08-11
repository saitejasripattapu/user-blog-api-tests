Feature: User Blog API Automation

  @Regression @API
  Scenario: Getting a particular user from the users API
    When User will call "users" API with "GET" http request
    Then verify the response and get the userID for "Delphine" user

  @Regression @API
  Scenario: Get the posts written by a particular User
    When User will call "posts" API with "GET" http request
    Then verify the response and get the posts written by user

  @Regression @API
  Scenario: For each post of the user get the comments
    When User will call "comments" API with "GET" http request
    Then verify the response and get the comments for each post
    And validate the email in each comment

