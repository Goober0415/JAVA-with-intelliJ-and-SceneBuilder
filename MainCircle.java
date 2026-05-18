package com.cis2235.dowdenduarteintelijdemo;

import javax.swing.JOptionPane;

public class MainCircle {
    public static void main(String[] args) {

        Circle circle;
        int response;

        do {
            // Ask user for radius
            String str = JOptionPane.showInputDialog(null,
                    "Enter the radius of the circle: ");

            // Convert string to double
            double radius = Double.parseDouble(str);

            // Instantiate Circle with the radius
            circle = new Circle(radius);

            // Display results
            JOptionPane.showMessageDialog(null, "For a circle of radius " + radius +
                    ", the area is " + circle.getArea() +
                    " and the circumference is " + circle.getCircumference());

            // Ask if they want to go again
            response = JOptionPane.showConfirmDialog(null,
                    "Do you want to do another circle?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        } while (response == JOptionPane.YES_OPTION);

        // Say goodbye
        JOptionPane.showMessageDialog(null, "Thanks for making circles with us.");
    }
}