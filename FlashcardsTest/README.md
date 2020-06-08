* This simple java project is a digital version of Flashcard (a learning mechanism).
* You can create the card using add option and tell tell the program to ask you by using ask option.
* you can save and restore the cards between sessions using `import/export` options (*.txt files)  
    * import/export options are also available as command line arguments:  
        > `java -jar FlashcardsTest.jar -import test.txt`  
          `java -jar FlashCardsTest.jar -export test.txt`  
          `java -jar FlashcardsTest.jar -import test.txt -export test.txt`
    * the files will be in the same directory as jar.
* The program also tells you which card(s) you answered wrong the most number of times (`hardest card` option).  
    * you can reset the stats using `reset stats` option.
* The executeable version of this project is located under out -> artifcats -> flashcardsTest_jar
