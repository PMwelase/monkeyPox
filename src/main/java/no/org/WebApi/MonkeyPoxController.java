package no.org.WebApi;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.CommandFactory;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/monkeypox")
public class MonkeyPoxController {

    private final World world;
    private final Random random;

    public MonkeyPoxController(RoomGrid roomGrid) {
        this.world = world(roomGrid);
        this.random = new Random();  // Initialize the random object
    }

    public World world(RoomGrid roomGrid) {
        return new World(roomGrid, new Position(0, 0), new Position(30, 30));
    }

    // Method to generate a random position within the grid
    private Position getRandomPosition(RoomGrid roomGrid) {
        int width = roomGrid.getWidth();
        int height = roomGrid.getHeight();
        int randomX = random.nextInt(width);  // Random X within grid width
        int randomY = random.nextInt(height); // Random Y within grid height
        return new Position(randomX, randomY);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Player user) {
        Position randomPosition = getRandomPosition(this.world.getRoomGrid());  // Get random position in grid
        Player newPlayer = new Player(user.getName(), user.getType(), this.world, randomPosition);  // Use random position
        newPlayer.initializePlayer(user.getName(), user.getType());
        return ResponseEntity.ok("User added to world as Player: " + newPlayer.getName() + " at position " + randomPosition);
    }

    @PostMapping("/world/action")
    public ResponseEntity<String> performAction(@RequestBody Action action) {
        String commandName = action.getCommand();
        System.out.println("Command: " + commandName);
        JSONArray arguments = new JSONArray(action.getArguments());
        System.out.println("Arguments: " + arguments.toString());
        Command command = CommandFactory.createCommand(commandName, arguments);
        Player player = this.world.getPlayer(action.getName());
        JSONObject response = command.execute(player, world);
        return ResponseEntity.ok(response.toString());
    }

    public static class Action {
        private String command;
        private List<String> arguments;
        private String name;

        public Action() {
        }

        public String getCommand() {
            return command;
        }

        public void setCommand(String command) {
            this.command = command;
        }

        public List<String> getArguments() {
            return arguments;
        }

        public void setArguments(List<String> arguments) {
            this.arguments = arguments;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public JSONArray getArgumentsAsJSONArray() {
            return new JSONArray(arguments);
        }
    }

}
