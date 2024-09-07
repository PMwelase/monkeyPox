package no.org.PlayerCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

public class PickUpWeapon extends Command {

    private final String weaponName;

    public PickUpWeapon(String weaponName) {
        super("pickup");
        String jsonString = "[\"shotgun\"]";

        JSONArray jsonArray = new JSONArray(jsonString);

        this.weaponName = jsonArray.getString(0);
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();

        if (player.isInRoom()) {
            if (currentRoom.getWeaponsInRoomInterior().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "No weapons in room.");
                return response;
            }

            Weapon weaponToPickUp = currentRoom.getWeaponsInRoomInterior().stream()
                    .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                    .findFirst()
                    .orElse(null);

            if (weaponToPickUp != null) {
                currentRoom.removeWeaponInRoom(weaponToPickUp);
                player.addToWeapons(weaponToPickUp);
                response.put("status", "success");
                response.put("message", "Picked up " + weaponToPickUp.getName() + " from room interior.");
            } else {
                response.put("status", "fail");
                response.put("message", "Weapon '" + weaponName + "' not found in room.");
            }
        } else {
            response.put("status", "fail");
            response.put("message", "Player is not in a room.");
        }

        return response;
    }
}
