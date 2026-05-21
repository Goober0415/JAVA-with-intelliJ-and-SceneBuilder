/**************************
 * Your Name  youremail@cnm.edu
 * Class RoomUI
 * CIS2235
 * File: RoomUI.java
 **************************/

import javax.swing.JOptionPane;

public class RoomUI {

    // default constructor
    public RoomUI() {

        // course header — first thing displayed
        JOptionPane.showMessageDialog(null,
                "Your Name\nRoom Dimensions Program\nObjective: Display data for three Room objects.",
                "Welcome", JOptionPane.INFORMATION_MESSAGE);

        // room1 — default constructor, default data
        Room room1 = new Room();

        // room2 — overloaded constructor with custom data
        Room room2 = new Room(9.0, 15.0, 12.0);

        // room3 — default constructor, then updated via setRoomData
        Room room3 = new Room();
        room3.setRoomData(10.0, 20.0, 14.5);

        // display room1
        JOptionPane.showMessageDialog(null,
                "Room 1 (Default Constructor):\n\n" + room1.getRoomDesc(),
                "Room 1", JOptionPane.INFORMATION_MESSAGE);

        // display room2
        JOptionPane.showMessageDialog(null,
                "Room 2 (Overloaded Constructor):\n\n" + room2.getRoomDesc(),
                "Room 2", JOptionPane.INFORMATION_MESSAGE);

        // display room3
        JOptionPane.showMessageDialog(null,
                "Room 3 (Default Constructor + setRoomData):\n\n" + room3.getRoomDesc(),
                "Room 3", JOptionPane.INFORMATION_MESSAGE);

        // goodbye message
        JOptionPane.showMessageDialog(null,
                "Thank you for using the Room Dimensions Program!\nGoodbye!",
                "Goodbye", JOptionPane.INFORMATION_MESSAGE);
    }

}