package no.org.PlayerCommands;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("launch", new LaunchCommand());
        // Add other commands as needed
    }

    public static Command createCommand(String commandName) {
        System.out.println("Create Command: " + commandName);
        return commands.getOrDefault(commandName.toLowerCase(), new ErrorCommand());
    }
}

