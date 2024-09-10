package no.org.PlayerPackage.PlayerCommands.RoomCommands.RoomUtility;

import no.org.PlayerPackage.PlayerCommands.Command;
import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.PlayerUtility.StaminaCheck;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import no.org.Rooms.Room;
import org.json.JSONObject;

public class RoomUtility {

    public static boolean canEnterRoom(Room room) {
        return room.getBarricades() == 0;
    }

    public static boolean canBarricadeRoom(Room room) {
        return room.getBarricades() < 20;
    }

    public static void addBarricade(Room room) {
        room.setBarricades(room.getBarricades() + 1);
    }

    public static void enterRoom(Player player, Room room) {
        player.enterRoom(room);
    }
}
