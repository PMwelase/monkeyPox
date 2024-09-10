package no.org.PlayerPackage.PlayerCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;
import org.json.JSONObject;

public class DamageCalculator {

    public int calculateDamage(Player player, JSONObject response) {
        Weapon weapon = player.getWeapon();
        int damage;

        if (weapon != null && weapon.usesAmmo() && weapon.getAmmo() > 0) {
            damage = weapon.getDamage();
            weapon.setAmmo(weapon.getAmmo() - 1);
            response.put("remainingAmmo", weapon.getAmmo());
            response.put("attackType", weapon.getAttackType());
        } else if (weapon != null && !weapon.usesAmmo()) {
            damage = weapon.getDamage();
            response.put("attackType", weapon.getAttackType());
        } else {
            damage = 1;
            response.put("attackType", "punched");
        }
        return damage;
    }

}
