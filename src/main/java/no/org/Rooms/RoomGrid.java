package no.org.Rooms;

import java.util.Random;

public class RoomGrid {
    private int width;
    private int height;
    private Room[][] rooms;

    // List of possible room types corresponding to the assets
    private static final String[] roomTypes = {
            "Apartment", "Church", "Complex", "Police Station",
            "fire station", "hospital", "townhouse", "Library", "high rise", "school"
    };

    public RoomGrid(int width, int height) {
        this.width = width;
        this.height = height;
        // Initialize the grid with the exact number of rooms
        rooms = new Room[width][height];
        initializeRooms();
    }

    // Method to initialize rooms and set random room types
    private void initializeRooms() {
        Random random = new Random();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Assign a random room type
                String randomRoomType = roomTypes[random.nextInt(roomTypes.length)];

                // Create the room and set its type
                Room room = new Room(x, y);
                room.setRoomType(randomRoomType);

                if (x < width/2 && y < height / 2) {
                    room.setColor("FBF8CC");
                } else if (x >= width / 2 && y < height / 2) {
                    room.setColor("FDE4CF");
                } else if (x < width / 2 && y >= height / 2) {
                    room.setColor("98F5E1");
                } else {
                    room.setColor("90DBF4");
                }
                rooms[x][y] = room;
            }
        }
    }

    // Getter for room grid (optional)
    public Room getRoom(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return rooms[x][y];
        } else {
            return null; // Out of bounds
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
