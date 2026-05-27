/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  SnowFrame
 *  SnowFrame.java
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnowFrame extends JFrame {

    private Snowman snow;
    private SnowmanPanel canvas;

    // North - instructions
    private JTextArea jtaInstructions;

    // West - game controls
    private JLabel jlblName;
    private JTextField jtfName;
    private JButton jbtnSetName;
    private JLabel jlblGuess;
    private JTextField jtfGuess;
    private JButton jbtnGuess;
    private JLabel jlblMissed;
    private JTextArea jtaMissed;
    private JLabel jlblWord;
    private JTextArea jtaWord;
    private JLabel jlblResult;
    private JTextArea jtaResult;
    private JLabel jlblPlayAgain;
    private JButton jbtnYes;
    private JButton jbtnNo;

    // Constructor
    public SnowFrame(Snowman snow) {
        this.snow = snow;
        canvas = new SnowmanPanel();
        initComponents();
        showGuessingWord();
    }

    private void initComponents() {
        setLayout(new BorderLayout(5, 5));

        // --- NORTH: title and instructions ---
        jtaInstructions = new JTextArea(
                "Welcome to Snowman!\nEnter your name, then guess one letter at a time. " +
                        "You have 7 wrong guesses before the snowman is complete!");
        jtaInstructions.setFont(new Font("SansSerif", Font.BOLD, 13));
        jtaInstructions.setBackground(Color.CYAN);
        jtaInstructions.setEditable(false);
        jtaInstructions.setLineWrap(true);
        jtaInstructions.setWrapStyleWord(true);
        jtaInstructions.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        add(jtaInstructions, BorderLayout.NORTH);

        // --- CENTER: snowman drawing ---
        canvas.setBackground(Color.CYAN);
        canvas.setPreferredSize(new Dimension(300, 350));
        add(canvas, BorderLayout.CENTER);

        // --- WEST: controls panel ---
        JPanel westPanel = new JPanel();
        westPanel.setLayout(new GridLayout(0, 1, 3, 3));
        westPanel.setPreferredSize(new Dimension(200, 0));
        westPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Name input
        jlblName = new JLabel("Your Name:");
        jtfName = new JTextField();
        jbtnSetName = new JButton("Set Name");

        // Guess input
        jlblGuess = new JLabel("Guess a Letter:");
        jtfGuess = new JTextField();
        jbtnGuess = new JButton("Guess");
        jbtnGuess.setEnabled(false); // disabled until name is set

        // Missed letters display
        jlblMissed = new JLabel("Missed Letters:");
        jtaMissed = new JTextArea(2, 10);
        jtaMissed.setEditable(false);
        jtaMissed.setLineWrap(true);
        jtaMissed.setFont(new Font("Monospaced", Font.PLAIN, 12));

        // Word display
        jlblWord = new JLabel("Word:");
        jtaWord = new JTextArea(1, 10);
        jtaWord.setEditable(false);
        jtaWord.setFont(new Font("Monospaced", Font.BOLD, 14));

        // Result messages
        jlblResult = new JLabel("Result:");
        jtaResult = new JTextArea(3, 10);
        jtaResult.setEditable(false);
        jtaResult.setLineWrap(true);
        jtaResult.setWrapStyleWord(true);

        // Play again
        jlblPlayAgain = new JLabel("Play Again?");
        jbtnYes = new JButton("Yes");
        jbtnNo = new JButton("No");
        jlblPlayAgain.setVisible(false);
        jbtnYes.setVisible(false);
        jbtnNo.setVisible(false);

        westPanel.add(jlblName);
        westPanel.add(jtfName);
        westPanel.add(jbtnSetName);
        westPanel.add(jlblGuess);
        westPanel.add(jtfGuess);
        westPanel.add(jbtnGuess);
        westPanel.add(jlblMissed);
        westPanel.add(new JScrollPane(jtaMissed));
        westPanel.add(jlblWord);
        westPanel.add(jtaWord);
        westPanel.add(jlblResult);
        westPanel.add(new JScrollPane(jtaResult));
        westPanel.add(jlblPlayAgain);
        westPanel.add(jbtnYes);
        westPanel.add(jbtnNo);

        add(westPanel, BorderLayout.WEST);

        // --- EVENT LISTENERS ---

        // Set Name button - anonymous class
        jbtnSetName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtfName.getText().trim();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    JOptionPane.showMessageDialog(SnowFrame.this,
                            "Please enter a valid name (letters only).",
                            "Invalid Name", JOptionPane.ERROR_MESSAGE);
                } else {
                    snow.setName(name);
                    jtaResult.setText("Good luck, " + name + "!");
                    jbtnGuess.setEnabled(true);
                    jbtnSetName.setEnabled(false);
                    jtfName.setEditable(false);
                }
            }
        });

        // Guess button - anonymous class
        jbtnGuess.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = jtfGuess.getText().trim();
                jtfGuess.setText("");

                if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                    JOptionPane.showMessageDialog(SnowFrame.this,
                            "Please enter a single letter.",
                            "Invalid Guess", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                char letter = input.charAt(0);
                int result = snow.checkGuess(letter);

                if (result == 0) {
                    jtaResult.setText("You already guessed '" + letter + "'. Try another letter.");
                } else if (result == 1) {
                    jtaResult.setText("Good guess! '" + letter + "' is in the word.");
                } else {
                    jtaResult.setText("Sorry, '" + letter + "' is not in the word.");
                }

                showGuessingWord();
                canvas.repaint();

                if (snow.isGameOver()) {
                    checkResult();
                }
            }
        });

        // Play Again - Yes button (inner class)
        jbtnYes.addActionListener(new ButtonListener());

        // Play Again - No button - anonymous class
        jbtnNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sayGoodBye();
            }
        });
    }

    // Show the current state of the guessing word
    private void showGuessingWord() {
        jtaWord.setText(snow.getGuessingWord());
        jtaMissed.setText(snow.getMissedString());
    }

    // Check end-of-game state
    private void checkResult() {
        jtaResult.setText(snow.getGameOverString());
        jbtnGuess.setEnabled(false);
        jlblPlayAgain.setVisible(true);
        jbtnYes.setVisible(true);
        jbtnNo.setVisible(true);
    }

    // Say goodbye and close
    private void sayGoodBye() {
        JOptionPane.showMessageDialog(this, snow.getGoodBye(), "Goodbye!", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Inner class for Yes (play again) button
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            snow.newGame();
            jtaResult.setText("Starting a new game! Good luck, " + snow.getName() + "!");
            jbtnGuess.setEnabled(true);
            jlblPlayAgain.setVisible(false);
            jbtnYes.setVisible(false);
            jbtnNo.setVisible(false);
            showGuessingWord();
            canvas.repaint();
        }
    }

    // -------------------------
    // Inner class: SnowmanPanel
    // -------------------------
    class SnowmanPanel extends JPanel {

        private final int MID = 150;
        private final int TOP = 50;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int wrong = snow.getIncorrectCount();

            // Draw ground
            g.setColor(Color.blue);
            g.fillRect(0, 210, 300, 50);

            // Draw sun
            g.setColor(Color.yellow);
            g.fillOval(-20, -20, 80, 80);

            g.setColor(Color.white);

            // 1st wrong: lower torso
            if (wrong >= 1) {
                g.fillOval(MID - 50, TOP + 120, 100, 70);
            }
            // 2nd wrong: upper torso
            if (wrong >= 2) {
                g.fillOval(MID - 35, TOP + 75, 70, 55);
            }
            // 3rd wrong: head
            if (wrong >= 3) {
                g.fillOval(MID - 20, TOP + 35, 40, 40);
            }

            g.setColor(Color.black);

            // 4th wrong: left arm
            if (wrong >= 4) {
                g.drawLine(MID - 25, TOP + 95, MID - 55, TOP + 75);
            }
            // 5th wrong: right arm
            if (wrong >= 5) {
                g.drawLine(MID + 25, TOP + 95, MID + 60, TOP + 90);
            }
            // 6th wrong: hat
            if (wrong >= 6) {
                g.drawLine(MID - 20, TOP + 40, MID + 20, TOP + 40);
                g.fillRect(MID - 15, TOP + 15, 30, 25);
            }
            // 7th wrong: eyes and sad face
            if (wrong >= 7) {
                g.fillOval(MID - 10, TOP + 45, 5, 5);
                g.fillOval(MID + 5, TOP + 45, 5, 5);
                g.drawArc(MID - 10, TOP + 58, 20, 12, 10, 160);
            }
        }
    }
}