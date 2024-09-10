package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;
import java.util.concurrent.atomic.AtomicBoolean;

public class Delay {
    private static final AtomicBoolean isRecoveringStamina = new AtomicBoolean(false);

    public static void delay(int milliseconds, Player player) {
        if (isRecoveringStamina.compareAndSet(false, true)) {  // Ensure only one recovery thread runs
            new Thread(() -> {
                try {
                    Thread.sleep(milliseconds);
                    StaminaIncrease.increaseStamina(player, 1);
                    System.out.println("Recovered 1 stamina.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    isRecoveringStamina.set(false);
                }
            }).start();
        }
    }
}
