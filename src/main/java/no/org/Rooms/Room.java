package no.org.Rooms;

import no.org.PlayerPackage.Player;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private String tag;
    public static List<Player> playersInRoom = new ArrayList<>();
    private int barricades;
    private int xCoordinate;
    private int yCoordinate;

    public Room(int x, int y) {
        this.xCoordinate = x;
        this.yCoordinate = y;
    }

    public void setPlayerInRoom(Player newPlayer) {
        playersInRoom.add(newPlayer);
    }

    public void removePlayerInRoom(Player player) {
        playersInRoom.remove(player);
    }

    public void tagRoom(String newTag) {
        this.tag = newTag;
    }

    public String getTag() {
        return this.tag;
    }

    public void setBarricades(int barricades) {
        this.barricades = barricades;
    }

    public int increaseBarricades() {
        return this.barricades++;
    }

    public int getBarricades() {
        return barricades;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    public String setRoomType(String type) {
        return type;
    }
}
