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
import no.org.Protocols.Response;

public class BreakCommand extends Command {
    private final int staminaCost = 3;

    public BreakCommand() {
        super("break");
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
        }

        if (currentRoom.getBarricades() > 0 && player.getInventory().contains("crow bar")) {
            currentRoom.setBarricades(currentRoom.getBarricades() - 1);
            message = "weakened barricades.";
        } else if (!player.getInventory().contains("crow bar")){
            message = "don't have the tools to perform this action.";
        } else {
            message = "There are no barricades in the room to remove";
        }
        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
