package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;

public class HitCommand extends Command {

    private final String targetName;

    public HitCommand(String targetName) {
        super("hit");
        this.targetName = targetName;
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room currentRoom = roomGrid.getRoom(position.getX(), position.getY());

        int playerX = position.getX();
        int playerY = position.getY();
        Boolean isPlayerInRoom = player.isInRoom();

        JSONObject response = new JSONObject();

        for (Player user : world.getPlayersInWorld()) {
            int userX = user.getPosition().getX();
            int userY = user.getPosition().getY();
            Boolean isUserInRoom = user.isInRoom();

            if (user.getName().equals(targetName) &&
                    !user.getName().equals(player.getName()) &&
                    userX == playerX && userY == playerY &&
                    isUserInRoom == isPlayerInRoom) {
                user.setHealth(user.getHealth() - 1);

                if (user.getHealth() <= 0) {
                    response.put("status", "success");
                    response.put("message", targetName + " has been hit and is now down.");
                } else {
                    response.put("status", "success");
                    response.put("message", targetName + " has been hit. Remaining health: " + user.getHealth());
                }
                return response;
            }
        }

        response.put("status", "failure");
        response.put("message", "Target not found or invalid.");
        return response;
    }
}
