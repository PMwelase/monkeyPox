package no.org.ItemsPackage.Weapons;

public class Knife extends Weapon{
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
    public int getQuantity() {
        return 0;
    }

    @Override
    public void setQuantity(int quantity) {

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
}
