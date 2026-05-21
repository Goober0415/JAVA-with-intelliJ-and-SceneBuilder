/**************************
 * Your Name  jdowdenduarte@cnm.edu
 * Class SnowmanUI
 * CIS2235
 * File: SnowmanUI.java
 **************************/

import javax.swing.JOptionPane;

public class SnowmanUI {

    private Snowman snowman;

    // constructor — receives the first Snowman object from MainP2
    public SnowmanUI(Snowman snowman) {
        this.snowman = snowman;
    }

    // public play method — main game loop
    public void play() {

        // course header — first thing the player sees
        JOptionPane.showMessageDialog(null,
                "Your Name\nSnowman Game\nObjective: Guess the hidden word one letter at a time.",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // get the player's name once per session
        String name = JOptionPane.showInputDialog(null,
                "Please enter your name:", "Player Name",
                JOptionPane.QUESTION_MESSAGE);
        if (name == null || name.trim().isEmpty()) {
            name = "Player";
        }
        snowman.setName(name);

        boolean keepPlaying = true;

        do {
            boolean gameOver = false;

            // inner loop — one game
            while (!gameOver) {
                String prompt = "Word: "   + snowman.getGuessingWord() + "\n"
                        + "Missed: " + snowman.getMissedString()  + "\n\n"
                        + "Enter your guess (one letter):";

                String input = JOptionPane.showInputDialog(null,
                        prompt, "Snowman", JOptionPane.QUESTION_MESSAGE);

                // player closed the dialog
                if (input == null || input.trim().isEmpty()) {
                    continue;
                }

                char letter = Character.toLowerCase(input.trim().charAt(0));
                int result = snowman.checkGuess(letter);

                switch (result) {
                    case 1:
                        JOptionPane.showMessageDialog(null,
                                "You already guessed '" + letter + "'. Try again!");
                        break;
                    case 2:
                        JOptionPane.showMessageDialog(null,
                                "'" + letter + "' is not in the word.");
                        break;
                    case 3:
                        JOptionPane.showMessageDialog(null,
                                "Great guess! '" + letter + "' is in the word.");
                        break;
                    case 4:
                        JOptionPane.showMessageDialog(null,
                                snowman.getGameOverString());
                        gameOver = true;
                        break;
                }
            }

            // ask if the player wants to play again
            int again = JOptionPane.showConfirmDialog(null,
                    "Would you like to guess another word?",
                    "Play Again?", JOptionPane.YES_NO_OPTION);

            if (again == JOptionPane.YES_OPTION) {
                snowman = new Snowman();
                snowman.setName(name);
            } else {
                keepPlaying = false;
            }

        } while (keepPlaying);

        sayGoodBye();
    }

    // private goodbye method
    private void sayGoodBye() {
        JOptionPane.showMessageDialog(null,
                snowman.getGoodBye(), "Goodbye",
                JOptionPane.INFORMATION_MESSAGE);
    }

}