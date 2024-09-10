package no.org.PlayerPackage.PlayerCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.PlayerUtility;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.World.World;
import org.json.JSONObject;

import java.util.Objects;

public class HitCommand extends Command {

    private final String targetName;
    private final int staminaCost = 2;

    public HitCommand(String targetName) {
        super("hit");
        this.targetName = targetName;
    }

    @Override
    public JSONObject execute(Player player, World world) {
        JSONObject response = new JSONObject();

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        for (Player target : world.getPlayersInWorld()) {
            System.out.println(targetName + player.getName() + "in position" + PlayerUtility.arePlayersInSamePosition(player, target));
            if (target.getName().equals(targetName) &&
                    !target.getName().equals(player.getName()) &&
                    PlayerUtility.arePlayersInSamePosition(player, target) &&
                    target.getHealth() > 0) {

                Weapon weapon = player.getWeapon();
                int damage = 0;
                String attackType;

                if (weapon != null && weapon.usesAmmo() && weapon.getAmmo() > 0) {
                    damage = weapon.getDamage();
                    target.setHealth(target.getHealth() - damage);
                    weapon.setAmmo(weapon.getAmmo() - 1);
                    response.put("remainingAmmo", weapon.getAmmo());
                    attackType = weapon.getAttackType();
                } else if (weapon != null && !weapon.usesAmmo()) {

                    damage = weapon.getDamage();
                    target.setHealth(target.getHealth() - damage);
                    attackType = weapon.getAttackType();
                } else {
                    damage = 1;
                    target.setHealth(target.getHealth() - damage);
                    attackType = "punched";
                }

                if (target.getHealth() <= 0) {
                    response.put("status", "success");
                    player.setKills(player.getKills() + 1);
                    target.setHealth(0);
                    target.incrementDeathCount();

                    if (Objects.equals(target.getType(), player.getType())) {
                        player.setFriendlyKills(player.getFriendlyKills() + 1);
                        response.put("message", targetName + " (friendly) has been killed. " +
                                "Friendly kills: " + player.getFriendlyKills() +
                                ". Damage dealt: " + damage);
                    } else {
                        player.setEnemyKills(player.getEnemyKills() + 1);
                        response.put("message", targetName + " (enemy) has been killed. " +
                                "Enemy kills: " + player.getEnemyKills() +
                                ". Damage dealt: " + damage);
                    }
                } else {
                    response.put("status", "success");
                    response.put("message", "You " + attackType + " " + targetName + ". " +
                            "Remaining health: " + target.getHealth() +
                            ". Damage dealt: " + damage);
                }

                player.setStamina(player.getStamina() - staminaCost);
                return response;
            }
        }

        response.put("status", "failure");
        response.put("message", "Target not found or invalid.");
        return response;
    }
}
