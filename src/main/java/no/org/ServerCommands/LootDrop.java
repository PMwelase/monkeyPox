package no.org.ServerCommands;

import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;

import java.util.Random;
import java.util.Scanner;

public class LootDrop {
    private World world;
    private RoomGrid roomGrid;
    private Random random;

    private String[] lootItems = {"clip", "spray can", "shells", "water", "crow bar"};

    public LootDrop(World world, Scanner scanner) {
        this.world = world;
        this.roomGrid = world.getRoomGrid();
        this.random = new Random();  // Initialize random number generator
        dropLoot();
        dropTreasure();
    }

    public void dropLoot() {
        for (int x = 0; x < roomGrid.getWidth(); x++) {
            for (int y = 0; y < roomGrid.getHeight(); y++) {
                Room room = roomGrid.getRoom(x, y); // Get the room at (x, y)

                if (room != null) {
                    // Randomly select an item from the lootItems array
                    String randomItem = lootItems[random.nextInt(lootItems.length)];

                    // Randomly decide to drop inside or outside (50% chance for each)
                    boolean dropInside = random.nextBoolean();

                    if (dropInside) {
                        room.addItemInRoomInterior(randomItem);
                        System.out.println("Dropped " + randomItem + " inside the room at (" + x + ", " + y + ").");
                    } else {
                        room.addItemInRoomExterior(randomItem);
                        System.out.println("Dropped " + randomItem + " outside the room at (" + x + ", " + y + ").");
                    }
                }
            }
        }
    }

    public void dropTreasure() {
        Random random1 = new Random();
        Position pos = new Position(random1.nextInt(0, 29), random1.nextInt(0, 29));
        roomGrid.getRoom(pos.getX(), pos.getY()).addItemInRoomExterior("Slippers");
//        System.out.println("Dropped Slippers outside the room at (" + pos.getX() + ", " + pos.getY() + ").");
    }
}
