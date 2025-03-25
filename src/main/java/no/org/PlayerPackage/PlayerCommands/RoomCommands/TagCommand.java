package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomUtility.RoomUtility;
import no.org.Protocols.Response;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;
import org.json.JSONArray;

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
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        Response response = new Response();
        String message = "";

        List<String> inventory = player.getInventory();


        // Check if the player has enough stamina to perform the action
        if (!StaminaCheck.canPerformAction(player, this)) {
            message = "Not enough stamina to perform the action.";
        }

        if (inventory.contains("spray can")) {
            message=  "Room tagged as: " + tag;

            if (player.isInRoom()){
                room.setInteriorTag(tag);
            } else {
                room.setExteriorTag(tag);
            }

            inventory.remove("spray can");
        }

        else {
            message = "have no spray can in inventory";
        }

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        System.out.println(response1);
        return response1;
    }
}
