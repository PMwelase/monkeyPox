package no.org.PlayerCommands;

import no.org.ItemsPackage.Weapons.Weapon;
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
        response.put("Weapon", player.getWeapon());
        response.put("Pistol Ammo", player.getPistolAmmo());
        JSONArray weapons = new JSONArray();
        for (Weapon weapon : player.getWeapons()) {
            weapons.put(weapon.getName());
        }
        response.put("Weapons", weapons);
        response.put("Inventory", player.getInventory());
        response.put("Enemy Kills", player.getEnemyKills());
        response.put("Friendly Kills", player.getFriendlyKills());
        response.put("Kills", player.getKills());
        return response;
    }
}
