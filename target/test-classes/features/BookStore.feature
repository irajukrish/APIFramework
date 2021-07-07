Feature: Validating End to End Test Book Store API

Scenario: Verify User able to Add and Remove a book
Given I am an authorized user
Given A list of books are available
When I add a book to my reading list
Then The book is added
When I remove a book from my reading list
Then The book is removed