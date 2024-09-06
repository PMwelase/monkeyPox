package no.org.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;

import java.util.Objects;

public class HitCommand extends Command {

    private final String targetName;

    public HitCommand(String targetName) {
        super("hit");
        this.targetName = targetName;
    }

    @Override
    public JSONObject execute(Player player, World world) {
        Position position = player.getPosition();

        int playerX = position.getX();
        int playerY = position.getY();
        boolean isPlayerInRoom = player.isInRoom();

        JSONObject response = new JSONObject();

        for (Player user : world.getPlayersInWorld()) {
            int userX = user.getPosition().getX();
            int userY = user.getPosition().getY();
            boolean isUserInRoom = user.isInRoom();

            if (user.getName().equals(targetName) &&
                    !user.getName().equals(player.getName()) &&
                    userX == playerX && userY == playerY &&
                    isUserInRoom == isPlayerInRoom &&
                    user.getHealth() >= 1) {
                user.setHealth(user.getHealth() - 1);

                if (user.getHealth() <= 0) {

                    response.put("status", "success");

                    player.setKills(player.getKills() + 1);

                    if (Objects.equals(user.getType(), player.getType())) {
                        player.setFriendlyKills(player.getFriendlyKills() + 1);
                        response.put("message", targetName + " has been killed. Remaining health: " + user.getHealth() +
                                " Friendly kills: " + player.getFriendlyKills());
                    } else {
                        player.setEnemyKills(player.getEnemyKills() + 1);
                        response.put("message", targetName + " has been killed. Remaining health: " + user.getHealth() +
                                " Enemy kills: " + player.getEnemyKills());
                    }
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
