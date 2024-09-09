package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.PlayerCommands.WorldCommands.MovementHelper;
import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

public class MoveCommand extends Command {

    private final int moveValue;

    public MoveCommand(int moveValue) {
        super("move");
        this.moveValue = moveValue;
    }

    @Override
    public JSONObject execute(Player player, World world) {

        return MovementHelper.movePlayer(player, world, moveValue);
    }
}
