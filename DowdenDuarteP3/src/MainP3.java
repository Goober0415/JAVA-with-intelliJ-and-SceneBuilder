/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  MainP3
 *  MainP3.java
 *
 */

import javax.swing.JFrame;

public class MainP3 {

    public static void main(String[] args) {
        Snowman man = new Snowman();
        SnowFrame frame = new SnowFrame(man);
        frame.setTitle("Jamie Dowden-Duarte | Program 3 | Snowman Guessing Game");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}