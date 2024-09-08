package no.org.PlayerCommands.WorldCommands;

import no.org.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

import java.util.Random;

public class LaunchCommand extends Command {

    public LaunchCommand() {
        super("launch");
    }

    @Override
    public JSONObject execute(Player player, World world) {
        // Generate random coordinates within the world's bounds
        Random random = new Random();
        int x = random.nextInt(world.getBottomRight().getX() + 1);
        int y = random.nextInt(world.getBottomRight().getY() + 1);

        // Set the player's position to the random coordinates
        Position newPosition = new Position(x, y);
        player.setPosition(newPosition);

        // Return a JSON response
        JSONObject response = new JSONObject();
        String name = player.getName();
        response.put("status", "success");
        response.put("message", name + " launched to position: (" + x + ", " + y + ")");
        return response;
    }
}

