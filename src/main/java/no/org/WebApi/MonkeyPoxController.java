package no.org.WebApi;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.CommandFactory;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.Delay;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/monkeypox")
public class MonkeyPoxController {

    private final World world;
    private final Random random;
    private final GameState gameState;  // Make GameState a class-level variable

    public MonkeyPoxController(RoomGrid roomGrid) {
        this.world = world(roomGrid);
        this.random = new Random();  // Initialize the random object
        this.gameState = new GameState();  // Initialize GameState once at the controller level
    }

    public World world(RoomGrid roomGrid) {
        return new World(roomGrid, new Position(0, 0), new Position(31, 31));
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
        Position randomPosition = getRandomPosition(this.world.getRoomGrid());
        Player newPlayer = new Player(user.getName(), user.getType(), this.world, randomPosition);
        newPlayer.initializePlayer(user.getName(), user.getType());

        // Return the URL for the action page
        String redirectUrl = "/play?playerName=" + newPlayer.getName();
        return ResponseEntity.ok(redirectUrl);
    }

    @PostMapping("/play")
    public ResponseEntity<String> performAction(@RequestBody Action action) {
        String commandName = action.getCommand();
        System.out.println("Command: " + commandName);
        JSONArray arguments = new JSONArray(action.getArguments());
        System.out.println("Arguments: " + arguments);
        Command command = CommandFactory.createCommand(commandName, arguments);

        // Check for the winning player
        if (gameState.getWinningPlayer() != null) {
            String winner = gameState.getWinningPlayer().getName();
            System.out.println("Winner: " + winner);
            return ResponseEntity.ok("Winner: " + winner);
        } else {
            Player player = this.world.getPlayer(action.getName());

            // Handle stamina and health recovery
            if (player.getMaxStamina() > player.getStamina()) {
                Delay.delay(54000, player, false);
            }

            if (player.getMaxHealth() > player.getHealth()) {
                Delay.delay(60000, player, true);
            }

            // If the player has the flag, set them as the winner
            if (player.getInventory().contains("flag")) {
                System.out.println("flag has been captured");
                gameState.setWinningPlayer(player);
                JSONObject response = new JSONObject();
                response = gameState.getGameState();
                return ResponseEntity.ok(response.toString());
            }
            System.out.println(player.getName() + player.getInventory());

            // Execute the command and return the response
            JSONObject response = command.execute(player, world);
            return ResponseEntity.ok(response.toString());
        }
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
