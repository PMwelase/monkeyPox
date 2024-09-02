package no.org.World;

import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final Position topLeft;
    private final Position bottomRight;
    private final RoomGrid roomGrid;  // Added RoomGrid

    public static List<Player> playersInWorld = new ArrayList<>();

    public World(RoomGrid roomGrid, Position topLeft, Position bottomRight) {
        this.roomGrid = roomGrid;  // Initialize RoomGrid
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public RoomGrid getRoomGrid() {
        return roomGrid;
    }

    public Position getTopLeft() {
        return topLeft;
    }

    public Position getBottomRight() {
        return bottomRight;
    }

    public void setPlayerInWorld(Player newPlayer) {
        playersInWorld.add(newPlayer);
    }

    public void removePlayerInWorld(Player player) {
        playersInWorld.remove(player);
    }

    public List<Player> getPlayersInWorld() {
        return playersInWorld;
    }

    public void setPlayersInWorld(List<Player> newPlayers) {
        playersInWorld = newPlayers;
    }

    public void clearPlayersInWorld() {
        playersInWorld.clear();
    }

}
