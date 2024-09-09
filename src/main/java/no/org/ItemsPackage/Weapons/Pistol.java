package no.org.ItemsPackage.Weapons;

import no.org.World.Position;

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
    public int getQuantity() {
        return 0;
    }

    @Override
    public void setQuantity(int quantity) {

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
