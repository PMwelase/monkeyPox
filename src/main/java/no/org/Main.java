package no.org;

import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;

public class Main {
    public static void main(String[] args) {
        // Create a 30x30 room grid
        RoomGrid roomGrid = new RoomGrid(30, 30);

        // Access a specific room at coordinates (5, 10)
        Room room = roomGrid.getRoom(5, 10);

        // Set a tag for the room
        room.tagRoom("Treasure Room");

        // Set barricades in the room
        room.setBarricades(5);

        // Print out the room details
        System.out.println("Room at (5, 10) has tag: " + room.getInteriorTag());
        System.out.println("Barricades: " + room.getBarricades());
    }
}
