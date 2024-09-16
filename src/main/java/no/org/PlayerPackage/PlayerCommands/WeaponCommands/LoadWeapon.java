package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class LoadWeapon extends Command {
    private final String weaponName;
    private final int staminaCost = 1;

    public LoadWeapon(String weapon) {
        super("load");

        JSONArray jsonArray = new JSONArray(weapon);
        this.weaponName = jsonArray.toList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public JSONObject execute(Player player, World world) {
        List<String> inventory = player.getInventory();
        Weapon playerWeapon = player.getWeapon();
        JSONObject response = new JSONObject();

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        if (playerWeapon == null) {
            response.put("status", "error");
            response.put("message", "No weapon in hand to load.");
            return response;
        }

        String weaponInHand = playerWeapon.getName();

        switch (weaponInHand.toLowerCase()) {
            case "shotgun":
                if (inventory.contains("shells")) {
                    player.removeItem("shells");
                    playerWeapon.setAmmo(16);
                    response.put("status", "success");
                    response.put("message", "Loaded your " + playerWeapon.getName() + " with " + playerWeapon.getAmmo() + " shells.");
                } else {
                    response.put("status", "error");
                    response.put("message", "No shells available in inventory to load.");
                }
                break;

            case "pistol":
                if (inventory.contains("clip")) {
                    player.removeItem("clip");
                    playerWeapon.setAmmo(10);
                    response.put("status", "success");
                    response.put("message", "Loaded your " + weaponName + " with " + playerWeapon.getAmmo() + " bullets.");
                } else {
                    response.put("status", "error");
                    response.put("message", "No pistol ammo available in inventory to load.");
                }
                break;

            default:
                response.put("status", "error");
                response.put("message", "Cannot load this type of weapon.");
                break;
        }
        player.setStamina(player.getStamina() - staminaCost);

        return response;
    }
}
