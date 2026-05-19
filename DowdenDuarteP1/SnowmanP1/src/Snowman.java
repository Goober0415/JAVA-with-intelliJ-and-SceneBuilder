
/*
 * Name:        [Jamie Dowden-Duarte]
 * Email:       [jdowdenduarte@cnm.edu]
 * Program:     Snowman Game
 * File Name:   Snowman.java
 * Course:      CIS 2235 Java Programming Part I
 * Program 1:   First Java Program
 */

import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class Snowman {

    public static void main(String[] args) {

        // -------------------------------------------------------
        // Declare variables
        // -------------------------------------------------------
        int gamesPlayed = 0;
        int gamesWon    = 0;

        // Array of 10 hidden words (all lowercase, 5-10 letters, nature theme)
        String[] words = {
                "sunshine", "blizzard", "rainbow", "thunder",
                "crystal", "glacier", "meadow", "tornado",
                "forest", "cascade"
        };

        // DecimalFormat for percentage display
        DecimalFormat df = new DecimalFormat("0.00%");

        // -------------------------------------------------------
        // Course header JOptionPane
        // -------------------------------------------------------
        JOptionPane.showMessageDialog(null,
                "Student:   [Jamie]\n" +
                        "Program:   Snowman Game\n" +
                        "Objective: Guess the hidden word one letter at a time!\n" +
                        "           You have a limited number of wrong guesses\n" +
                        "           equal to the length of the hidden word.",
                "CIS 2235 – Program 1",
                JOptionPane.INFORMATION_MESSAGE);

        // -------------------------------------------------------
        // Directions JOptionPane
        // -------------------------------------------------------
        JOptionPane.showMessageDialog(null,
                "DIRECTIONS:\n\n" +
                        "• All words are from a NATURE theme.\n" +
                        "• Enter one lowercase letter at a time.\n" +
                        "• No abbreviations – all full words.\n" +
                        "• You may guess a correct letter again with no penalty.\n" +
                        "• You may NOT re-guess an incorrect letter without penalty.\n" +
                        "• You lose when wrong guesses = length of the hidden word.",
                "How to Play the Snowman Game",
                JOptionPane.INFORMATION_MESSAGE);

        // -------------------------------------------------------
        // Ask for player name
        // -------------------------------------------------------
        String playerName = JOptionPane.showInputDialog(null,
                "Please enter your name:",
                "Player Name",
                JOptionPane.QUESTION_MESSAGE);

        if (playerName == null || playerName.trim().isEmpty()) {
            playerName = "Player";
        }

        // -------------------------------------------------------
        // "Play Again" outer loop
        // -------------------------------------------------------
        int playAgain;
        do {
            // Pick a random word from the array
            int index = (int)(Math.random() * words.length);
            String hiddenWord = words[index];

            // Build guessedWord filled with '*' placeholders
            StringBuilder guessedWord = new StringBuilder();
            for (int i = 0; i < hiddenWord.length(); i++) {
                guessedWord.append('*');
            }

            // StringBuilder to track incorrect guesses
            StringBuilder incorrectGuesses = new StringBuilder();

            int correctCount  = 0;   // correct letters found so far
            int incorrectCount = 0;  // number of wrong guesses
            int totalGuesses  = 0;   // all guesses (correct + incorrect, no double-penalty)
            int maxWrong      = hiddenWord.length(); // max allowed wrong guesses

            gamesPlayed++;

            // -------------------------------------------------------
            // Snowman play loop for one word
            // -------------------------------------------------------
            do {
                // Build the title bar to show incorrect guesses so far
                String titleBar = "Wrong guesses (" + incorrectCount + "/" + maxWrong + "): "
                        + (incorrectGuesses.length() == 0 ? "none" : incorrectGuesses.toString());

                // Show current guessed word and prompt for a letter
                String input = JOptionPane.showInputDialog(null,
                        "Word:  " + guessedWord.toString() + "\n\n" +
                                "Enter a letter guess (lowercase):",
                        titleBar,
                        JOptionPane.QUESTION_MESSAGE);

                // Handle cancel / empty input gracefully
                if (input == null || input.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter a letter.",
                            "Invalid Input", JOptionPane.WARNING_MESSAGE);
                    continue;
                }

                // Take only the first character
                char guess = input.trim().charAt(0);

                // -------------------------------------------------------
                // 1) Check if letter was already guessed correctly
                // -------------------------------------------------------
                if (guessedWord.indexOf(String.valueOf(guess)) >= 0) {
                    JOptionPane.showMessageDialog(null,
                            "'" + guess + "' is already showing in the word – no penalty!",
                            "Already Guessed", JOptionPane.INFORMATION_MESSAGE);
                    continue;  // no count changes
                }

                // -------------------------------------------------------
                // 2) Check if letter is NOT in the hidden word
                // -------------------------------------------------------
                if (hiddenWord.indexOf(guess) < 0) {

                    // Check if this wrong letter was already guessed before
                    if (incorrectGuesses.indexOf(String.valueOf(guess)) >= 0) {
                        JOptionPane.showMessageDialog(null,
                                "'" + guess + "' was already guessed wrong – no extra penalty!",
                                "Already Tried", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Brand-new wrong guess
                        incorrectGuesses.append(guess).append(' ');
                        incorrectCount++;
                        totalGuesses++;
                        JOptionPane.showMessageDialog(null,
                                "'" + guess + "' is NOT in the word.  Keep trying!",
                                "Incorrect Guess", JOptionPane.WARNING_MESSAGE);
                    }

                } else {
                    // -------------------------------------------------------
                    // 3) Letter IS in the hidden word – reveal all occurrences
                    // -------------------------------------------------------
                    totalGuesses++;
                    int searchFrom = 0;
                    while (true) {
                        int pos = hiddenWord.indexOf(guess, searchFrom);
                        if (pos < 0) break;            // no more occurrences
                        guessedWord.setCharAt(pos, guess);
                        correctCount++;
                        searchFrom = pos + 1;
                    }
                }

                // Check win: no '*' left in guessedWord
            } while (guessedWord.indexOf("*") >= 0 && incorrectCount < maxWrong);

            // -------------------------------------------------------
            // End-of-word report
            // -------------------------------------------------------
            boolean won = guessedWord.indexOf("*") < 0;
            if (won) {
                gamesWon++;
            }

            double pctCorrect = (totalGuesses > 0)
                    ? (double) correctCount / totalGuesses
                    : 0.0;

            String resultMsg = (won
                    ? "🎉 You guessed it, " + playerName + "!\n\n"
                    : "❄️  Out of guesses, " + playerName + "!\n\n")
                    + "The word was:       " + hiddenWord + "\n"
                    + "Your word:          " + guessedWord.toString() + "\n\n"
                    + "Total guesses:      " + totalGuesses + "\n"
                    + "Correct letters:    " + correctCount + "\n"
                    + "Wrong guesses:      " + incorrectCount + "\n"
                    + "Score:              " + df.format(pctCorrect);

            JOptionPane.showMessageDialog(null,
                    resultMsg,
                    won ? "You Win!" : "Game Over",
                    won ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);

            // -------------------------------------------------------
            // Ask to play again
            // -------------------------------------------------------
            playAgain = JOptionPane.showConfirmDialog(null,
                    "Would you like to guess another word?",
                    "Play Again?",
                    JOptionPane.YES_NO_OPTION);

        } while (playAgain == JOptionPane.YES_OPTION);

        // -------------------------------------------------------
        // Good-bye message
        // -------------------------------------------------------
        JOptionPane.showMessageDialog(null,
                "Thanks for playing, " + playerName + "!\n\n" +
                        "Games played: " + gamesPlayed + "\n" +
                        "Games won:    " + gamesWon + "\n\n" +
                        "See you next time! ⛄",
                "Goodbye!",
                JOptionPane.INFORMATION_MESSAGE);

    } // end main

} // end class Snowman