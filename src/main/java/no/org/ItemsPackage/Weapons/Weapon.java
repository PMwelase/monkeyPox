package no.org.ItemsPackage.Weapons;

import no.org.ItemsPackage.Item;

public abstract class Weapon implements Item {
    private String name;
    private int quantity;

    public Weapon(String name) {
        this.name = name;
        this.quantity = 1;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public abstract int getDamage();
}
