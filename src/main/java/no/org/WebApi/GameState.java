package no.org.WebApi;

import no.org.PlayerPackage.Player;
import org.json.JSONObject;

public class GameState {
    Player player = null;

    public Player getWinningPlayer() {
        return player;
    }

    public void setWinningPlayer(Player player) {
        this.player = player;
    }


    public JSONObject getGameState() {
        JSONObject gameState = new JSONObject();
        gameState.put("message", "Game Over! " + player.getName() + " wins!");
        return gameState;
    }

}
