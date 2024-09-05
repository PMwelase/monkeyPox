package no.org.PlayerPackage;

import no.org.World.Position;
import no.org.World.World;

public class Player {
    private String name;
    private String type;
    private int health;
    private int maxHealth;
    private int experience;
    private int level;
    private String weapon;

    private World world;
    private Position position;

    public Player(String name, String type, World world, Position position) {
        this.name = name;
        this.type = type;
        this.world = world;
        this.position = position;
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

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
