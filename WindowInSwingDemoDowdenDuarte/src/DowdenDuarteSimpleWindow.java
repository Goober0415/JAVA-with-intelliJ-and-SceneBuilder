/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  SimpleWindow
 *  DowdenDuarteSimpleWindow.java
 *
 */

import javax.swing.*;
import java.awt.Color;

public class DowdenDuarteSimpleWindow {

    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("Hello, Java Again!");

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the frame
        frame.setSize(300, 300);

        // Create a label
        JLabel label = new JLabel("Hello, Java Again!", JLabel.CENTER);

        // Add the label to the frame
        frame.add(label);

        // Change the background color of the JFrame
        frame.getContentPane().setBackground(Color.PINK);

        // Make the frame visible
        frame.setVisible(true);
    }
}