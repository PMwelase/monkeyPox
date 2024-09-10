package no.org.ItemsPackage.Weapons.Survivor;

import no.org.ItemsPackage.Weapons.Weapon;

public class Pistol extends Weapon {
    private int ammo;

    public Pistol(int x, int y, String serialNumber) {
        super("Pistol", x, y, serialNumber);
        this.ammo = 0;
    }

    @Override
    public int getDamage() {
        return 3;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public Pistol getPistol(){
        return this;
    }

    @Override
    public boolean usesAmmo() {
        return true;
    }

    @Override
    public String getAttackType() {
        return "shot";
    }

}
