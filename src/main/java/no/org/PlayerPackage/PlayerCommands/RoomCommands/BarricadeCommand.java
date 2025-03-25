package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomUtility.RoomUtility;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;
import no.org.Protocols.Response;

public class BarricadeCommand extends Command {
    private final int staminaCost = 2;

    public BarricadeCommand(){
        super("barricade");
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

        if (player.isInRoom() && RoomUtility.canBarricadeRoom(currentRoom)) {
            RoomUtility.addBarricade(currentRoom);
            player.setStamina(player.getStamina() - staminaCost);
            message = "added a barricade to the room";
        } else if (!player.isInRoom()) {
            message = "cannot barricade a building from the outside.";
        } else if (!RoomUtility.canBarricadeRoom(currentRoom)) {
            message = " can't add any more barricades";
        }
        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
