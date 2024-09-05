package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

public class StateCommand extends Command {

    public StateCommand(){
        super("state");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", player.getName() + " Position: " + player.getPosition() + " Type: " + player.getType());
        return response;
    }
}
