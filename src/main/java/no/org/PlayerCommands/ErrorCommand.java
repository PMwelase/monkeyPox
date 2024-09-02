package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

public class ErrorCommand extends Command {

    public ErrorCommand() {
        super("error");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        JSONObject response = new JSONObject();
        response.put("status", "error");
        response.put("message", "Unknown command");
        return response;
    }
}

