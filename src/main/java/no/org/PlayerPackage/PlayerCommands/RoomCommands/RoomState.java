package no.org.PlayerPackage.PlayerCommands.RoomCommands;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.Position;
import no.org.World.World;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomState {
    public JSONObject getRoomState(Player player, World world) {
        JSONObject roomState = new JSONObject();

        Position position = player.getPosition();
        RoomGrid roomGrid = world.getRoomGrid();
        Room room = roomGrid.getRoom(position.getX(), position.getY());
        List<String> weaponsList = new ArrayList<>();
        List<String> weaponsListOut = new ArrayList<>();
        List<Weapon> weapons = room.getWeaponsInRoom();
        List<Weapon> WeaponsOut = room.getWeaponsExRoom();
        List<String> players = new ArrayList<>();


        for (Weapon weapon : weapons) {
            weaponsList.add(weapon.getName());
        };

        for (Weapon weapon : WeaponsOut) {
            weaponsListOut.add(weapon.getName());
        }

        for (Player player1 : world.getPlayersInWorld()) {
            if (!Objects.equals(player1.getName(), player.getName()) &&
                    player1.getPosition().getX() == position.getX() &&
                    player1.getPosition().getY() == position.getY())
            {
                players.add(player1.getName());
            }
        }

        roomState.put("Name", room.getName());
        roomState.put("Type", room.getType());
        String tag = "";

        if (player.isInRoom()) {
            roomState.put("Location", "Indoors");
            roomState.put("Tag", room.getInteriorTag());
            roomState.put("Players", room.getPlayersInRoom());
            roomState.put("Items", room.getItemsInRoomInterior());
            roomState.put("Weapons", weaponsList);
            roomState.put("Players", players);
        } else {
            roomState.put("Location", "Outside");
            roomState.put("Tag", room.getExteriorTag());
            roomState.put("Players", room.getPlayersInRoom());
            roomState.put("Items", room.getItemsInRoomExterior());
            roomState.put("Weapons", weaponsListOut);
            roomState.put("Players", players);
        }
        return roomState;
    }
}
