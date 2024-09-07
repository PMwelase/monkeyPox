package no.org.ItemsPackage.Weapons;

public class Knife extends Weapon{
    private int uses;
//    private int accuracy;

    public Knife() {
        super("knife");
        this.uses = 100;
    }

    @Override
    public int getDamage() {
        return 2;
    }

    public int getUses() {
        return uses;
    }

    public void setUses(int uses) {
        this.uses = uses;
    }

//    public int getAccuracy(){
//        return this.accuracy;
//    }
}
