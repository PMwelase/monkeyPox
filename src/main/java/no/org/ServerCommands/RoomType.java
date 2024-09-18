package no.org.ServerCommands;

import no.org.Rooms.Room;
import no.org.World.Position;

public class RoomType {
    public RoomType(int x, int y, String type){
        Room room = new Room(x, y);
        room.setRoomType(type);
    }
}
