Feature: Video GameDb Tests - Validate Get, Post, Put, delete for Video Games Endpoints

@getAllGames
Scenario: Validate Get All Games
Given Video Games Request scenario setup   
When Get All Games
Then Video Games Response and Validation

#@CreateNewGame
#Scenario: Validate Create New Game by JSON
#Given Video Games Request scenario setup   
#When Create a New Game
#Then Video Games Response and Validation
#
#@UpdateGame
#Scenario: Validate Update Game
#Given Video Games Request scenario setup   
#When Update a Game
#Then Video Games Response and Validation
#
#@DeleteGame
#Scenario: Validate Delete Game
#Given Video Games Request scenario setup   
#When Delete a Game
#Then Video Games Response and Validation