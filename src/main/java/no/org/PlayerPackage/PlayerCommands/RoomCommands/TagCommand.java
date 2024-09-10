package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class TagCommand extends Command {
    private final String tag;
    private final int staminaCost = 1;


    public TagCommand(String tag) {
        super("tag");

        JSONArray jsonArray = new JSONArray(tag);

        this.tag = jsonArray.toList().stream()
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

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        if (inventory.contains("spray can")) {
            response.put("status", "success");
            response.put("message", "Room tagged as: " + tag);

            if (player.isInRoom()){
                currentRoom.setInteriorTag(tag);
            } else {
                currentRoom.setExteriorTag(tag);
            }

            inventory.remove("spray can");
        }

        else {
            response.put("status", "success");
            response.put("message", "No spray can in inventory");
            System.out.println("You don't have a spray can");
        }

        player.setStamina(player.getStamina() - staminaCost);
        return response;
    }
}
