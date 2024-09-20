package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.GridState;
import no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomState;
import no.org.Protocols.Response;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.util.Stack;

public class MovementHelper {

    public static JSONObject movePlayer(Player player, World world, int moveValue, boolean isJump) {
        Position currentPosition = player.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(currentPosition.getX(), currentPosition.getY());
        String direction = "";

        switch (moveValue) {
            case 1:
                x -= 1;
                y += 1;
                direction = "North West";
                break;
            case 2:
                y += 1;
                direction = "North";
                break;
            case 3:
                x += 1;
                y += 1;
                direction = "North East";
                break;
            case 4:
                x -= 1;
                direction = "West";
                break;
            case 6:
                x += 1;
                direction = "East";
                break;
            case 7:
                x -= 1;
                y -= 1;
                direction = "South West";
                break;
            case 8:
                y -= 1;
                direction = "South";
                break;
            case 9:
                x += 1;
                y -= 1;
                direction = "South East";
                break;
            default:
                JSONObject response = new JSONObject();
                response.put("status", "failure");
                response.put("message", "Invalid move value!");
                return response;
        }

        Position newPosition = new Position(x, y);
        Response response = new Response();
        String message = "";

        if (x >= roomGrid.getWidth() || y >= roomGrid.getHeight() || x < 0 || y < 0) {
            message = "Can't leave the city.";
        }
        else {
            String move = isJump ? "jumped" : "moved";
            if (!isJump) {
                player.leaveRoom(currentRoom);
            }
            player.setPosition(newPosition);

            message = "You " + move + " 1 step " +direction + " to " + newPosition + ".";
        }
        JSONObject response1 = response.buildResponse(player, world);
        response1.put("message", message);
        System.out.println(response1);

        return response1;
    }
}
