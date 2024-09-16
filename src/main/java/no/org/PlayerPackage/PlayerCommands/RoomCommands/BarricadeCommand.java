package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.ErrorCommand;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomUtility.RoomUtility;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;

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

        JSONObject response = new JSONObject();

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        response.put("status", "success");

        if (player.isInRoom() && RoomUtility.canBarricadeRoom(currentRoom)) {
            RoomUtility.addBarricade(currentRoom);
            player.setStamina(player.getStamina() - staminaCost);
            response.put("message", "Barricade added to room");
        } else if (!player.isInRoom()) {
            response.put("message", "You cannot barricade a building from the outside.");
        } else if (!RoomUtility.canBarricadeRoom(currentRoom)) {
            response.put("message", "Room is already full of barricades");
        } else {
            return new ErrorCommand().execute(player, world);
        }
        return response;
    }
}
