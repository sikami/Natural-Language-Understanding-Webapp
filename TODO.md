
1. [OK] Need to turn off button when syntax or emotion is selected so that user cannot click twice or
   put error handling
   
2. [OK] need to provide a text area to display the result
3. possibly add a reset button to clear everything

To connect to watson:

1. Query class need to be in main view and attached with the user input, along with controller and service
2. [OK] Retrieve API key from IBM
3. [OK] Find out what URL in setservice is, by comparing with previous project in github
4. [OK] Create Syntax result class
5. [OK] Create Emotion result class
6. [no need]create method to parse Json array, and objects
7. Logging error is needed
8. [OK] moving Syntax or Emotion option to Query instead in the service
9. public parseKeyword() in watson service needs to be refactored so its private, fix related problems
10. change Syntax and Emotion option to Enum
11. need to catch an error when forgot to setOption in query. When there is not keyword in the query, assume that its selecting syntax
12. need to catch error if selecting emotion, text has to be long at least 100 words
13. need to catch error if unauthorization exists, that means need to change the access token
14. need to wrap the result in to the result field in Main view, possibly there is a method to do that