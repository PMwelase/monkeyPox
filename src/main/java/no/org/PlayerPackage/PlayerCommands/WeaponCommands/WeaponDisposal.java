package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Protocols.Response;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class WeaponDisposal extends Command {

    String weaponName;

    public WeaponDisposal(String weaponName) {
        super("lose");

        JSONArray jsonArray = new JSONArray(weaponName);
        this.weaponName = jsonArray.getString(0);
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());
        Response response = new Response();
        String message = "";

        boolean isEquipped = false;

        if (currentRoom == null) {
            message = "No room found at the player's current position.";
        }

        List<Weapon> weapons = player.getWeapons();

        Weapon targetWeapon = weapons.stream()
                .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                .findFirst()
                .orElse(null);

        if (targetWeapon == null && player.getWeapon() != null && Objects.equals(player.getWeapon().getName(), weaponName)) {
            targetWeapon = player.getWeapon();
            isEquipped = true;
        }

        if (targetWeapon == null) {
            message = "Weapon '" + weaponName + "' not found in player's inventory.";
        }

        if (isEquipped) {
            player.setWeapon(null);
        } else {
            weapons.remove(targetWeapon);
        }

        if (player.isInRoom()) {
            currentRoom.getWeaponsInRoom().add(targetWeapon);
        } else {
            currentRoom.getWeaponsExRoom().add(targetWeapon);
        }

        if (isEquipped) {
            message = "Disposed of " + targetWeapon.getName() + " (Serial number: " + targetWeapon.getSerialNumber() + ")";
        } else {
            message = "Disposed of weapon: " + targetWeapon.getName() + " (Serial number: " + targetWeapon.getSerialNumber() + ")";
        }

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
