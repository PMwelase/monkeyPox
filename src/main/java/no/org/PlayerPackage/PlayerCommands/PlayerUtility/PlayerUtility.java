package no.org.PlayerPackage.PlayerCommands.PlayerUtility;

import no.org.PlayerPackage.Player;
import no.org.World.Position;

public class PlayerUtility {

    public static boolean arePlayersInSamePosition(Player player1, Player player2) {
            Position pos1 = player1.getPosition();
            Position pos2 = player2.getPosition();

            // Compare both X and Y coordinates
            return pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY();
        }
}
