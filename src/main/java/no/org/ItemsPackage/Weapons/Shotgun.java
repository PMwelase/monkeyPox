package no.org.ItemsPackage.Weapons;

import no.org.World.Position;
import no.org.World.World;

public class Shotgun extends Weapon {
    private int shells;
    private String serialNumber;

    private World world;
    private Position position;

    private boolean inUse;
    private boolean inRoom;

    public Shotgun(int x, int y, String serialNumber) {
        super("shotgun");
        this.shells = 1;
        this.inUse = false;
        this.position = new Position(x, y);
        this.serialNumber = serialNumber;
    }

    @Override
    public int getDamage() {
        return 25;
    }

    public int getAmmo() {
        return shells;
    }

    public void setAmmo(int ammo) {
        this.shells = ammo;
    }

    public void setSerialNumber(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber(){
        return this.serialNumber;
    }

    public Shotgun getShotgun(){
        return this;
    }

    private void setPosition(Position position){
        this.position = position;
    }

    public Position getPosition(){
        return this.position;
    }

    private void setInUse(boolean bool){
        this.inUse =bool;
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

    @Override
    public String getName() {
        return super.getName();
    }

}