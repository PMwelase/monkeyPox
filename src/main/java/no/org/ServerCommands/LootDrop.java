package no.org.ServerCommands;

import no.org.Rooms.RoomGrid;
import no.org.World.World;

import java.util.Random;

public class LootDrop {
    private World world;
    private RoomGrid roomGrid;
    private Random random = new Random();

    public LootDrop(World world) {
        this.world = world;
        this.roomGrid = world.getRoomGrid();
        dropLoot();
    }

    public void dropLoot() {
        int x = random.nextInt(roomGrid.getWidth());
        int y = random.nextInt(roomGrid.getHeight());

        String[] items = {"shell", "crow bar", "bee"};
        String loot = items[random.nextInt(items.length)];

        boolean isInterior = random.nextBoolean();

        if (isInterior) {
            roomGrid.getRoom(x, y).addItemInRoomInterior(loot);
            System.out.println("Dropped " + loot + " inside room at (" + x + "," + y + ")");
        } else {
            roomGrid.getRoom(x, y).addItemInRoomExterior(loot);
            System.out.println("Dropped " + loot + " outside room at (" + x + "," + y + ")");
        }
    }
}
