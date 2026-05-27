/* Jamie Dowden-Duarte jdowdenduarte@cnm.edu
 *  TextAreaDemo
 *  TextAreaDemo.java
 *
 */

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class TextAreaDemo extends JFrame {

    private DescriptionPanel panel = new DescriptionPanel();

    public TextAreaDemo() {
        // Set title, text and image in the description panel
        panel.setTitle("Canada");

        String desc = "The Canadian national flag, also known as the Maple Leaf flag, "
                + "was adopted on February 15, 1965. It features a red field on the left "
                + "and right sides, a white square in the center, and a stylized red maple "
                + "leaf at the center. The maple leaf has been a symbol of Canada since the "
                + "18th century. The flag's design was chosen after a national competition "
                + "and debate in Parliament. It replaced the Canadian Red Ensign, which had "
                + "been used since 1868. The proportions of the flag are 1:2 (height to width).";

        ImageIcon userIcon = new ImageIcon(Objects.requireNonNull(this.getClass()
                .getResource("flag.png")));

        panel.setImageIcon(userIcon);
        panel.setDescription(desc);

        // Add the description panel to the frame
        setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // Create a frame and set some properties
        TextAreaDemo frame = new TextAreaDemo();
        frame.setSize(500, 225);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Text Area Demo");
        frame.setVisible(true);
    }
}