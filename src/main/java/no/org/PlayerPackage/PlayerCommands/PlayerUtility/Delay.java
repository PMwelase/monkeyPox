package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;
import java.util.concurrent.atomic.AtomicBoolean;

public class Delay {
    private static final AtomicBoolean isRecoveringStamina = new AtomicBoolean(false);
    private static final AtomicBoolean isRecoveringHealth = new AtomicBoolean(false);

    public static void delay(int milliseconds, Player player, boolean isHealthRecovery) {
        AtomicBoolean recoveryFlag = isHealthRecovery ? isRecoveringHealth : isRecoveringStamina;

        // Ensure only one recovery thread runs for each type (health or stamina)
        if (recoveryFlag.compareAndSet(false, true)) {
            new Thread(() -> {
                try {
                    // Keep checking and recovering until the player is fully recovered
                    while (isPlayerRecoveryNeeded(player, isHealthRecovery)) {
                        Thread.sleep(milliseconds);  // Delay between each recovery attempt

                        // Perform health or stamina recovery
                        if (isHealthRecovery) {
                            if (player.getHealth() < player.getMaxHealth()) {
                                HealthIncrease.increaseHealth(player, 1);
                                System.out.println("Recovered 1 health.");
                            }
                        } else {
                            if (player.getStamina() < player.getMaxStamina()) {
                                StaminaIncrease.increaseStamina(player, 1);
                                System.out.println("Recovered 1 stamina.");
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Reset the flag so recovery can restart when needed
                    recoveryFlag.set(false);
                }
            }).start();
        }
    }

    // Helper method to check if recovery is needed (either stamina or health)
    private static boolean isPlayerRecoveryNeeded(Player player, boolean isHealthRecovery) {
        if (isHealthRecovery) {
            return player.getHealth() < player.getMaxHealth();
        } else {
            return player.getStamina() < player.getMaxStamina();
        }
    }
}
