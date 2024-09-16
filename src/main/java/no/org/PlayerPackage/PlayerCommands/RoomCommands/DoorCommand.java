package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomUtility.RoomUtility;
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

        JSONObject response = new JSONObject();

        // Check if the player has enough stamina to perform the action
        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        // Player tries to enter the room if they're not already in it
        if (!player.isInRoom()) {
            if (RoomUtility.canEnterRoom(room)) {
                RoomUtility.enterRoom(player, room);
                response.put("message", "You have entered the room.");
            } else {
                response.put("message", "The room is barred.");
            }
        }
        // Player tries to leave the room if they're currently in it
        else if (player.isInRoom()) {
            player.leaveRoom(room);
            response.put("message", "You have left the room.");
        }

        response.put("status", "success");
        player.setStamina(player.getStamina() - staminaCost);

        return response;
    }
}
