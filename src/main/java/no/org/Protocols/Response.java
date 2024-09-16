package no.org.Protocols;

import no.org.PlayerPackage.Player;
import org.json.JSONObject;

public class Response {
    public JSONObject response;

    public Response(Player player, JSONObject object) {
        response = new JSONObject();

        String locationStatus = player.isInRoom() ? "In doors" : "On the street";

        response.put("Location", locationStatus);

        response.put("PlayerData", object);
    }
}
