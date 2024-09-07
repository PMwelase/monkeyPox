package no.org.ItemsPackage.Weapons;

public class Crowbar extends Weapon {
    public Crowbar() {
        super("Crowbar");
    }

    @Override
    public int getDamage() {
        return 2;
    }
}