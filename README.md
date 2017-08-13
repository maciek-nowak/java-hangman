# hangman-in-java

## Specification

### **`Application.java`**
**Fields:** 
  * AsciiArt **asciiArt**
  * boolean **again**

**Methods:**
  * public static void **main**(String[] args)
Controls main application flow
  * public static boolean **askUserToPlayAgain**()
Asks user if he wants to play again

### **`LeaderBoard.java`**
**Fields:** 
  * ArrayList<HighScore> **highScores**

**Methods:**
  * public static void **addHighScore**(HighScore highscore)
Adds new highscore to the highScores ArrayList
  * public static void **printHighScore**()
Prints Top 10 highscores list
  * public static void **saveHighScore**()
Saves Top 10 highscores list to the txt file
  * public static void **readHighScore**()
Reads Top 10 highscores list from the txt file
  * 

### **`Player.java`**
**Fields:** 
  * int **life**
  * String **name**

**Methods:**
  * public boolean **isAlive**()
Returns true if player has at least 1 life
  * public void **decreaseLife**(int damage)
Decreases player's life
  * public int **getLife**()
Returns player's life
  * public String **getName**()
Returns player's name


### **`Game.java`**
**Fields:** 
  * Player **player**
  * Capital **capital**
  * AsciiArt **asciiArt**
  * long **startTime**
  * int **attempt**
  * int **gameTime**
  * static String **GREEN**
  * static String **RED**
  * static String **YELLOW**
  * static String **WHITE**

**Methods:**
  * public static void **singleGame**(AsciiArt asciiArt)
Controls single game flow
  * public static String **askPlayerName**()
Asks player for his name
  * public static void **clearScreen**()
Clears screen
  * public String **getUserLetter**()
Asks user to guess a letter
  * public String **getUserWord**()
Asks user to guess whole word
  * public void **printGameStatus**()
Prints all current game information
  * public void **handleUserGuess**()
Processes user inputs of guessed letters and words
  * public void **printStartMessage**()
Prints info about game goal
  * public void **printFinalInfo**()
Prints end game info
  *  public void **handleHighScore**()
Controls all methods connected to highscore

### **`Capital.java`**
**Fields:** 
  * String **capital**
  * String **country**
  * String **iddenCapital**
  * HashSet<String> **wrongLetters**
  * HashSet<String> **wrongWords**

**Methods:**
  * public String **getCapital**()
Returns capital name
  * public String **getCountry**()
Returns country
  * public String **getHiddenCapital**()
Returns hiddenCapital
  * public String **getHiddenCapitalView**()
Returns representation of hiddenCapital for printing
  * public String **getWrongLettersView**
Returns representation of unguessed letters set for printing
  * public String **getWrongWordsView**()
Returns representation of unguessed words set for printing
  * public HashSet<String> **getWrongLetters**
Returns wrongLetters
  * public HashSet<String> **getWrongWords**
Returns wrongwords
  * public boolean **isGuessed**()
Returns true if all letters of hiddenCapital are uncovered
  * public boolean **checkLetter**( String letter )
Checks if letter entered by user is correct
  * public boolean **checkWord**( String word )
Checks if word entered by user is correct
  * public static ArrayList<Capital> **readCapitalsData**()
Reads and creates list of Capitals from file
  * public static Capital **chooseRandomCapital**()
Randomly chooses Capital