package no.org.PlayerCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONArray;
import org.json.JSONObject;

public class LookCommand extends Command {

    public LookCommand() {
        super("look");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        String tag = "";

        if (player.isInRoom()) {
            tag = currentRoom.getInteriorTag();
        } else {
            tag = currentRoom.getExteriorTag();
        }

        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "Room Tag: " + tag);

        if (currentRoom.getBarricades() > 0) {
            response.put("Barricades", currentRoom.getBarricades());
        }

        int playerX = position.getX();
        int playerY = position.getY();
        boolean isPlayerInRoom = player.isInRoom();

        JSONArray playersArray = new JSONArray();
        for (Player user : world.getPlayersInWorld()) {
            int userX = user.getPosition().getX();
            int userY = user.getPosition().getY();
            boolean isUserInRoom = user.isInRoom();

            if (!user.getName().equals(player.getName()) &&
                    userX == playerX && userY == playerY &&
                    isUserInRoom == isPlayerInRoom) {
                JSONObject playerInfo = new JSONObject();
                playerInfo.put("Name", user.getName());
                playerInfo.put("Position", user.getPosition().toString());
                playerInfo.put("Type", user.getType());
                playersArray.put(playerInfo);
            }
        }

        // Collect all weapons in the room into a JSONArray
        JSONArray weaponsArray = new JSONArray();
        for (Weapon weapon : currentRoom.getWeaponsInRoomInterior()) {
            weaponsArray.put(weapon.getName());
        }

        // Add the weapons array to the response
        response.put("Weapons", weaponsArray);

        if (player.isInRoom()) {
            response.put("Items on the floor", currentRoom.getItemsInRoomInterior());
        } else {
            response.put("Items on the floor", currentRoom.getItemsInRoomExterior());
        }

        response.put("Players", playersArray);

        return response;
    }
}
