package no.org.Rooms;

public class RoomGrid {
    private final Room[][] rooms;
    private final int width;
    private final int height;

    public RoomGrid(int width, int height) {
        this.width = width;
        this.height = height;
        // Initialize the grid with the exact number of rooms
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
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
        return rooms[x][y];
    }

    public void setRoom(int x, int y, Room room) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordinates out of bounds: (" + x + ", " + y + ")");
        }
        rooms[x][y] = room;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
