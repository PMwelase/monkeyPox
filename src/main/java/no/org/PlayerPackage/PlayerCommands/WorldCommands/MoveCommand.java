package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

public class MoveCommand extends Command {

    private final int staminaCost = 2;

    private final int moveValue;

    public MoveCommand(int moveValue) {
        super("move");
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
        player.setStamina(player.getStamina() - staminaCost);
        return MovementHelper.movePlayer(player, world, moveValue, false);
    }
}
