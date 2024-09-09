package no.org.PlayerPackage.PlayerCommands;

import no.org.PlayerPackage.Player;

public class StaminaCheck {
    private int stamina;
    public static boolean canPerformAction(Player player, Command command){
        return player.getStamina() >= command.getStaminaCost();
    }
}
