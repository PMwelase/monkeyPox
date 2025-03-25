package no.org.PlayerPackage.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Protocols.Response;
import no.org.World.World;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.stream.Collectors;

public class UseItem extends Command {

    private final String item;

    public UseItem(String item) {
        super("use");

        JSONArray jsonArray = new JSONArray(item);
        this.item = jsonArray.toList().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
    }

    @Override
    public JSONObject execute(Player player, World world) {
        List<String> inventory = player.getInventory();

        Response response = new Response();
        String message = "";

        if (inventory.contains(item)) {
            player.setItem(item);
            player.removeItem(item);

            message = "Item in hand: " + item + ".";
        } else {
            message = item +  "not found in inventory.";
        }

        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        return response1;
    }
}
