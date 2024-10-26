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
        rooms = new Room[width][height];
        initializeRooms();
        myWorld();
        stadium();
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

    private void myWorld(){
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (x < width / 2 && y < height / 2) {
                    rooms[x][y].setColor("FBF8CC");
                    rooms[x][y].setRoomType("Apartment");
                } else if (x >= width / 2 && y >= height / 2) {
                    rooms[x][y].setColor("FDE4CF");
                    rooms[x][y].setRoomType("townhouse");
                } else if (x < width / 2 && y >= height / 2) {
                    rooms[x][y].setColor("98F5E1");
                    rooms[x][y].setRoomType("high rise");
                } else {
                    rooms[x][y].setColor("90DBF4");
                    rooms[x][y].setRoomType("complex");
                }
            }
        }
    }

    private void stadium(){
        rooms[1][13].setRoomType("M1");
        rooms[2][13].setRoomType("M2");
        rooms[3][13].setRoomType("M3");
        rooms[4][13].setRoomType("M4");
        rooms[1][12].setRoomType("M5");
        rooms[2][12].setRoomType("M6");
        rooms[3][12].setRoomType("M7");
        rooms[4][12].setRoomType("M8");
        rooms[1][11].setRoomType("M9");
        rooms[2][11].setRoomType("M10");
        rooms[3][11].setRoomType("M11");
        rooms[4][11].setRoomType("M12");
    }

    private void firestations(){

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
