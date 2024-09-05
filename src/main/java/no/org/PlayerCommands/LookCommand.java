package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;

public class LookCommand extends Command {

    public LookCommand() {
        super("look");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "Room Tag: " + currentRoom.getTag());
        return response;
    }
}
