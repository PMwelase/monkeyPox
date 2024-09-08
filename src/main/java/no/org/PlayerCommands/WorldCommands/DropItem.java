package no.org.PlayerCommands.WorldCommands;

import no.org.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class DropItem extends Command {


    String item;

    public DropItem(String item){
        super("drop");

        JSONArray jsonArray = new JSONArray(item);
        this.item = jsonArray.toList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        List<String> inventory = player.getInventory();

        JSONObject response = new JSONObject();

        if (inventory.contains(item)) {
            response.put("status", "success");
            response.put("message", "Item dropped: " + item);
            inventory.remove(item);

            if (player.isInRoom()){
                currentRoom.addItemInRoomInterior(item);
            } else {
                currentRoom.addItemInRoomExterior(item);
            }
        }
        else {
            response.put("status", "success");
            response.put("message", "No item in inventory");
        }
        return response;
    }
}
