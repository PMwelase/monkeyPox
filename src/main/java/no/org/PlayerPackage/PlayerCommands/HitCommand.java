package no.org.PlayerPackage.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.PlayerUtility;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Protocols.Response;
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
        Response responseObject = new Response();
        String message;

        // Check stamina first
        if (!StaminaCheck.canPerformAction(player, this)) {
            message = "Not enough stamina to perform the action.";
            JSONObject response = responseObject.buildResponse(player, world);
            response.put("message", message);
            return response;
        }

        // Search for target
        for (Player target : world.getPlayersInWorld()) {
            if (isValidTarget(player, target)) {
                JSONObject damageResponse = new JSONObject();
                int damage = damageCalculator.calculateDamage(player, damageResponse);

                // Apply damage
                target.setHealth(target.getHealth() - damage);

                // Handle kill logic
                if (target.getHealth() <= 0) {
                    handleKill(player, target);
                    message = buildKillMessage(player, target, damage);
                } else {
                    message = buildHitMessage(damageResponse, target, damage);
                }

                // Update attacker stamina
                player.setStamina(player.getStamina() - staminaCost);

                // Build response and merge damage details
                JSONObject response = responseObject.buildResponse(player, world);
                response.put("message", message);
                damageResponse.keySet().forEach(key -> response.put(key, damageResponse.get(key)));
                return response;
            }
        }

        // Target not found
        message = "Target not found or invalid.";
        JSONObject response = responseObject.buildResponse(player, world);
        response.put("message", message);
        return response;
    }

    private boolean isValidTarget(Player attacker, Player target) {
        return target.getName().equals(targetName)
                && !target.getName().equals(attacker.getName())
                && PlayerUtility.arePlayersInSamePosition(attacker, target)
                && target.getHealth() > 0;
    }

    private void handleKill(Player attacker, Player target) {
        attacker.setKills(attacker.getKills() + 1);
        target.setHealth(0);
        target.incrementDeathCount();

        if (Objects.equals(target.getType(), attacker.getType())) {
            attacker.setFriendlyKills(attacker.getFriendlyKills() + 1);
        } else {
            attacker.setEnemyKills(attacker.getEnemyKills() + 1);
        }
    }

    private String buildKillMessage(Player attacker, Player target, int damage) {
        String type = Objects.equals(target.getType(), attacker.getType()) ? "friendly" : "enemy";
        int kills = Objects.equals(target.getType(), attacker.getType())
                ? attacker.getFriendlyKills()
                : attacker.getEnemyKills();
        return String.format("%s (%s) has been killed. %s kills: %d. Damage dealt: %d",
                targetName, type, type, kills, damage);
    }

    private String buildHitMessage(JSONObject damageResponse, Player target, int damage) {
        String attackType = damageResponse.optString("attackType", "punched");
        return String.format("%s %s. Remaining health: %d. Damage dealt: %d",
                attackType, targetName, target.getHealth(), damage);
    }
}