package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.Collectors;

public class PickUpItem extends Command {

    private final String item;

    public PickUpItem(String item) {
        super("take");

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

        JSONObject response = new JSONObject();


        if (!player.isInRoom() && currentRoom.getItemsInRoomExterior().contains(item)) {
            currentRoom.getItemsInRoomExterior().remove(item);
            player.addToInventory(item);
            response.put("status", "success");
            response.put("message", "Item picked up from room exterior: " + item);
        }

        else if (player.isInRoom() && currentRoom.getItemsInRoomInterior().contains(item)) {
            currentRoom.getItemsInRoomInterior().remove(item);
            player.addToInventory(item);
            response.put("status", "success");
            response.put("message", "Item picked up from room interior: " + item);
        }
        else {
            return new ErrorCommand().execute(player, world);
        }

        return response;
    }
}
