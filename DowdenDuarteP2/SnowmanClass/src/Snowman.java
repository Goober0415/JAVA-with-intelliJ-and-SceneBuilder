/**************************
 * Your Name  jdowdenduarte@cnm.edu
 * Class Snowman
 * CIS2235
 * File: Snowman.java
 **************************/

import java.text.DecimalFormat;

public class Snowman {

    // word bank
    private static final String[] WORD_ARRAY = {
            "winter", "frozen", "carrot", "button",
            "scarf",  "flake",  "frost",  "glove",
            "sleet",  "icicle"
    };

    // static game-session counters
    private static int gamesPlayed = 0;
    private static int gamesWon    = 0;

    // instance variables
    private String          hiddenWord;
    private StringBuilder   guessedWord;
    private StringBuffer    missedLetters;
    private int             totalGuesses;
    private int             correctGuesses;
    private int             incorrectGuesses;
    private String          playerName;
    private String          gameOverString;
    private DecimalFormat   formatter;

    // default constructor — sets up a new game
    public Snowman() {
        formatter = new DecimalFormat("#0.00");

        // pick a random word from the array
        int index = (int) (Math.random() * WORD_ARRAY.length);
        hiddenWord = WORD_ARRAY[index];

        // build guessedWord as all asterisks, e.g. "******"
        guessedWord = new StringBuilder();
        for (int i = 0; i < hiddenWord.length(); i++) {
            guessedWord.append('*');
        }

        missedLetters    = new StringBuffer();
        totalGuesses     = 0;
        correctGuesses   = 0;
        incorrectGuesses = 0;
        playerName       = "";
        gameOverString   = "";

        gamesPlayed++;
    }

    // set the player's name
    public void setName(String s) {
        playerName = s;
    }

    // returns the current guessed word (e.g. "w***er")
    public String getGuessingWord() {
        return guessedWord.toString();
    }

    // returns the string of missed letters so far
    public String getMissedString() {
        return missedLetters.toString();
    }

    // returns the game-over message
    public String getGameOverString() {
        return gameOverString;
    }

    // returns the goodbye message with session stats
    public String getGoodBye() {
        return "Goodbye, " + playerName + "!\n"
                + "Games played: " + gamesPlayed + "\n"
                + "Games won:    " + gamesWon;
    }

    /**
     * checkGuess — evaluates the player's letter guess.
     * Returns:
     *   1 = already guessed (letter was already in guessedWord)
     *   2 = incorrect guess (not in hiddenWord)
     *   3 = correct guess
     *   4 = game over (word complete OR out of incorrect guesses)
     */
    public int checkGuess(char letter) {
        totalGuesses++;
        int maxIncorrect = hiddenWord.length();

        // case 1: already guessed — letter is in guessedWord (not a '*')
        if (guessedWord.indexOf(String.valueOf(letter)) != -1) {
            totalGuesses--; // don't count repeat as a new guess
            return 1;
        }

        // case 2: letter not in hiddenWord — incorrect guess
        if (hiddenWord.indexOf(letter) == -1) {
            incorrectGuesses++;
            missedLetters.append(letter).append(' ');

            // check if out of guesses
            if (incorrectGuesses >= maxIncorrect) {
                buildGameOver(false);
                return 4;
            }
            return 2;
        }

        // case 3 / 4: letter IS in hiddenWord — reveal all occurrences
        correctGuesses++;
        for (int i = 0; i < hiddenWord.length(); i++) {
            if (hiddenWord.charAt(i) == letter) {
                guessedWord.setCharAt(i, letter);
            }
        }

        // check if word is complete
        if (guessedWord.toString().equals(hiddenWord)) {
            gamesWon++;
            buildGameOver(true);
            return 4;
        }

        return 3;
    }

    // private helper — builds the gameOver string
    private void buildGameOver(boolean won) {
        double pct = (totalGuesses > 0)
                ? ((double) correctGuesses / totalGuesses) * 100.0
                : 0.0;

        gameOverString = (won ? "You guessed it! The word was: "
                : "Sorry, you ran out of guesses! The word was: ")
                + hiddenWord + "\n"
                + "Total guesses:   " + totalGuesses   + "\n"
                + "Correct guesses: " + correctGuesses + "\n"
                + "Score:           " + formatter.format(pct) + "%";
    }

}