package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONArray;
import org.json.JSONObject;

public class BarricadeCommand extends Command{

    public BarricadeCommand(){
        super("barricade");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();
        response.put("status", "success");

        if (player.isInRoom() && currentRoom.getBarricades() <= 20){
            currentRoom.setBarricades(currentRoom.getBarricades() + 1);
            response.put("message", "Barricade added to room");
        }
        else {
            return new ErrorCommand().execute(player, world);
        }
        return response;
    }

}
