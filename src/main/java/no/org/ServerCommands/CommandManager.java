package no.org.ServerCommands;

import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.Scanner;

public abstract class CommandManager {
    public static World world;


    public static World getWorld() {
        return world;
    }

    public static void setWorld(World currentWorld){
        world = currentWorld;
    }

    public static void create(String instruction, World world) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        switch (instruction) {
//            case "quit":
//                new QuitCommand();
//                return;
//            case "players":
//                new RobotsCommand(world);
//                return;
            case "dump":
                new DumpCommand(world);
                return;
//            case "help":
//                new HelpCommand();
//                return;

            case "drop":
                new LootDrop(world);
                return;

            case "type":
                System.out.println("x?: ");
                int x = scanner.nextInt();
                System.out.println("y?: ");
                int y = scanner.nextInt();
                scanner.nextLine();  // This will consume the remaining newline character
                System.out.println("type?: ");
                String type = scanner.nextLine();
                new RoomType(x, y, type);
                return;


//            case "purge":
//                PurgeCommand purge = new PurgeCommand();
//                System.out.println("Who would you like to purge?: ");
//                String robotName = scanner.nextLine();
//                purge.kick(robotName);
//                System.out.println(robotName + " has been kicked out!");
//                return;

            default:
                throw new IllegalArgumentException("Not a valid command: " + instruction);
        }
    }
}
