package no.org.PlayerPackage.PlayerCommands.WeaponCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Protocols.Response;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class UseWeapon extends Command {
    private final String weaponName;

    public UseWeapon(String weapon) {
        super("arm");

        JSONArray jsonArray = new JSONArray(weapon);
        this.weaponName = jsonArray.toList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public JSONObject execute(Player player, World world) {
        List<Weapon> weaponList = player.getWeapons();

        Weapon targetWeapon = weaponList.stream()
                .filter(weapon -> weapon.getName().equalsIgnoreCase(weaponName))
                .findFirst()
                .orElse(null);

        Response response = new Response();
        String message = "";

        if (weaponList.contains(targetWeapon)) {
            player.setWeapon(targetWeapon);
            player.removeWeapon(targetWeapon);

            message = "Weapon in hand: " + weaponName;
        } else {
            message =  "Weapon not found in inventory";
        }

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
