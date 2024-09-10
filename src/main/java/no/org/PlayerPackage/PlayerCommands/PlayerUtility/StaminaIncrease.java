package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;


public class StaminaIncrease {

    public static void increaseStamina(Player player, int amount) {
        player.setStamina(player.getStamina() + amount);
    }
}
