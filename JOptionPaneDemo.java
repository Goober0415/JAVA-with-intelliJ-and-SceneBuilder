package com.cis2235.dowdenduarteintelijdemo;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class JOptionPaneDemo {
    public static void main(String[] args) {

        // Step 2: Commented out println
        //System.out.println("Hello World");

        // Step 5: Simple Message Dialog
        JOptionPane.showMessageDialog(null, "Jamie Dowden-Duarte");

        // Step 7: Custom Message Dialog
        JOptionPane.showMessageDialog(null, "This is Hello, too",
                "Jamie Dowden-Duarte", JOptionPane.INFORMATION_MESSAGE);

        // Step 9: Input Dialog - ask for name
        String input = JOptionPane.showInputDialog("Enter your name");

        // Step 9b: Input Dialog - ask for age
        String sAge = JOptionPane.showInputDialog("Hello, " + input + " Enter your age:");

        // Step 10: Convert age string to int
        int age = Integer.parseInt(sAge);

        // Step 11: Show name and age
        JOptionPane.showMessageDialog(null, "Hello " + input + ", your age is " + age);

        // Step 12: Confirmation Dialog
        int response = JOptionPane.showConfirmDialog(null, "Do you want to continue?",
                "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (response == JOptionPane.NO_OPTION) {
            JOptionPane.showMessageDialog(null, "ok, Bye");
        } else {
            JOptionPane.showMessageDialog(null, "I guess I have not finished this demo.");
        }

        // Step 15-18: ComboBox with Rock, Paper, Scissors
        String[] options = {"Rock", "Paper", "Scissors"};
        JComboBox<String> jcb = new JComboBox<>(options);

        input = (String) JOptionPane.showInputDialog(null, "Select Rock, Paper or Scissors ",
                "Choices ", JOptionPane.QUESTION_MESSAGE, null, options, "RPS");

        // Step 19: Display user choice
        JOptionPane.showMessageDialog(null, "You chose " + input);

        // Step 20: Option Dialog
        Object[] moreOptions = {"Rock", "Paper", "Scissors", "Lizard"};
        int choice = JOptionPane.showOptionDialog(null, "Pick one.", "RPS",
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        JOptionPane.showMessageDialog(null, "You chose " + options[choice]);
    }
}