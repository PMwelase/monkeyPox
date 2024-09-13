package no.org.ServerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.World;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DumpCommand extends CommandManager{

    private World world;
    private RoomGrid roomGrid;

    public DumpCommand(World world) {
        this.world = world;
        this.roomGrid = world.getRoomGrid();
        dump();
    }

    public void dump() {
        List<Player> players = world.getPlayersInWorld();
        List<JSONObject> playerNames = new ArrayList<>();
        for (Player player : players) {
            JSONObject playerJson = new JSONObject();
            playerJson.put("name", player.getName());
            playerJson.put("position", player.getPosition().toString());
            playerNames.add(playerJson);
        }
        System.out.println("Players in world: " + playerNames);
    }

}
