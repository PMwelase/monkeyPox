package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.ErrorCommand;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.StaminaCheck;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.Collectors;

public class PickUpItem extends Command {

    private final String item;
    private final int staminaCost = 1;


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

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }


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

        player.setStamina(player.getStamina() - staminaCost);
        return response;
    }
}
