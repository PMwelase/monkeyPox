package no.org.PlayerCommands;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("launch", new LaunchCommand());
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

        else if ("look".equalsIgnoreCase(commandName)){
            return new LookCommand();
        }

        else if ("tag".equalsIgnoreCase(commandName)) {
            if (!arguments.isEmpty()) {
                String tag = String.valueOf(arguments);
                return new TagCommand(tag);
            } else {
                System.out.println("Tag command missing tag in arguments");
                return new ErrorCommand();
            }
        }

        else if ("hit".equalsIgnoreCase(commandName)) {
            if (!arguments.isEmpty()) {
                String targetName = arguments.get(0).toString();
                return new HitCommand(targetName);
            } else {
                System.out.println("Hit command missing target name in arguments");
                return new ErrorCommand();
            }
        }

        else if ("enter".equalsIgnoreCase(commandName)){
            return new EnterRoomCommand();
        }

        else if ("leave".equalsIgnoreCase(commandName)){
            return new LeaveRoomCommand();
        }

        else if ("barricade".equalsIgnoreCase(commandName)){
            return new BarricadeCommand();
        }

        else if ("drop".equalsIgnoreCase(commandName)){
            return new DropItem(String.valueOf(arguments));
        }

        Command command = commands.get(commandName.toLowerCase());
        if (command == null) {
            System.out.println("Command not recognized, defaulting to error command");
            return new ErrorCommand();
        }

        return command;
    }
}
