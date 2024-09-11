package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;
import java.util.concurrent.atomic.AtomicBoolean;

public class Delay {
    private static final AtomicBoolean isRecoveringStamina = new AtomicBoolean(false);
    private static final AtomicBoolean isRecoveringHealth = new AtomicBoolean(false);

    public static void delay(int milliseconds, Player player, boolean isHealthRecovery) {
        AtomicBoolean recoveryFlag = isHealthRecovery ? isRecoveringHealth : isRecoveringStamina;

        if (recoveryFlag.compareAndSet(false, true)) {  // Ensure only one recovery thread runs for each type
            new Thread(() -> {
                try {
                    Thread.sleep(milliseconds);
                    if (isHealthRecovery) {
                        HealthIncrease.increaseHealth(player, 1);
                        System.out.println("Recovered 1 health.");
                    } else {
                        StaminaIncrease.increaseStamina(player, 1);
                        System.out.println("Recovered 1 stamina.");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    recoveryFlag.set(false);
                }
            }).start();
        }
    }
}
