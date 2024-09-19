package no.org.ServerCommands;

import no.org.Rooms.RoomGrid;
import no.org.World.World;

import java.util.Scanner;

public class ItemDrop {
    private World world;
    private RoomGrid roomGrid;

    public ItemDrop(World world, Scanner scanner) {
        this.world = world;
        this.roomGrid = world.getRoomGrid();
        dropItem(scanner);
    }

    public void dropItem(Scanner scanner) {
        try {
            System.out.println("Enter the command (e.g., drop item in/out x y): ");
            String input = scanner.nextLine();

            String[] parts = input.split("\\s+");

            if (parts.length != 5 || !parts[0].equalsIgnoreCase("drop") ||
                    (!parts[2].equalsIgnoreCase("in") && !parts[2].equalsIgnoreCase("out"))) {
                System.out.println("Invalid command format. Use: drop [item] in/out [x] [y]");
                return;
            }

            String item = parts[1];
            int x = Integer.parseInt(parts[3]);
            int y = Integer.parseInt(parts[4]);

            if (parts[2].equalsIgnoreCase("in")) {
                roomGrid.getRoom(x, y).addItemInRoomInterior(item);
                System.out.println("Loot dropped inside the room.");
            } else if (parts[2].equalsIgnoreCase("out")) {
                roomGrid.getRoom(x, y).addItemInRoomExterior(item);
                System.out.println("Loot dropped outside the room.");
            }

        } catch (Exception e) {
            System.out.println("Error during loot drop: " + e.getMessage());
        }
    }
}
