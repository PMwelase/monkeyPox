package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;

public class HealthIncrease {

    public static void increaseHealth(Player player, int amount) {
        player.setHealth(player.getHealth() + amount);
    }
}
