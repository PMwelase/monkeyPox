package no.org.ItemsPackage.Weapons;

public class Fist extends Weapon{
    private boolean inUse;


    public Fist(int x, int y, String serialNumber) {
        super("fist", x, y, serialNumber);
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

    @Override
    public String getAttackType() {
        return "stabbed";
    }
}
