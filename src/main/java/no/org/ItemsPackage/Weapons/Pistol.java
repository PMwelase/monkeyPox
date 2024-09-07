package no.org.ItemsPackage.Weapons;

import no.org.World.Position;

public class Pistol extends Weapon {
    private int ammo;

    public Pistol() {
        super("Pistol");
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

}
