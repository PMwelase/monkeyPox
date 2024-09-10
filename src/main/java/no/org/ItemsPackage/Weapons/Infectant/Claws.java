package no.org.ItemsPackage.Weapons.Infectant;

import no.org.ItemsPackage.Weapons.Weapon;

public class Claws extends Weapon {
    private boolean inUse;


    public Claws(int x, int y, String serialNumber) {
        super("claws", x, y, null);
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
        return "swiped";
    }
}
