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

public class DoorCommand extends Command {
    private final int staminaCost = 1;

    public DoorCommand() {
        super("door");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        Response response = new Response();
        String message = "";

        // Check if the player has enough stamina to perform the action
        if (!StaminaCheck.canPerformAction(player, this)) {
            message = "Not enough stamina to perform the action.";
        }

        // Player tries to enter the room if they're not already in it
        if (!player.isInRoom()) {
            if (RoomUtility.canEnterRoom(room)) {
                RoomUtility.enterRoom(player, room);
                message =  "entered the room.";
            } else {
                message = "can't enter. The room is barred.";
            }
        }
        // Player tries to leave the room if they're currently in it
        else if (player.isInRoom()) {
            player.leaveRoom(room);
            message = "left the room.";
        }


        player.setStamina(player.getStamina() - staminaCost);

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
