package no.org.World;

import no.org.ItemsPackage.Weapons.Survivor.Shotgun;
import no.org.PlayerPackage.Player;
import no.org.Rooms.RoomGrid;

import java.util.ArrayList;
import java.util.List;

public class World {
    private final Position bottomLeft;
    private final Position topRight;
    private final RoomGrid roomGrid;

    public static List<Player> playersInWorld = new ArrayList<>();
    //Temp
    public static List<Shotgun> shotgunsInWorld = new ArrayList<>();
    public World(RoomGrid roomGrid, Position bottomLeft, Position topRight) {
        this.roomGrid = roomGrid;
        this.bottomLeft = bottomLeft;
        this.topRight = topRight;
    }

    public RoomGrid getRoomGrid() {
        return roomGrid;
    }

    public Position getBottomLeft() {
        return bottomLeft;
    }

    public Position getTopRight() {
        return topRight;
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

    public Player getPlayer(String name) {
        for (Player player : playersInWorld) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
    
    //Temp
    public List<Shotgun> getShotgunsInWorld(){
        return shotgunsInWorld;
    }
    //Temp
    public void setShotgunInWorld(Shotgun shotgun) {
        shotgunsInWorld.add(shotgun);
    }

    public void setPlayersInWorld(List<Player> newPlayers) {
        playersInWorld = newPlayers;
    }

    public void clearPlayersInWorld() {
        playersInWorld.clear();
    }

}
