package no.org.PlayerPackage.PlayerCommands;

import no.org.PlayerPackage.PlayerCommands.RoomCommands.*;
import no.org.PlayerPackage.PlayerCommands.WeaponCommands.*;
import no.org.PlayerPackage.PlayerCommands.WorldCommands.*;
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

//        if ("jump".equalsIgnoreCase(commandName)) {
//            try {
//                if (!arguments.isEmpty()) {
//                    int moveValue = Integer.parseInt(arguments.get(0).toString());
//                    System.out.println("Move value parsed: " + moveValue);
//                    return new JumpCommand(moveValue);
//                } else {
//                    System.out.println("Move command missing value in arguments");
//                    return new ErrorCommand();
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Invalid move value in arguments");
//                return new ErrorCommand();
//            }
//        }

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

        else if ("door".equalsIgnoreCase(commandName)){
            return new DoorCommand();
        }

        else if ("barricade".equalsIgnoreCase(commandName)){
            return new BarricadeCommand();
        }

        else if ("drop".equalsIgnoreCase(commandName)){
            return new DropItem(String.valueOf(arguments));
        }

        else if ("take".equalsIgnoreCase(commandName)){
            return new PickUpItem(String.valueOf(arguments));
        }

        else if ("use".equalsIgnoreCase(commandName)){
            return new UseItem(String.valueOf(arguments));
        }

        else if ("pickup".equalsIgnoreCase(commandName)){
            return new PickUpWeapon(String.valueOf(arguments));
        }

        else if ("lose".equalsIgnoreCase(commandName)) {
            return new WeaponDisposal(String.valueOf(arguments));
        }

        else if ("arm".equalsIgnoreCase(commandName)) {
            return new UseWeapon(String.valueOf(arguments));
        }

        else if ("load".equalsIgnoreCase(commandName)) {
            return new LoadWeapon(String.valueOf(arguments));
        }

        else if ("break".equalsIgnoreCase(commandName)) {
            return new BreakCommand();
        }

        else if ("stow".equalsIgnoreCase(commandName)) {
            return new StowWeapon(String.valueOf(arguments));
        }


        Command command = commands.get(commandName.toLowerCase());
        if (command == null) {
            System.out.println("Command not recognized, defaulting to error command");
            return new ErrorCommand();
        }

        return command;
    }
}