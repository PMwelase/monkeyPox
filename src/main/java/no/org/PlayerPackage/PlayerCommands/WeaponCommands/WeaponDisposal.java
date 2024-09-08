package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

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

        List<Weapon> weapons = player.getWeapons();

        Weapon targetWeapon = weapons.stream()
                .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                .findFirst()
                .orElse(null);

        if (targetWeapon == null) {
            JSONObject response = new JSONObject();
            response.put("status", "fail");
            response.put("message", "Weapon '" + weaponName + "' not found in player's inventory.");
            return response;
        }

        weapons.remove(targetWeapon);

        if (player.isInRoom()) {
            currentRoom.getWeaponsInRoom().add(targetWeapon);
        } else {
            currentRoom.getWeaponsExRoom().add(targetWeapon);
        }

        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", "Disposed of weapon: " + targetWeapon.getName() + " Serial number: " + targetWeapon.getSerialNumber());

        return response;
    }
}
