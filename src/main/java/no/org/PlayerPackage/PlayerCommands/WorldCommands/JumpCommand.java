package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.StaminaCheck;
import no.org.PlayerPackage.PlayerCommands.WorldCommands.MovementHelper;
import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

public class JumpCommand extends Command {

    private final int moveValue;
    private final int staminaCost = 3;

    public JumpCommand(int moveValue) {
        super("jump");
        this.moveValue = moveValue;
    }

    @Override
    public JSONObject execute(Player player, World world) {

        JSONObject response = new JSONObject();

        if (!StaminaCheck.canPerformAction(player, this)) {
            response.put("status", "failure");
            response.put("message", "Not enough stamina to perform the action.");
            return response;
        }

        if (player.isInRoom()) {
            player.setStamina(player.getStamina() - staminaCost);
            return MovementHelper.movePlayer(player, world, moveValue, true);
        }
        else {
            response.put("message", "You cannot jump from outside the room.");
            return response;
        }
    }
}
