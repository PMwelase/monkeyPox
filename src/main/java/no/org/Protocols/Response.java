package no.org.Protocols;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.GridState;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomState;
import no.org.PlayerPackage.PlayerCommands.WorldCommands.StateCommand;
import no.org.World.World;
import org.json.JSONObject;

public class Response {

    // Method to build and return the response as a JSONObject
    public JSONObject buildResponse(Player player, World world) {
        JSONObject response = new JSONObject();  // Initialize the response JSON object

        response.put("status", "success");  // Set the status to success

        // Add grid state to the response
        GridState gridState = new GridState();
        response.put("grid", gridState.getGrid(player, world));

        // Add room state to the response
        RoomState roomState = new RoomState();
        response.put("roomState", roomState.getRoomState(player, world));

        // Add player state to the response
        response.put("playerState", new StateCommand().execute(player, world));

        return response;  // Return the constructed JSONObject
    }
}
