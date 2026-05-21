/**************************
 * Your Name  jdowdenduarte@cnm.edu
 * Class Room
 * CIS2235
 * File: Room.java
 **************************/

public class Room {

    // instance variables
    private double height;
    private double length;
    private double width;

    // default constructor — sets standard room dimensions
    public Room() {
        this(8.0, 12.5, 10.0); // constructor chaining
    }

    // overloaded constructor — accepts custom dimensions
    public Room(double height, double length, double width) {
        this.height = height;
        this.length = length;
        this.width  = width;
    }

    // setRoomData — allows updating dimensions after construction
    public void setRoomData(double height, double length, double width) {
        this.height = height;
        this.length = length;
        this.width  = width;
    }

    // getRoomDesc — returns a formatted string describing the room
    public String getRoomDesc() {
        return "Room Dimensions:\n"
                + "  Height : " + height + " ft\n"
                + "  Length : " + length + " ft\n"
                + "  Width  : " + width  + " ft";
    }

}