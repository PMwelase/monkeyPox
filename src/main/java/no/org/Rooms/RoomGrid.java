package no.org.Rooms;

public class RoomGrid {
    private Room[][] rooms;
    private final int width;
    private final int height;

    public RoomGrid(int width, int height) {
        this.width = width;
        this.height = height;
        rooms = new Room[width][height];
        initializeRooms();
    }

    private void initializeRooms() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rooms[x][y] = new Room(x, y);
            }
        }
    }

    public Room getRoom(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return rooms[x][y];
        } else {
            return null; // or throw an exception
        }
    }

    public void setRoom(int x, int y, Room room) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            rooms[x][y] = room;
        }
    }
}
