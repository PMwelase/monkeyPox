package no.org.ClientPackage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class LaunchCommand  {

    public static JSONObject launch(){

        System.out.println("-------------------------------------------------------------");
        System.out.println("                       WELCOME PLAYER!                       ");
        System.out.println("-------------------------------------------------------------");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Type launch to launch your player: \n");
        System.out.println("Example <launch sniper Hal>: ");

        JSONObject launchCommand = new JSONObject();
        System.out.println("Enter Command: ");
        String userInput = scanner.nextLine();

        String[] splitCommand = userInput.split(" ");

        String playerKind = splitCommand[1].toLowerCase();
        String command = splitCommand[0].toLowerCase();
        String name = splitCommand[2].toLowerCase();

        int shield = 0;
        int shots = 0;

        switch (playerKind) {
            case "sniper" -> {
                shield = 20;
                shots = 15;
            }
            case "hitman" -> {
                shield = 30;
                shots = 10;
            }
            case "assassin" -> {
                shield = 10;
                shots = 20;
            }
            default -> {
                System.out.println("Unknown player kind: " + playerKind);
            }
        }

        ArrayList<Object> stats = new ArrayList<>();
        stats.add(playerKind);
        stats.add(shield);
        stats.add(shots);

        launchCommand.put("name", name);
        launchCommand.put("command", command);
        launchCommand.put("arguments", stats);

        return launchCommand;
    }

}