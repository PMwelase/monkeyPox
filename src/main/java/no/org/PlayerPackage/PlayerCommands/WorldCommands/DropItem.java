package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Protocols.Response;
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

        Response response = new Response();
        String message = "";

        if (inventory.contains(item)) {
            message = "Item dropped: " + item;
            inventory.remove(item);

            if (player.isInRoom()){
                currentRoom.addItemInRoomInterior(item);
            } else {
                currentRoom.addItemInRoomExterior(item);
            }
        }
        else {
            message = "No item in inventory";
        }
        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
