package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Protocols.Response;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

public class StowWeapon extends Command {

    String weaponName;

    public StowWeapon(String weaponName) {
        super("stow");

        JSONArray jsonArray = new JSONArray(weaponName);
        this.weaponName = jsonArray.getString(0);
    }

    @Override
    public JSONObject execute(Player player, World world) {
        List<Weapon> weapons = player.getWeapons();
        Weapon equippedWeapon = player.getWeapon();

        Response response = new Response();
        String message = "";

        if (equippedWeapon != null && Objects.equals(equippedWeapon.getName(), weaponName)) {
            weapons.add(equippedWeapon);
            player.setWeapon(null);

            message = "Weapon '" + equippedWeapon.getName() + "' stowed in inventory.";
        } else {
            message =  "Player is not currently armed with " + weaponName + ".";
        }

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
