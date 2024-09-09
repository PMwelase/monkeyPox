package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

public class BreakCommand extends Command {
    public BreakCommand() {
        super("break");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();
        response.put("status", "success");

        if (currentRoom.getBarricades() > 0 && player.getInventory().contains("crow bar")){
            currentRoom.setBarricades(currentRoom.getBarricades() - 1);
            response.put("message", "Barricade removed from room");
        } else {
            response.put("message", "There are no barricades in the room to remove");
        }
        return response;
    }
}
