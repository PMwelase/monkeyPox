package no.org.ItemsPackage.Weapons;

import no.org.World.Position;
import no.org.World.World;

public class Shotgun extends Weapon {
    private int shells;

    private boolean inUse;

    public Shotgun(int x, int y, String serialNumber) {
        super("shotgun", x, y, serialNumber);
        this.shells = 1;
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

    public Shotgun getShotgun(){
        return this;
    }


    private void setInUse(boolean bool){
        this.inUse =bool;
    }


    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public int getQuantity() {
        return 0;
    }

    @Override
    public void setQuantity(int quantity) {

    }

}