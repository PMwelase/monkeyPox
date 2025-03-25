package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;
import no.org.Protocols.Response;

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

        Response response = new Response();
        String message = "";

        if (!StaminaCheck.canPerformAction(player, this)) {
            message = "Not enough stamina to perform the action.";
        }

        if (playerWeapon == null) {
            message = "No weapon in hand to load.";
        }

        String weaponInHand = playerWeapon.getName();

        switch (weaponInHand.toLowerCase()) {
            case "shotgun":
                if (inventory.contains("shells")) {
                    player.removeItem("shells");
                    playerWeapon.setAmmo(16);
                    message = "Loaded your " + playerWeapon.getName() + " with " + playerWeapon.getAmmo() + " shells.";
                } else {
                    message = "No shells available in inventory to load.";
                }
                break;

            case "pistol":
                if (inventory.contains("clip")) {
                    player.removeItem("clip");
                    playerWeapon.setAmmo(10);
                    message = "Loaded your " + weaponName + " with " + playerWeapon.getAmmo() + " bullets.";
                } else {
                    message = "No pistol ammo available in inventory to load.";
                }
                break;

            default:
                message = "Cannot load this type of weapon.";
                break;
        }
        player.setStamina(player.getStamina() - staminaCost);

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
