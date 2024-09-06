package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
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
        JSONObject response = new JSONObject();

        if (inventory.contains(item)) {
            player.setWeapon(item);
            player.removeItem(item);

            response.put("status", "success");
            response.put("message", "Item in hand: " + item);
        } else {
            response.put("status", "error");
            response.put("message", "Item not found in inventory");
        }

        return response;
    }
}
