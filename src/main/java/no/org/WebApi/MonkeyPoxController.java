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

@RestController
@RequestMapping("/monkeypox")
public class MonkeyPoxController {

    private final World world;

    public MonkeyPoxController(RoomGrid roomGrid) {
        this.world = world(roomGrid);
    }

    public World world(RoomGrid roomGrid) {
        return new World(roomGrid, new Position(0, 0), new Position(30, 30));
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Player user) {
        Player newPlayer = new Player(user.getName(), user.getType(), this.world, new Position(0, 0));
        newPlayer.initializePlayer(user.getName(), user.getType());
        return ResponseEntity.ok("User added to world as Player: " + newPlayer.getName());
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
