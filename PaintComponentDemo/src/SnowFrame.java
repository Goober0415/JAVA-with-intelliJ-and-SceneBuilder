/* Jamie Dowden-Duarte  jdowdenduarte@cnm.edu
 *  SnowFrame
 *  SnowFrame.java
 *
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class SnowFrame extends JFrame {

    // handy variables
    private final int MID = 150;
    private final int TOP = 50;

    // reference to a SnowPanel object
    private SnowPanel snowPanel;

    // constructor
    public SnowFrame() {
        // instantiate the SnowPanel
        snowPanel = new SnowPanel();
        // set the background color for the panel
        snowPanel.setBackground(Color.cyan);
        // add the panel to the JFrame
        add(snowPanel);
    }

    // inner class SnowPanel
    class SnowPanel extends JPanel {

        @Override
        public void paintComponent(Graphics page) {
            super.paintComponent(page);

            // draw the ground
            page.setColor(Color.blue);
            page.fillRect(0, 175, 300, 50);

            // draw the sun
            page.setColor(Color.yellow);
            page.fillOval(-20, -20, 80, 80);

            // set the color for the snowman
            page.setColor(Color.white);

            // draw the head
            page.fillOval(MID - 20, TOP, 40, 40);

            // draw the upper torso
            page.fillOval(MID - 35, TOP + 35, 70, 50);

            // draw the lower torso
            page.fillOval(MID - 50, TOP + 80, 100, 60);

            // set the color for eyes, smile, arms and hat
            page.setColor(Color.black);

            // draw the left eye
            page.fillOval(MID - 10, TOP + 10, 5, 5);

            // draw the right eye
            page.fillOval(MID + 5, TOP + 10, 5, 5);

            // draw the smile
            page.drawArc(MID - 10, TOP + 20, 20, 10, 190, 160);

            // draw the left arm
            page.drawLine(MID - 25, TOP + 60, MID - 50, TOP + 40);

            // draw the right arm
            page.drawLine(MID + 25, TOP + 60, MID + 55, TOP + 60);

            // draw the brim of the hat
            page.drawLine(MID - 20, TOP + 5, MID + 20, TOP + 5);

            // draw the top of the hat
            page.fillRect(MID - 15, TOP - 20, 30, 25);
        }
    }
}