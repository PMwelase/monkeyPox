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

        JSONArray jsonArray = new JSONArray(weaponName);

        this.weaponName = jsonArray.getString(0);
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        JSONObject response = new JSONObject();

        if (player.isInRoom()) {
            if (currentRoom.getWeaponsInRoom().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "No weapons in the room.");
                return response;
            }

            Weapon weaponToPickUp = currentRoom.getWeaponsInRoom().stream()
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
            if (currentRoom.getWeaponsExRoom().isEmpty()) {
                response.put("status", "fail");
                response.put("message", "No weapons outside the room.");
                return response;
            }

            Weapon weaponToPickUp = currentRoom.getWeaponsExRoom().stream()
                    .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                    .findFirst()
                    .orElse(null);

            if (weaponToPickUp != null) {
                currentRoom.removeWeaponExRoom(weaponToPickUp);
                player.addToWeapons(weaponToPickUp);
                response.put("status", "success");
                response.put("message", "Picked up " + weaponToPickUp.getName() + " from room exterior.");
            } else {
                response.put("status", "fail");
                response.put("message", "Weapon '" + weaponName + "' not found outside the room.");
            }
        }


        return response;
    }
}
