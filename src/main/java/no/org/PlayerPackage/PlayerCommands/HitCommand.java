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
    private final DamageCalculator damageCalculator;

    public HitCommand(String targetName) {
        super("hit");
        this.targetName = targetName;
        this.damageCalculator = new DamageCalculator();
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
            if (target.getName().equals(targetName) &&
                    !target.getName().equals(player.getName()) &&
                    PlayerUtility.arePlayersInSamePosition(player, target) &&
                    target.getHealth() > 0) {

                int damage = damageCalculator.calculateDamage(player, response);
                target.setHealth(target.getHealth() - damage);

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
                    response.put("message", response.get("attackType") + " " + targetName + ". " +
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
