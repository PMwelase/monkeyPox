package no.org.PlayerPackage;

import no.org.Rooms.Room;
import no.org.World.Position;
import no.org.World.World;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private int experience;
    private int pistolAmmo = 0;
    private int level;
    private int kills;
    private int friendlyKills;
    private int enemyKills;
    private String weapon;
    private boolean isInRoom;
    private Room currentRoom;
    private List<String> inventory = new java.util.ArrayList<>();

    private World world;
    private Position position;

    public Player(String name, String type, World world, Position position) {
        this.name = name;
        this.type = type;
        this.world = world;
        this.position = position;
    }

    public void initializePlayer(String playerName, String type) {
        this.name = playerName;
        this.type = type;
        this.level = 1;
        this.maxHealth = 50;
        this.weapon = "fist";
        this.experience = 0;
        this.health = 5;


        switch (type.toLowerCase()) {
            case "ape":
                this.weapon = "fist";
                break;

            case "hound":
                this.weapon = "claws";
                break;

            case "survivor":
                this.weapon = "fist";
                this.setInventory(new ArrayList<>(List.of("spray can", "spray can")));
                break;
        }

        world.setPlayerInWorld(this);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public World getWorld() {
        return world;
    }

    public int getHealth(){
        return health;
    }

    public void setHealth(int health){
        this.health = health;
    }

    public int getMaxHealth(){
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    public int getExperience(){
        return experience;
    }

    public void setExperience(int experience){
        this.experience = experience;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setWeapon(String weapon){
        this.weapon = weapon;
    }

    public String getWeapon(){
        return weapon;
    }

    public void setPistolAmmo(int ammo){
        this.pistolAmmo = ammo;
    }

    public int getPistolAmmo(){
        return pistolAmmo;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void addToInventory(String item) {
        inventory.add(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getKills() {
        return kills;
    }

    public void setFriendlyKills(int friendlyKills) {
        this.friendlyKills = friendlyKills;
    }

    public int getFriendlyKills() {
        return friendlyKills;
    }

    public void setEnemyKills(int enemyKills) {
        this.enemyKills = enemyKills;
    }

    public int getEnemyKills() {
        return enemyKills;
    }

    public boolean isInRoom() {
        return isInRoom;
    }

    public void setInRoom(boolean isInRoom) {
        this.isInRoom = isInRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void enterRoom(Room room) {
        this.currentRoom = room;
        this.isInRoom = true;
        room.setPlayerInRoom(this);
    }

    public void leaveRoom(Room room) {
        this.currentRoom = room;
        room.removePlayerInRoom(this);
        this.isInRoom = false;
    }
}
