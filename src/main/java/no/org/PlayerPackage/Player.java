package no.org.PlayerPackage;

import no.org.ItemsPackage.Weapons.Infectant.Claws;
import no.org.ItemsPackage.Weapons.Weapon;
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
    private int stamina;
    private int maxStamina;

    private int pistolAmmo = 0;
    private int level;
    private int kills;
    private int friendlyKills;
    private int enemyKills;
    private Weapon weapon;
    private String item;
    private boolean isInRoom;
    private Room currentRoom;
    private List<String> inventory = new java.util.ArrayList<>();
    private List<Weapon> weapons = new ArrayList<>();
    private int deathCount;


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
        this.stamina = 100;
        this.maxStamina = 100;
        this.experience = 0;
        this.health = 5;
        this.deathCount = 0;



        switch (type.toLowerCase()) {
            case "ape":
                this.type = "ape";
                this.weapon = new Claws(0, 0, "sss");
                break;

//            case "hound":
////                this.weapon = "claws";
//                break;

            case "survivor":
                this.type = "survivor";
                this.item = "spray can";
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

    public int getStamina() {
        return stamina;
    }

    public void setStamina(int stamina) {
        this.stamina = stamina;
    }

    public void addStamina(int amount) {
        this.stamina += amount;
    }

    public int getMaxStamina() {
        return maxStamina;
    }

    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
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

    public void setItem(String item){
        this.item = item;
    }

    public String getItem(){
        return item;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
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
        return this.inventory;
    }

    public void addToInventory(String item) {
        this.inventory.add(item);
    }

    public void removeItem(String item) {
        inventory.remove(item);
    }

    public List<Weapon> getWeapons(){
        return weapons;
    }

    public void addToWeapons(Weapon weapon) {
        weapons.add(weapon);
    }

    public void removeWeapon(Weapon weapon){
        weapons.remove(weapon);
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

    public int getDeathCount() {
        return deathCount;
    }

    public void setDeathCount(int deathCount) {
        this.deathCount = deathCount;
    }

    public void incrementDeathCount() {
        this.deathCount++;
    }

}
