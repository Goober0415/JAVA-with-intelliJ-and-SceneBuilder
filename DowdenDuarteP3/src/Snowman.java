/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  Snowman
 *  Snowman.java
 *
 */

import java.util.HashSet;
import java.util.Set;

public class Snowman {

    private String[] wordList = {
            "java", "programming", "snowman", "computer", "keyboard",
            "variable", "method", "class", "object", "loop"
    };

    private String secretWord;
    private char[] guessingWord;
    private Set<Character> missedGuesses;
    private Set<Character> correctGuesses;
    private String playerName;
    private int incorrectCount;
    private static final int MAX_WRONG = 7;

    public Snowman() {
        newGame();
    }

    public void newGame() {
        // Pick a random word
        secretWord = wordList[(int)(Math.random() * wordList.length)];
        guessingWord = new char[secretWord.length()];
        for (int i = 0; i < guessingWord.length; i++) {
            guessingWord[i] = '_';
        }
        missedGuesses = new HashSet<>();
        correctGuesses = new HashSet<>();
        incorrectCount = 0;
    }

    // Returns: 1 = correct, 0 = already guessed, -1 = wrong
    public int checkGuess(char letter) {
        letter = Character.toLowerCase(letter);

        if (correctGuesses.contains(letter) || missedGuesses.contains(letter)) {
            return 0; // already guessed
        }

        boolean found = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == letter) {
                guessingWord[i] = letter;
                correctGuesses.add(letter);
                found = true;
            }
        }

        if (!found) {
            missedGuesses.add(letter);
            incorrectCount++;
            return -1;
        }

        return 1;
    }

    public void setName(String s) {
        playerName = s;
    }

    public String getName() {
        return playerName;
    }

    public String getGuessingWord() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guessingWord.length; i++) {
            sb.append(guessingWord[i]);
            if (i < guessingWord.length - 1) sb.append(" ");
        }
        return sb.toString();
    }

    public String getMissedString() {
        if (missedGuesses.isEmpty()) return "None yet";
        StringBuilder sb = new StringBuilder();
        for (char c : missedGuesses) {
            sb.append(c).append(" ");
        }
        return sb.toString().trim();
    }

    public int getIncorrectCount() {
        return incorrectCount;
    }

    public boolean isGameOver() {
        return incorrectCount >= MAX_WRONG || isWordGuessed();
    }

    public boolean isWordGuessed() {
        return new String(guessingWord).equals(secretWord);
    }

    public String getGameOverString() {
        if (isWordGuessed()) {
            return "Congratulations, " + playerName + "! You guessed the word: " + secretWord;
        } else {
            return "Game over, " + playerName + "! The word was: " + secretWord;
        }
    }

    public String getGoodBye() {
        return "Thanks for playing, " + playerName + "! Goodbye!";
    }

    public static int getMaxWrong() {
        return MAX_WRONG;
    }
}