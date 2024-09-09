package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

public class StateCommand extends Command {

    public StateCommand(){
        super("state");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("Position", player.getPosition());
        response.put("Type", player.getType());
        response.put("Health", player.getHealth());
        response.put("Max-Health", player.getMaxHealth());
        response.put("Experience", player.getExperience());
        response.put("Level", player.getLevel());
        response.put("Item in hand", player.getItem());
        if (player.getWeapon() != null) {
            response.put("Weapon in hand", player.getWeapon().getName());
        }
        response.put("Pistol Ammo", player.getPistolAmmo());
        JSONArray weapons = new JSONArray();
        for (Weapon weapon : player.getWeapons()) {
            weapons.put(weapon.getName());
        }
        response.put("Weapons Inventory", weapons);
        response.put("Items Inventory", player.getInventory());
        response.put("Enemy Kills", player.getEnemyKills());
        response.put("Friendly Kills", player.getFriendlyKills());
        response.put("Kills", player.getKills());
        response.put("Deaths", player.getDeathCount());
        response.put("Stamina", player.getStamina());
        return response;
    }
}
