package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.StaminaCheck;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;


public class EnterRoomCommand extends Command {
    private final int staminaCost = 1;


    public EnterRoomCommand() {
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

        if (room.getBarricades() == 0) {
            player.enterRoom(room);
            response.put("message", "You have entered the room.");
        }
        else {
            response.put("message", "The room is barred.");
        }

        response.put("status", "success");

        player.setStamina(player.getStamina() - staminaCost);

        return response;
    }
}