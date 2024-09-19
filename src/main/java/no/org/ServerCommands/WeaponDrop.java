package no.org.ServerCommands;

import no.org.ItemsPackage.Weapons.Survivor.Knife;
import no.org.ItemsPackage.Weapons.Survivor.Pistol;
import no.org.Rooms.Room;
import no.org.Rooms.RoomGrid;
import no.org.World.World;
import no.org.ItemsPackage.Weapons.Weapon;
import no.org.ItemsPackage.Weapons.Survivor.Shotgun;

import java.util.Random;

public class WeaponDrop {
    private World world;
    private RoomGrid roomGrid;
    private Random random;

    // Array or list of weapon instances
    private Weapon[] weapons = {
            new Shotgun(0, 0, "SG-123"),
            new Pistol(0, 0, "P-124"),
            new Knife(0, 0, "K-456"),
    };

    public WeaponDrop(World world) {
        this.world = world;
        this.roomGrid = world.getRoomGrid();
        this.random = new Random();  // Initialize random number generator
        dropWeapon();
    }

    public void dropWeapon() {
        for (int x = 0; x < roomGrid.getWidth(); x++) {
            for (int y = 0; y < roomGrid.getHeight(); y++) {
                Room room = roomGrid.getRoom(x, y); // Get the room at (x, y)

                if (room != null) {
                    // Randomly select a weapon from the weapons array
                    Weapon randomWeapon = weapons[random.nextInt(weapons.length)];

                    // Randomly decide to drop inside or outside (50% chance for each)
                    boolean dropInside = random.nextBoolean();

                    if (dropInside) {
                        room.addWeaponInRoom(randomWeapon);
                        System.out.println("Dropped " + randomWeapon.getName() + " inside the room at (" + x + ", " + y + ").");
                    } else {
                        room.addWeaponExRoom(randomWeapon);
                        System.out.println("Dropped " + randomWeapon.getName() + " outside the room at (" + x + ", " + y + ").");
                    }

                    // Optionally, set the weapon's position based on where it was dropped
                    randomWeapon.getPosition().setX(x);
                    randomWeapon.getPosition().setY(y);
                }
            }
        }
    }
}
