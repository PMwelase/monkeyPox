package no.org.PlayerCommands;

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

    public String getName() {
        return name;
    }

    // Abstract method to execute the command
    public abstract JSONObject execute(Player player, World world);
}

