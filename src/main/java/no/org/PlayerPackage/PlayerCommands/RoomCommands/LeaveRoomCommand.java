package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;


public class LeaveRoomCommand extends Command {

    public LeaveRoomCommand() {
        super("enter");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        player.leaveRoom(room);
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "You have left the room.");
        return response;
    }
}