package no.org.ItemsPackage.Weapons.Survivor;

import no.org.ItemsPackage.Weapons.Weapon;

public class Shotgun extends Weapon {

    private boolean inUse;

    public Shotgun(int x, int y, String serialNumber) {
        super("shotgun", x, y, serialNumber);
    }

    @Override
    public int getDamage() {
        return 4;
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

    public boolean usesAmmo() {
        return true;
    }

    @Override
    public String getAttackType() {
        return "shot";
    }

}