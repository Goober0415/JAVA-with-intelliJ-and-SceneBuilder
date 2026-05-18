package com.cis2235.dowdenduarteintelijdemo;

public class Circle {
    private double radius = 1;
    private double area = 1;
    private double circumference = 1;

    // Default constructor
    public Circle() {
        calcAreaAndCircumference();
    }

    // Parameterized constructor
    public Circle(double rad) {
        setRadius(rad);
    }

    // Setter
    public void setRadius(double rad) {
        radius = rad;
        calcAreaAndCircumference();
    }

    // Getters
    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return area;
    }

    public double getCircumference() {
        return circumference;
    }

    // Calculate area and circumference
    private void calcAreaAndCircumference() {
        area = Math.PI * Math.pow(radius, 2);
        circumference = 2.0 * Math.PI * radius;
    }
}