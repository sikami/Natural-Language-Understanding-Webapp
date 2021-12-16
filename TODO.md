
1. [OK] Need to turn off button when syntax or emotion is selected so that user cannot click twice or
   put error handling
   
2. [OK] need to provide a text area to display the result
3. possibly add a reset button to clear everything

To connect to watson:

1. [No longer needed] Query class need to be in main view and attached with the user input, along with controller and service
2. [OK] Retrieve API key from IBM
3. [OK] Find out what URL in setservice is, by comparing with previous project in github
4. [OK] Create Syntax result class
5. [OK] Create Emotion result class
6. [no need]create method to parse Json array, and objects
7. [OK] Logging error is needed
8. [OK] moving Syntax or Emotion option to Query instead in the service
9. [no need] public parseKeyword() in watson service needs to be refactored so its private, fix related problems
10. change Syntax and Emotion option to Enum
11. [OK] need to catch an error when forgot to setOption in query. When there is not keyword in the query, assume that its selecting syntax - changed the behavior entirely, so User has to put some keywords in
12. [OK] need to catch error if selecting emotion, text has to be long at least 100 words - user is alerted if text < 100
13. [ok] need to catch error if selecting emotion with no keywords, then need to find another way around - user needs to always have keyword for emotions
14. [OK] need to catch error if unauthorization exists, that means need to change the access token
15. [OK] need to wrap the result in to the result field in Main view, possibly there is a method to do that
16. need to see if spell checker is possible for the text input
17. need some logging
18. Add word count in textArea