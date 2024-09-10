package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;
import no.org.PlayerPackage.PlayerCommands.Command;

public class StaminaCheck {
    private int stamina;
    public static boolean canPerformAction(Player player, Command command){
        return player.getStamina() >= command.getStaminaCost();
    }
}
