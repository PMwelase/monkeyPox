package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;
import org.json.JSONArray;

public class GridState {
    public JSONObject getGrid(Player player, World world) {
        JSONObject gridState = new JSONObject();
        JSONArray roomTypes = new JSONArray();

        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();

        int playerX = position.getX();
        int playerY = position.getY();

        // Loop through the rooms around the player's position (3x3 grid)
        for (int x = playerX - 1; x <= playerX + 1; x++) {
            for (int y = playerY - 1; y <= playerY + 1; y++) {
                // Check if the room is within the grid bounds
                if (x >= 0 && x <= roomGrid.getWidth() && y >= 0 && y <= roomGrid.getHeight()) {
                    Room room = roomGrid.getRoom(x, y);
                    JSONObject roomInfo = new JSONObject();
                    roomInfo.put("x", x);
                    roomInfo.put("y", y);
                    roomInfo.put("type", room.getType());
                    roomInfo.put("Color", room.getColor());

                    roomTypes.put(roomInfo);
                }
            }
        }

        gridState.put("rooms", roomTypes);
        return gridState;
    }
}
