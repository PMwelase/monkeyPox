package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;


public class EnterRoomCommand extends Command {

    public EnterRoomCommand() {
        super("enter");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        player.enterRoom(room);
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "You have entered the room.");
        return response;
    }
}