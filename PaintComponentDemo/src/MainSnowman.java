/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  MainSnowman
 *  MainSnowman.java
 *
 */

import javax.swing.JFrame;

public class MainSnowman {

    // create a reference to a custom frame inheriting from JFrame
    private SnowFrame snow;

    // Add the main method:
    public static void main(String[] args) {
        // Instantiate the MainSnowman class.
        MainSnowman app = new MainSnowman();
    }

    // constructor for the class
    public MainSnowman() {
        snow = new SnowFrame();
        snow.setTitle("Snowman");
        snow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        snow.setLocationRelativeTo(null);
        snow.setSize(300, 260);
        snow.setVisible(true);
    }

} // end of class