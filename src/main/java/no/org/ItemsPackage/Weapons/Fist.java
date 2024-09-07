package no.org.ItemsPackage.Weapons;

public class Fist extends Weapon {

    public Fist() {
        super("Pistol");
    }

    @Override
    public int getDamage() {
        return 1;
    }
}
