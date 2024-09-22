package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Protocols.Response;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

public class PickUpWeapon extends Command {

    private final String weaponName;
    private final int staminaCost = 1;

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
        Response response1 = new Response();
        String message = "";
        Weapon weaponToPickUp = null;
        boolean hasWeapon = false;


        // Check if the player already has the weapon
        for (Weapon weapon : player.getWeapons()) {
            if (weapon.getName().equalsIgnoreCase(weaponName)) {
                message = "You already have a " + weaponName + " in your inventory.";
                response.put("message", message);
                hasWeapon = true;
            }
        }


        if (!hasWeapon) {
        if (player.isInRoom() && currentRoom != null && !currentRoom.getWeaponsInRoom().isEmpty()) {
            weaponToPickUp = currentRoom.getWeaponsInRoom().stream()
                    .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                    .findFirst()
                    .orElse(null);
        } else if (!player.isInRoom() && currentRoom != null && !currentRoom.getWeaponsExRoom().isEmpty()) {
            weaponToPickUp = currentRoom.getWeaponsExRoom().stream()
                    .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                    .findFirst()
                    .orElse(null);
        }

        if (weaponToPickUp != null) {
            if (player.isInRoom()) {
                currentRoom.removeWeaponInRoom(weaponToPickUp);
            } else {
                currentRoom.removeWeaponExRoom(weaponToPickUp);
            }
            player.setStamina(player.getStamina() - staminaCost);
            player.addToWeapons(weaponToPickUp);
            message = "picked up " + weaponToPickUp.getName() + " (Serial Number: " + weaponToPickUp.getSerialNumber() + ").";
        } else {
            message = "could not find " + weaponName + " to pick up.";
        }
        }
        response.put("message", message);
        JSONObject responseJson = response1.buildResponse(player, world);
        responseJson.put("message", message);
        System.out.println(responseJson);
        return responseJson;
    }
}
