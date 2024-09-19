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

        int gridWidth = roomGrid.getWidth();
        int gridHeight = roomGrid.getHeight();

        // Loop through the 3x3 grid around the player's position, including out-of-bounds areas
        for (int x = playerX - 1; x <= playerX + 1; x++) {
            for (int y = playerY - 1; y <= playerY + 1; y++) {
                JSONObject roomInfo = new JSONObject();

                // Check if the coordinates are out of bounds
                if (x < 0 || x >= gridWidth || y < 0 || y >= gridHeight) {
                    // For out-of-bounds areas, send "edge"
                    roomInfo.put("x", x);
                    roomInfo.put("y", y);
                    roomInfo.put("type", "edge");
                    roomInfo.put("Color", "CFBAF0");
                } else {
                    // For valid rooms within bounds
                    Room room = roomGrid.getRoom(x, y);
                    if (room != null) {
                        roomInfo.put("x", x);
                        roomInfo.put("y", y);
                        roomInfo.put("type", room.getType());
                        roomInfo.put("Color", room.getColor());
                    }
                }

                // Add the room (or edge) information to the list
                roomTypes.put(roomInfo);
            }
        }

        gridState.put("rooms", roomTypes);
        return gridState;
    }
}

