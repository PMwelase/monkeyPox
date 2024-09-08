package no.org.PlayerPackage.PlayerCommands;

import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

import no.org.PlayerPackage.Player;
import no.org.World.World;
import org.json.JSONObject;

public abstract class Command {
    private final String name;

    protected Command(String name) {
        this.name = name;
    }
    public abstract JSONObject execute(Player player, World world);
}

