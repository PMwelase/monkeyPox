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
        response.put("Position", player.getPosition());
        response.put("Type", player.getType());
        response.put("Health", player.getHealth());
        response.put("Max-Health", player.getMaxHealth());
        response.put("Experience", player.getExperience());
        response.put("Level", player.getLevel());
        response.put("Weapon", player.getWeapon());
        return response;
    }
}
