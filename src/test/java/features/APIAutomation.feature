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


  @Regression @API
  Scenario: Create a new post for the given user
    Given the payload for the Post creation
    When User will call "posts" API with "POST" http request
    Then verify the response and get the postID
    And verify that the new post is displayed when called with GET request


  @Regression @API
  Scenario: Create a new comments for the given post
    Given the payload for the comment creation
    When User will call "comments" API with "POST" http request
    Then verify the response and get the commentID
    And verify that the new comment is displayed when called with GET request


  @Regression @API @NegativeScenario
  Scenario: Search for a user who is not available
    When User will call "users" API with "GET" http request 345
    Then verify the response

  @Regression @API @NegativeScenario
  Scenario: Search for a particular post which is not available
    When User will call "posts" API with "GET" http request 345
    Then verify the response

  @Regression @API @NegativeScenario
  Scenario: Search for a particular post which is not available
    When User will call "comments" API with "GET" http request 700
    Then verify the response


  @Regression @API
  Scenario: Delete a particular user
    When User will call "users" API with "DELETE" http request 9
    Then verify the response for the delete request


  @Regression @API
  Scenario: Delete a particular user which is not present
    When User will call "users" API with "DELETE" http request 5677
    Then verify the response for particular delete request

  @Regression @API
  Scenario: Delete a particular post
    When User will call "posts" API with "DELETE" http request 4
    Then verify the response for the delete request
    And verify that the post 4 is not present in GET post by id API

  @Regression @API
  Scenario: Delete a particular post which is not present
    When User will call "posts" API with "DELETE" http request 578
    Then verify the response for particular delete request

  @Regression @API
  Scenario: Delete a particular comment
    When User will call "comments" API with "DELETE" http request 27
    Then verify the response for the delete request

  @Regression @API
  Scenario: Delete a particular comment which is not present
    When User will call "comments" API with "DELETE" http request 700
    Then verify the response for particular delete request





