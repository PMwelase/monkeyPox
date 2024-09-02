package no.org.World;

import no.org.PlayerPackage.Player;

public class PlayerCreator {
    private World world;

    public PlayerCreator(World world) {
        this.world = world;
    }

    public World world() {
        return this.world;
    }

    public Player createPlayer(String name) {
        Player player = new Player(); // Assuming you have a default constructor in Player
        player.setName(name); // Set the name if needed
        this.world.setPlayerInWorld(player); // Add player to the world
        return player;
    }
}
