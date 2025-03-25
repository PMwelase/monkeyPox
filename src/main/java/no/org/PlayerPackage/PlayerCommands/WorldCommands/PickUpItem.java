package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.ErrorCommand;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Protocols.Response;
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

        Response response = new Response();
        String message = "";

        if (!StaminaCheck.canPerformAction(player, this)) {
            message = "Not enough stamina to perform the action.";

        } else {

            if (!player.isInRoom() && currentRoom.getItemsInRoomExterior().contains(item)) {
                currentRoom.getItemsInRoomExterior().remove(item);
                player.addToInventory(item);

            } else if (player.isInRoom() && currentRoom.getItemsInRoomInterior().contains(item)) {
                currentRoom.getItemsInRoomInterior().remove(item);
                player.addToInventory(item);
            }
            player.setStamina(player.getStamina() - staminaCost);
            message = "picked up " + item + ".";
        }
        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
