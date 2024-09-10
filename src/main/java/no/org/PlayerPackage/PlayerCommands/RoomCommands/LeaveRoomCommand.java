package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;


public class LeaveRoomCommand extends Command {
    private final int staminaCost = 1;

    public LeaveRoomCommand() {
        super("enter");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        player.leaveRoom(room);

        response.put("status", "success");
        response.put("message", "You have left the room.");

        player.setStamina(player.getStamina() - staminaCost);

        return response;
    }
}