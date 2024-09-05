package no.org.Protocols;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Request {
    public static JSONObject getJsonObject(String name) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Ask user for input
        System.out.print("Enter command name: ");
        String commandName = scanner.nextLine();

        String[] commandParts = commandName.split(" ");
        JSONArray arguments = new JSONArray();

        if (commandParts.length > 1) {
            commandName = commandParts[0];
            // Add the second part of the command as an argument if it exists
            for (int i = 1; i < commandParts.length; i++) {
                arguments.put(commandParts[i]);
            }
        }

        // Create a JSON object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("command", commandName);
        jsonObject.put("arguments", arguments);


        // Return the JSON object
        System.out.println(jsonObject);
        return jsonObject;
    }
}
