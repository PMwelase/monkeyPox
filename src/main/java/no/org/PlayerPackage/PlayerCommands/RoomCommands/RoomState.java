package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

public class RoomState {
    public JSONObject getRoomState(Player player, World world) {
        JSONObject roomState = new JSONObject();

        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());

        roomState.put("Name", room.getName());
        roomState.put("Type", room.getType());
        String tag = "";

        if (player.isInRoom()) {
            roomState.put("Location", "Indoors");
            roomState.put("Tag", room.getInteriorTag());
            roomState.put("Players", room.getPlayersInRoom());
            roomState.put("Items", room.getItemsInRoomInterior());
            roomState.put("Weapons", room.getWeaponsInRoom());
        } else {
            roomState.put("Location", "Outside");
            roomState.put("Tag", room.getExteriorTag());
            roomState.put("Players", room.getPlayersInRoom());
            roomState.put("Items", room.getItemsInRoomExterior());
            roomState.put("Weapons", room.getWeaponsExRoom());
        }
        return roomState;
    }
}
