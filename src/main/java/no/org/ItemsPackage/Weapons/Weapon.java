package no.org.ItemsPackage.Weapons;

import no.org.ItemsPackage.Item;
import no.org.World.Position;
import no.org.World.World;

public abstract class Weapon implements Item {
    private String name;

    private String serialNumber;

    private boolean inUse;
    private boolean inRoom;

    private World world;
    private Position position;

    private int ammo;

    public Weapon(String name, int x, int y, String serialNumber) {
        this.name = name;
        this.inUse = false;
        this.position = new Position(x, y);
        this.serialNumber = serialNumber;
        this.ammo = 0;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    private void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    public boolean getInUse(){
        return inUse;
    }

    private void setInRoom(Boolean bool){
        this.inRoom = bool;
    }

    public Boolean getInRoom(){
        return inRoom;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }


    public abstract int getDamage();
}
