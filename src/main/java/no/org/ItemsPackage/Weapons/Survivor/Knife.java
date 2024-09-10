package no.org.ItemsPackage.Weapons.Survivor;

import no.org.ItemsPackage.Weapons.Weapon;

public class Knife extends Weapon {
    private boolean inUse;


    public Knife(int x, int y, String serialNumber) {
        super("knife", x, y, serialNumber);
    }

    @Override
    public int getDamage(){
        return 2;
    }

    public boolean getInUse() {
        return inUse;
    }

    public String getName() {
        return super.getName();
    }

    @Override
    public boolean usesAmmo() {
        return false;
    }

    @Override
    public int getAmmo() {
        return 100;
    }

    @Override
    public void setAmmo(int ammo) {
    }

    @Override
    public String getAttackType() {
        return "stabbed";
    }
}
