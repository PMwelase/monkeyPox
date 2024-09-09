package no.org.PlayerPackage.PlayerCommands.WorldCommands;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

public class JumpCommand extends Command {

    private final int moveValue;

    public JumpCommand(int moveValue) {
        super("jump");
        this.moveValue = moveValue;
    }

    @Override
    public JSONObject execute(Player player, World world) {
        System.out.println("Move Command: " + moveValue);
        Position currentPosition = player.getPosition();
        int x = currentPosition.getX();
        int y = currentPosition.getY();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(currentPosition.getX(), currentPosition.getY());

        // Update position based on moveValue
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
                // If the move value is not recognized, return an error
                JSONObject response = new JSONObject();
                response.put("status", "failure");
                response.put("message", "Invalid move value!");

                return response;
        }

        Position newPosition = new Position(x, y);
        player.setPosition(newPosition);

        JSONObject response = new JSONObject();
        response.put("status", "success");
        response.put("message", player.getName() + " moved to position: (" + x + ", " + y + ")");
        return response;
    }
}
