package no.org.Rooms;

import no.org.ItemsPackage.Weapons.Weapon;
import no.org.PlayerPackage.Player;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public static List<Player> playersInRoom = new ArrayList<>();
    private List<Weapon> weaponsInRoom = new ArrayList<>();
    private List<Weapon> weaponsExRoom = new ArrayList<>();
    private List<String> itemsInRoomInterior = new ArrayList<>();
    private List<String> itemsInRoomExterior = new ArrayList<>();
    private int barricades;
    private final int xCoordinate;
    private final int yCoordinate;
    private String interiorTag;
    private String exteriorTag;

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

    public void setInteriorTag(String interiorTag) {
        this.interiorTag = interiorTag;
    }

    public String getInteriorTag() {
        return this.interiorTag;
    }

    public void setExteriorTag(String exteriorTag) {
        this.exteriorTag = exteriorTag;
    }

    public String getExteriorTag() {
        return this.exteriorTag;
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

    public String setRoomType(String type) {
        return type;
    }

    public List<String> getItemsInRoomInterior() {
        return itemsInRoomInterior;
    }

    public void addItemInRoomInterior(String item) {
        itemsInRoomInterior.add(item);
    }


    public void removeItemInRoomInterior(String item) {
        itemsInRoomInterior.remove(item);
    }

    public List<String> getItemsInRoomExterior() {
        return itemsInRoomExterior;
    }

    public void addItemInRoomExterior(String item) {
        itemsInRoomExterior.add(item);
    }

    public void removeItemInRoomExterior(String item) {
        itemsInRoomExterior.remove(item);
    }

    public void addWeaponInRoom(Weapon weapon) {
        weaponsInRoom.add(weapon);
    }

    public List<Weapon> getWeaponsInRoom(){
        return weaponsInRoom;
    }

    public void removeWeaponInRoom(Weapon weapon){
        weaponsInRoom.remove(weapon);
    }

    public void addWeaponExRoom(Weapon weapon) {
        weaponsExRoom.add(weapon);
    }

    public List<Weapon> getWeaponsExRoom() {
        return weaponsExRoom;
    }

    public void removeWeaponExRoom(Weapon weapon) {
        weaponsExRoom.remove(weapon);
    }

}
