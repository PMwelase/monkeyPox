package no.org.World;

import no.org.PlayerPackage.Player;

public class PlayerCreator {
    private final World world;

    public PlayerCreator(World world) {
        this.world = world;
    }

    public Player setPlayer(String name, String type, Position position) {
        // Ensure you provide all required parameters when creating a Player object
        return new Player(name, type, world, position);
    }
}
