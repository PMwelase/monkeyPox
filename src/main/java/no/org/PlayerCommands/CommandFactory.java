package no.org.PlayerCommands;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("launch", new LaunchCommand());
        // No need to register MoveCommand as it requires an argument
    }

    public static Command createCommand(String commandName, JSONArray arguments) {
        System.out.println("Create Command: " + commandName);

        if ("move".equalsIgnoreCase(commandName)) {
            try {
                if (!arguments.isEmpty()) {
                    int moveValue = Integer.parseInt(arguments.get(0).toString());
                    System.out.println("Move value parsed: " + moveValue);
                    return new MoveCommand(moveValue);
                } else {
                    System.out.println("Move command missing value in arguments");
                    return new ErrorCommand();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid move value in arguments");
                return new ErrorCommand();
            }
        }

        else if ("state".equalsIgnoreCase(commandName)){
            return new StateCommand();
        }

        Command command = commands.get(commandName.toLowerCase());
        if (command == null) {
            System.out.println("Command not recognized, defaulting to error command");
            return new ErrorCommand();
        }

        return command;
    }
}
