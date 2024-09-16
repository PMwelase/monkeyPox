package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomState;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

import java.util.Stack;

public class MovementHelper {

    public static JSONObject movePlayer(Player player, World world, int moveValue, boolean isJump) {
        Position currentPosition = player.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(currentPosition.getX(), currentPosition.getY());

        switch (moveValue) {
            case 1:
                x -= 1;
                y += 1;
                break;
            case 2:
                y += 1;
                break;
            case 3:
                x += 1;
                y += 1;
                break;
            case 4:
                x -= 1;
                break;
            case 6:
                x += 1;
                break;
            case 7:
                x -= 1;
                y -= 1;
                break;
            case 8:
                y -= 1;
                break;
            case 9:
                x += 1;
                y -= 1;
                break;
            default:
                JSONObject response = new JSONObject();
                response.put("status", "failure");
                response.put("message", "Invalid move value!");
                return response;
        }

        Position newPosition = new Position(x, y);

        JSONObject response = new JSONObject();

        if (newPosition.isIn(world.getBottomLeft(), world.getTopRight())) {
            String move = isJump ? "jumped" : "moved";
            if (!isJump) {
                player.leaveRoom(currentRoom);
            }

            player.setPosition(newPosition);

            Room newRoom = roomGrid.getRoom(x, y);

            response.put("status", "success");
            response.put("message", "You " + move + " to position: (" + x + ", " + y + ")");
            RoomState roomState = new RoomState();
            response.put("roomState", roomState.getRoomState(player, world));
            response.put("playerState", new StateCommand().execute(player, world));
        }
        else {
            response.put("message", "Can't leave the city.");
        }

        return response;
    }
}
